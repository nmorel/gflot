/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client.jsni;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.gflot.client.*;
import com.googlecode.gflot.client.event.LoadImagesCallback;
import com.googlecode.gflot.client.event.PlotClickListener;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotPanListener;
import com.googlecode.gflot.client.event.PlotPosition;
import com.googlecode.gflot.client.event.PlotSelectedListener;
import com.googlecode.gflot.client.event.PlotSelectingListener;
import com.googlecode.gflot.client.event.PlotUnselectedListener;
import com.googlecode.gflot.client.event.PlotZoomListener;
import com.googlecode.gflot.client.options.PieSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;

/**
 * @author AlexanderDeleon
 */
public class Plot
    extends JavaScriptObject
{
    private static class PlotImage
        extends Image
    {
        PlotImage( Element element )
        {
            super( element );
        }
    }

    public static final native Plot create( Element container, JsArray<Series> series )
    /*-{
        return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series);
    }-*/;

    public static final native Plot create( Element container, JsArray<Series> series, PlotOptions options )
    /*-{
        return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series,
            options);
    }-*/;

    public static final native void loadDataImages( JsArray<Series> series, PlotOptions options,
                                                    LoadImagesCallback callback )
    /*-{
        $wnd.jQuery.plot.image
            .loadDataImages(
            series,
            options,
            function () {
                callback.@com.googlecode.gflot.client.event.LoadImagesCallback::onImagesLoaded(Lcom/google/gwt/core/client/JsArray;Lcom/googlecode/gflot/client/options/PlotOptions;)(series, options);
            });
    }-*/;

    protected Plot()
    {
        // empty
    }

    public final native void draw()
    /*-{
        this.draw();
    }-*/;

    public final native void setupGrid()
    /*-{
        this.setupGrid();
    }-*/;

    public final native void triggerRedrawOverlay()
    /*-{
        this.triggerRedrawOverlay();
    }-*/;

    public final native void addPlotSelectedListener( Element container, PlotSelectedListener listener )
    /*-{
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotselected",
            function (event, area) {
                listener.@com.googlecode.gflot.client.event.PlotSelectedListener::onPlotSelected(Lcom/googlecode/gflot/client/PlotSelectionArea;)(area);
            });
    }-*/;

    public final native void addPlotSelectingListener( Element container, PlotSelectingListener listener )
    /*-{
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotselecting",
            function (event, area) {
                listener.@com.googlecode.gflot.client.event.PlotSelectingListener::onPlotSelecting(Lcom/googlecode/gflot/client/PlotSelectionArea;)(area);
            });
    }-*/;

    public final native void addPlotUnselectedListener( Element container, PlotUnselectedListener listener )
    /*-{
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotunselected",
            function (event) {
                listener.@com.googlecode.gflot.client.event.PlotUnselectedListener::onPlotUnselected()();
            });
    }-*/;

    public final native PlotSelectionArea getSelection()
    /*-{
        return this.getSelection();
    }-*/;

    public final native void setSelection( PlotSelectionArea area, boolean preventEvent )
    /*-{
        this.setSelection(area, preventEvent);
    }-*/;

    public final native void clearSelection( boolean preventEvent )
    /*-{
        this.clearSelection(preventEvent);
    }-*/;

    public final native void addPlotHoverListener( Element container, PlotHoverListener listener,
                                                   boolean onlyOnDatapoint )
    /*-{
        var plot = this;
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plothover",
            function (event, pos, item) {
                if (item != null || !onlyOnDatapoint) {
                    listener.@com.googlecode.gflot.client.event.PlotHoverListener::onPlotHover(Lcom/googlecode/gflot/client/jsni/Plot;Lcom/googlecode/gflot/client/event/PlotPosition;Lcom/googlecode/gflot/client/event/PlotItem;)(plot, pos, item);
                }
            });
    }-*/;

    public final native void addPlotClickListener( Element container, PlotClickListener listener,
                                                   boolean onlyOnDatapoint )
    /*-{
        var plot = this;
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotclick",
            function (event, pos, item) {
                if (item != null || !onlyOnDatapoint) {
                    listener.@com.googlecode.gflot.client.event.PlotClickListener::onPlotClick(Lcom/googlecode/gflot/client/jsni/Plot;Lcom/googlecode/gflot/client/event/PlotPosition;Lcom/googlecode/gflot/client/event/PlotItem;)(plot, pos, item);
                }
            });
    }-*/;

    public final native int getPlotOffsetLeft()
    /*-{
        var offset = this.getPlotOffset().left;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    public final native int getPlotOffsetRight()
    /*-{
        var offset = this.getPlotOffset().right;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    public final native int getPlotOffsetTop()
    /*-{
        var offset = this.getPlotOffset().top;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    public final native int getPlotOffsetBottom()
    /*-{
        var offset = this.getPlotOffset().bottom;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    public final native JsArray<JsonObject> getData()
    /*-{
        return this.getData();
    }-*/;

    public final native void setData( JsArray<Series> series )
    /*-{
        this.setData(series);
    }-*/;

    public final native PlotOptions getOptions()
    /*-{
        return this.getOptions();
    }-*/;

    public final native void saveAsImage()
    /*-{
        $wnd.Canvas2Image.saveAsPNG(this.getCanvas());
    }-*/;

    public final native void saveAsImage( int width, int height )
    /*-{
        $wnd.Canvas2Image.saveAsPNG(this.getCanvas(), false, width, height);
    }-*/;

    public final Image getImage()
    {
        Element img = getImage0();
        if ( null == img )
        {
            return null;
        }
        return new PlotImage( img );
    }

    private final native Element getImage0()
    /*-{
        return $wnd.Canvas2Image.saveAsPNG(this.getCanvas(), true);
    }-*/;

    public final Image getImage( int width, int height )
    {
        Element img = getImage0( width, height );
        if ( null == img )
        {
            return null;
        }
        return new PlotImage( img );
    }

    private final native Element getImage0( int width, int height )
    /*-{
        return $wnd.Canvas2Image.saveAsPNG(this.getCanvas(), true, width,
            height);
    }-*/;

    public final native Axes getAxes()
    /*-{
        return this.getAxes();
    }-*/;

    public final native void addPlotPanListener( Element container, PlotPanListener listener )
    /*-{
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotpan",
            function (event, plot) {
                listener.@com.googlecode.gflot.client.event.PlotPanListener::onPlotPan(Lcom/googlecode/gflot/client/jsni/Plot;)(plot);
            });
    }-*/;

    public final native void addPlotZoomListener( Element container, PlotZoomListener listener )
    /*-{
        $wnd
            .jQuery("#" + container.id)
            .bind(
            "plotzoom",
            function (event, plot) {
                listener.@com.googlecode.gflot.client.event.PlotZoomListener::onPlotZoom(Lcom/googlecode/gflot/client/jsni/Plot;)(plot);
            });
    }-*/;

    public final native void zoom( Zoom zoom )
    /*-{
        this.zoom(zoom);
    }-*/;

    public final native void zoomOut( Zoom zoom )
    /*-{
        this.zoomOut(zoom);
    }-*/;

    public final native void pan( Pan pan )
    /*-{
        this.pan(pan);
    }-*/;

    public final native void setCrosshair( PlotPosition pos )
    /*-{
        this.setCrosshair(pos);
    }-*/;

    public final native void clearCrosshair()
    /*-{
        this.clearCrosshair();
    }-*/;

    public final native void lockCrosshair()
    /*-{
        this.lockCrosshair();
    }-*/;

    public final native void lockCrosshair( PlotPosition pos )
    /*-{
        this.lockCrosshair(pos);
    }-*/;

    public final native void unlockCrosshair()
    /*-{
        this.unlockCrosshair();
    }-*/;

    /**
     * Highlight a specific datapoint in the data series.
     *
     * @param series
     * @param datapoint
     */
    public final native void highlight( Series series, DataPoint datapoint )
    /*-{
        this.highlight(series, datapoint);
    }-*/;

    /**
     * Highlight a specific datapoint in the data series.
     *
     * @param seriesIndex index of the series (starting at 0)
     * @param datapointIndex index of the datapoint in the series (starting at 0)
     */
    public final native void highlight( int seriesIndex, int datapointIndex )
    /*-{
        this.highlight(seriesIndex, datapointIndex);
    }-*/;

    /**
     * Unhighlight a specific datapoint in the data series.
     *
     * @param series
     * @param datapoint
     */
    public final native void unhighlight( Series series, DataPoint datapoint )
    /*-{
        this.unhighlight(series, datapoint);
    }-*/;

    /**
     * Unhighlight a specific datapoint in the data series.
     *
     * @param seriesIndex index of the series (starting at 0)
     * @param datapointIndex index of the datapoint in the series (starting at 0)
     */
    public final native void unhighlight( int seriesIndex, int datapointIndex )
    /*-{
        this.unhighlight(seriesIndex, datapointIndex);
    }-*/;

    /**
     * Removes all current highlights
     */
    public final native void unhighlight()
    /*-{
        this.unhighlight();
    }-*/;

    /**
     * Gets the width of the plotting area inside the grid.
     * This is smaller than the canvas or placeholder dimensions as some
     * extra space is needed (e.g. for labels).
     */
    public final native int width()
    /*-{
        return this.width();
    }-*/;

    /**
     * Gets the height of the plotting area inside the grid.
     * This is smaller than the canvas or placeholder dimensions as some
     * extra space is needed (e.g. for labels).
     */
    public final native int height()
    /*-{
        return this.height();
    }-*/;

    /**
     * Tells Flot to resize the drawing canvas to the size of the
     * placeholder. You need to run setupGrid() and draw() afterwards as
     * canvas resizing is a destructive operation. This is used
     * internally by the resize plugin.
     */
    public final native void resize()
    /*-{
        this.resize();
    }-*/;

    /**
     * Cleans up any event handlers Flot has currently registered. This
     * is used internally.
     */
    public final native void shutdown()
    /*-{
        this.shutdown();
    }-*/;

    /**
     * Cleans up any event handlers Flot has currently registered. This
     * is used internally.
     */
    public final native PieSeriesOptions.Offset offset()
    /*-{
        return this.offset();
    }-*/;

    /**
     * Returns the calculated offset of the data point at (x, y) in data
     * space within the placeholder div. If you are working with multiple axes, you
     * can specify the x and y axis references, e.g.
     * <p/>
     * o = pointOffset({ x: xpos, y: ypos, xaxis: 2, yaxis: 3 })
     * // o.left and o.top now contains the offset within the div
     */
    public final native PieSeriesOptions.Offset pointOffset( JsonObject point )
    /*-{
        return this.pointOffset(point);
    }-*/;
}
