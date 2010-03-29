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

import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.SelectionListener;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;

/**
 * @author AlexanderDeleon
 */
public class PlotImpl {

	static native Plot create(Element container, JavaScriptObject series)/*-{
																			var plot = new $wnd.jQuery.plot($wnd.jQuery("#"+container.id), series);
																			return plot;
																			}-*/;

	static native Plot create(Element container, JavaScriptObject series, JavaScriptObject options)/*-{
																									var plot = new $wnd.jQuery.plot($wnd.jQuery("#"+container.id), series, options);
																									return plot;
																									}-*/;

	static native void setData(Plot plot, JavaScriptObject series)/*-{
																				           plot.setData(series);
																				    }-*/;

	static native void draw(Plot plot)/*-{
																				           plot.draw();
																				    }-*/;

	static native void setupGrid(Plot plot)/*-{
																				           plot.setupGrid();
																				    }-*/;

	static native void setLinearSelection(Plot plot, double x1, double x2)/*-{
																				           plot.setSelection({ 'x1': x1, 'x2': x2});
																				    }-*/;

	static native void setRectangularSelection(Plot plot, double x1, double y1, double x2, double y2)/*-{
																																					           plot.setSelection({ 'x1': x1, 'y1': y1,  'x2': x2, 'y2': y2});
																																					     }-*/;

	static native void addSelectionListener(Element container, SelectionListener listener)/*-{
																																	           $wnd.jQuery("#"+container.id).bind("selected", function(event, area) {
																																	             listener.@ca.nanometrics.gflot.client.event.SelectionListener::selected(DDDD)(area.x1, area.y1, area.x2, area.y2);
																																	           });
																																	    }-*/;

	static native void addPlotHoverListener(Element container, PlotHoverListener listener, boolean onlyOnDatapoint,
			Plot plot)/*-{
							           $wnd.jQuery("#"+container.id).bind("plothover", function(event, pos, item) {
							              var javaPos = pos==null?null:@ca.nanometrics.gflot.client.PlotPosition::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(pos));
							              var javaItem = item==null?null:@ca.nanometrics.gflot.client.PlotItem::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(item));
							              if(item != null || !onlyOnDatapoint){
							                  listener.@ca.nanometrics.gflot.client.event.PlotHoverListener::onPlotHover(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/PlotPosition;Lca/nanometrics/gflot/client/PlotItem;)(plot, javaPos, javaItem);
							              }
							           });
							    }-*/;

	static native void addPlotClickListener(Element container, PlotClickListener listener, boolean onlyOnDatapoint,
			Plot plot)/*-{
							      $wnd.jQuery("#"+container.id).bind("plotclick", function(event, pos, item) {
							      var javaPos = pos==null?null:@ca.nanometrics.gflot.client.PlotPosition::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(pos));
							      var javaItem = item==null?null:@ca.nanometrics.gflot.client.PlotItem::new(Lcom/google/gwt/json/client/JSONObject;)(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(item));
							        if(item != null || !onlyOnDatapoint){
							          listener.@ca.nanometrics.gflot.client.event.PlotClickListener::onPlotClick(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/PlotPosition;Lca/nanometrics/gflot/client/PlotItem;)(plot, javaPos, javaItem);
							        }
							      });
							    }-*/;

	static native int getPlotOffsetLeft(Plot plot)/*-{
													var offset = plot.getPlotOffset().left;
													return (offset === undefined) ? -1 : offset;
															}-*/;

	static native int getPlotOffsetRight(Plot plot)/*-{
													var offset = plot.getPlotOffset().right;
													return (offset === undefined) ? -1 : offset;
													}-*/;

	static native int getPlotOffsetTop(Plot plot)/*-{
													var offset = plot.getPlotOffset().top;
													return (offset === undefined) ? -1 : offset;
													}-*/;

	static native int getPlotOffsetBottom(Plot plot)/*-{
													var offset = plot.getPlotOffset().bottom;
													return (offset === undefined) ? -1 : offset;
													}-*/;
}
