/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
 * flot-tickrotor: flot plugin to display angled X-axis tick labels.
 *
 * Requires flot 0.7 or higher and a browser supporting <canvas>.
 *
 * To activate, just set xaxis.rotateTicks to an angle in degrees.  Labels
 * are rotated clockwise, so if you want the labels to angle up and to the
 * right (/) you need to provide an angle > 90.  The text will be flipped so
 * that it is still right-side-up.
 * Angles greater than or equal to 180 are ignored.
 */
(function ($) {
    var options = { };

    function init(plot) {
        // Taken from flot-axislabels.
        // This is kind of a hack. There are no hooks in Flot between
        // the creation and measuring of the ticks (setTicks, measureTickLabels
        // in setupGrid() ) and the drawing of the ticks and plot box
        // (insertAxisLabels in setupGrid() ).
        //
        // Therefore, we use a trick where we run the draw routine twice:
        // the first time to get the tick measurements, so that we can change
        // them, and then have it draw it again.

        var ticks = [];  // preserve between draw() calls.
        var font;
        var secondPass = false;
        var rotateTicks, rotateTicksRads, radsAboveHoriz;

        plot.hooks.draw.push(function (plot, ctx) {
            var xaxis;  // for convenience
            if (!secondPass) {
                var opts = plot.getAxes().xaxis.options;
                if (opts.rotateTicks === undefined) {
                    return;
                }

                rotateTicks = parseInt(opts.rotateTicks, 10);
                if (rotateTicks.toString() != opts.rotateTicks || rotateTicks == 0 || rotateTicks >= 180) {
                    return;
                }

                rotateTicksRads = rotateTicks * Math.PI/180;
                if (rotateTicks > 90) {
                    radsAboveHoriz = Math.PI - rotateTicksRads;
                } else {
                    radsAboveHoriz = Math.PI/2 - rotateTicksRads;
                }

                font = opts.rotateTicksFont;
                if (!font) {
                    font = $('.tickLabel').css('font');
                }
                if (!font) {
                    font = 'smaller sans-serif';
                }

                var elem, maxLabelWidth = 0, maxLabelHeight = 0, minX = 0, maxX = 0;

                // We have to clear the ticks option so that flot core
                // doesn't draw ticks superimposed with ours, but we preserve
                // the tick data as xaxis.rotatedTicks so that external code
                // can still get to it.

                // FIXME: It would obviously be better to just interrupt
                // the drawing of the ticks and preserve the 'ticks'
                // property.  That probably requires another hook.

                xaxis = plot.getAxes().xaxis;
                ticks = plot.getAxes().xaxis.ticks;
                xaxis.rotatedTicks = ticks;
                opts.ticks = [];  // we'll make our own

                var x;
                for (var i = 0; i < ticks.length; i++) {
                  elem = $('<span style="font:' + font + '">' + ticks[i].label + '</span>');
                  plot.getPlaceholder().append(elem);
                  ticks[i].height = elem.outerHeight(true);
                  ticks[i].width = elem.outerWidth(true);
                  elem.remove();
                  if (ticks[i].height > maxLabelHeight) {
                      maxLabelHeight = ticks[i].height;
                  }
                  if (ticks[i].width > maxLabelWidth) {
                      maxLabelWidth = ticks[i].width;
                  }
                  var tick = ticks[i];
                  // See second-draw code below for explanation of offsets.
                  if (rotateTicks > 90) {
                      // See if any labels are too long and require increased left
                      // padding.
                      x = Math.round(plot.getPlotOffset().left + xaxis.p2c(tick.v))
                          - Math.ceil(Math.cos(radsAboveHoriz) * tick.height)
                          - Math.ceil(Math.cos(radsAboveHoriz) * tick.width);
                      if (x < minX) {
                          minX = x;
                      }
                  } else {
                      // See if any labels are too long and require increased right
                      // padding.
                      x = Math.round(plot.getPlotOffset().left + xaxis.p2c(tick.v))
                          + Math.ceil(Math.cos(radsAboveHoriz) * tick.height)
                          + Math.ceil(Math.cos(radsAboveHoriz) * tick.width);
                      if (x > maxX) {
                          maxX = x;
                      }
                  }
                }

                // Calculate maximum label height after rotating.
                if (rotateTicks > 90) {
                    var acuteRads = rotateTicksRads - Math.PI/2;
                    opts.labelHeight = Math.ceil(Math.sin(acuteRads) * maxLabelWidth)
                                       + Math.ceil(Math.sin(acuteRads) * maxLabelHeight);
                } else {
                    var acuteRads = Math.PI/2 - rotateTicksRads;
                    // Center such that the top of the label is at the center of the tick.
                    opts.labelHeight = Math.ceil(Math.sin(rotateTicksRads) * maxLabelWidth)
                                       + Math.ceil(Math.sin(acuteRads) * maxLabelHeight);
                }

                if (minX < 0) {
                  plot.getAxes().yaxis.options.labelWidth = -1 * minX;
                }

                // Doesn't seem to work if there are no values using the
                // second y axis.
                //if (maxX > xaxis.box.left + xaxis.box.width) {
                //  plot.getAxes().y2axis.options.labelWidth = maxX - xaxis.box.left - xaxis.box.width;
                //}

                // re-draw with new label widths and heights
                secondPass = true;
                plot.setupGrid();
                plot.draw();
            } else {
                if (ticks.length == 0) {
                    return;
                }
                xaxis = plot.getAxes().xaxis;
                var box = xaxis.box;
                var tick, label, xoffset, yoffset;
                for (var i = 0; i < ticks.length; i++) {
                    tick = ticks[i];
                    if (!tick.label) {
                        continue;
                    }
                    ctx.save();
                    ctx.font = font;
                    if (rotateTicks <= 90) {
                        // Center such that the top of the label is at the center of the tick.
                        xoffset = -Math.ceil(Math.cos(radsAboveHoriz) * tick.height);
                        yoffset = Math.ceil(Math.sin(radsAboveHoriz) * tick.height);
                        ctx.translate(Math.round(plot.getPlotOffset().left + xaxis.p2c(tick.v)) + xoffset,
                                      box.top + box.padding + plot.getOptions().grid.labelMargin + yoffset);
                        ctx.rotate(rotateTicksRads);
                    } else {
                        // We want the text to facing up, so we have to
                        // rotate counterclockwise, which means the label
                        // has to *end* at the center of the tick.
                        xoffset = Math.ceil(Math.cos(radsAboveHoriz) * tick.height)
                                  - Math.ceil(Math.cos(radsAboveHoriz) * tick.width);
                        yoffset = Math.ceil(Math.sin(radsAboveHoriz) * tick.width)
                                  + Math.ceil(Math.sin(radsAboveHoriz) * tick.height);
                        ctx.translate(Math.round(plot.getPlotOffset().left + xaxis.p2c(tick.v) + xoffset),
                                      box.top + box.padding + plot.getOptions().grid.labelMargin + yoffset);
                        ctx.rotate(-radsAboveHoriz);
                    }
                    ctx.fillText(tick.label, 0, 0);
                    ctx.restore();
                }
            }
        });
    }

    $.plot.plugins.push({
        init: init,
        options: options,
        name: 'tickRotor',
        version: '1.0'
    });
})(jQuery);
