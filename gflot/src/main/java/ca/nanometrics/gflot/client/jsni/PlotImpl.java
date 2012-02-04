/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.jsni;

import ca.nanometrics.gflot.client.PlotSelectionArea;
import ca.nanometrics.gflot.client.event.LoadImagesCallback;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author AlexanderDeleon
 */
public class PlotImpl
{

    static native Plot create( Element container, JavaScriptObject series )
    /*-{
		return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series);
    }-*/;

    static native Plot create( Element container, JavaScriptObject series, JavaScriptObject options )
    /*-{
		return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series,
				options);
    }-*/;

    static native void loadDataImages( JavaScriptObject data, JavaScriptObject options, LoadImagesCallback callback )
    /*-{
		$wnd.jQuery.plot.image
				.loadDataImages(
						data,
						options,
						function() {
							callback.@ca.nanometrics.gflot.client.event.LoadImagesCallback::onImagesLoaded(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(data, options);
						});
    }-*/;

    static native void setData( Plot plot, JavaScriptObject series )
    /*-{
		plot.setData(series);
    }-*/;

    static native void draw( Plot plot )
    /*-{
		plot.draw();
    }-*/;

    static native void setupGrid( Plot plot )
    /*-{
		plot.setupGrid();
    }-*/;

    static native void setLinearSelection( Plot plot, double x1, double x2 )
    /*-{
		plot.setSelection({
			'x1' : x1,
			'x2' : x2
		});
    }-*/;

    static native void setRectangularSelection( Plot plot, double x1, double y1, double x2, double y2 )
    /*-{
		plot.setSelection({
			'x1' : x1,
			'y1' : y1,
			'x2' : x2,
			'y2' : y2
		});
    }-*/;

    static native void addPlotSelectedListener( Element container, PlotSelectedListener listener )
    /*-{
		$wnd
				.jQuery("#" + container.id)
				.bind(
						"plotselected",
						function(event, area) {
							var range = @ca.nanometrics.gflot.client.PlotSelectionArea::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(area));
							listener.@ca.nanometrics.gflot.client.event.PlotSelectedListener::onPlotSelected(Lca/nanometrics/gflot/client/PlotSelectionArea;)(range);
						});
    }-*/;

    static native void addPlotSelectingListener( Element container, PlotSelectingListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotselecting",
                        function(event, area) {
                            var range = @ca.nanometrics.gflot.client.PlotSelectionArea::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(area));
                            listener.@ca.nanometrics.gflot.client.event.PlotSelectingListener::onPlotSelecting(Lca/nanometrics/gflot/client/PlotSelectionArea;)(range);
                        });
    }-*/;

    static native void addPlotUnselectedListener( Element container, PlotUnselectedListener listener )
    /*-{
		$wnd
				.jQuery("#" + container.id)
				.bind(
						"plotunselected",
						function(event) {
							listener.@ca.nanometrics.gflot.client.event.PlotUnselectedListener::onPlotUnselected()();
						});
    }-*/;

    static native PlotSelectionArea getSelection( Plot plot )
    /*-{
        var jsSelection = plot.getSelection();
        if(jsSelection==null){
            return null;
        }
        var selection = @ca.nanometrics.gflot.client.PlotSelectionArea::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(jsSelection));
        return selection;
    }-*/;

    static native void setSelection( Plot plot, JavaScriptObject area, boolean preventEvent )
    /*-{
		plot.setSelection(area, preventEvent);
    }-*/;

    static native void clearSelection( Plot plot, boolean preventEvent )
    /*-{
		plot.clearSelection(preventEvent);
    }-*/;

    static native void addPlotHoverListener( Element container, PlotHoverListener listener, boolean onlyOnDatapoint, Plot plot )
    /*-{
        $wnd.jQuery("#"+container.id).bind("plothover", function(event, pos, item) {
            if(item != null || !onlyOnDatapoint){
                var javaPos = pos==null?null:@ca.nanometrics.gflot.client.event.PlotPosition::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(pos));
                var javaItem = item==null?null:@ca.nanometrics.gflot.client.event.PlotItem::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(item));
                listener.@ca.nanometrics.gflot.client.event.PlotHoverListener::onPlotHover(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, javaPos, javaItem);
            }
        });
    }-*/;

    static native void addPlotClickListener( Element container, PlotClickListener listener, boolean onlyOnDatapoint, Plot plot )
    /*-{
        $wnd.jQuery("#"+container.id).bind("plotclick", function(event, pos, item) {
            if(item != null || !onlyOnDatapoint){
                var javaPos = pos==null?null:@ca.nanometrics.gflot.client.event.PlotPosition::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(pos));
                var javaItem = item==null?null:@ca.nanometrics.gflot.client.event.PlotItem::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(item));
                listener.@ca.nanometrics.gflot.client.event.PlotClickListener::onPlotClick(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, javaPos, javaItem);
            }
        });
    }-*/;

    static native int getPlotOffsetLeft( Plot plot )
    /*-{
		var offset = plot.getPlotOffset().left;
		return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetRight( Plot plot )
    /*-{
		var offset = plot.getPlotOffset().right;
		return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetTop( Plot plot )
    /*-{
		var offset = plot.getPlotOffset().top;
		return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetBottom( Plot plot )
    /*-{
		var offset = plot.getPlotOffset().bottom;
		return (offset === undefined) ? -1 : offset;
    }-*/;

    static native PlotOptions getPlotOptions( Plot plot )
    /*-{
        var jsOptions = plot.getOptions();
        var options = @ca.nanometrics.gflot.client.options.PlotOptions::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(jsOptions));
		return options;
    }-*/;
}
