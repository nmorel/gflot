/*
 * Copyright (c) 2014 naBerTech
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

/**
 * @author Igor Baldachini
 */
(function ($){
	var pluginName = "background", pluginVersion = "1.0";
	var options ={
		grid:{
			background:{
				imageurl: null,
				alpha: null
			}
		}
	};
	function init(plot){
		plot.hooks.draw.push(function (plot, ctx) {
			var offset = plot.getPlotOffset();
			var opt = plot.getOptions();
			var img = new Image();
			ctx.save();
			ctx.translate(offset.left, offset.top);
			img.src = opt.grid.background.imageurl;
			if(typeof img !== "undefined"){
				var alpha = ctx.globalAlpha;
				if (opt.grid.background.alpha)
					ctx.globalAlpha = opt.grid.background.alpha;
				ctx.drawImage(img, 0, 0, plot.width(), plot.height());
				ctx.globalAlpha = alpha;
			}
			ctx.restore();
		});
	}
	$.plot.plugins.push({
		init: init,
		options: options,
		name: pluginName,
		version: pluginVersion
	});
})(jQuery); 
