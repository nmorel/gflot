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

import ca.nanometrics.gflot.client.Axes;
import ca.nanometrics.gflot.client.Pan;
import ca.nanometrics.gflot.client.PlotSelectionArea;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.Zoom;
import ca.nanometrics.gflot.client.event.LoadImagesCallback;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotPanListener;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.event.PlotZoomListener;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Image;

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

    protected Plot()
    {
        // empty
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
                        function() {
                            callback.@ca.nanometrics.gflot.client.event.LoadImagesCallback::onImagesLoaded(Lcom/google/gwt/core/client/JsArray;Lca/nanometrics/gflot/client/options/PlotOptions;)(series, options);
                        });
    }-*/;

    public final native void setData( JsArray<Series> series )
    /*-{
        this.setData(series);
    }-*/;

    public final native void draw()
    /*-{
        this.draw();
    }-*/;

    public final native void setupGrid()
    /*-{
        this.setupGrid();
    }-*/;

    public final native void addPlotSelectedListener( Element container, PlotSelectedListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotselected",
                        function(event, area) {
                            listener.@ca.nanometrics.gflot.client.event.PlotSelectedListener::onPlotSelected(Lca/nanometrics/gflot/client/PlotSelectionArea;)(area);
                        });
    }-*/;

    public final native void addPlotSelectingListener( Element container, PlotSelectingListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotselecting",
                        function(event, area) {
                            listener.@ca.nanometrics.gflot.client.event.PlotSelectingListener::onPlotSelecting(Lca/nanometrics/gflot/client/PlotSelectionArea;)(area);
                        });
    }-*/;

    public final native void addPlotUnselectedListener( Element container, PlotUnselectedListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotunselected",
                        function(event) {
                            listener.@ca.nanometrics.gflot.client.event.PlotUnselectedListener::onPlotUnselected()();
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
                        function(event, pos, item) {
                            if (item != null || !onlyOnDatapoint) {
                                listener.@ca.nanometrics.gflot.client.event.PlotHoverListener::onPlotHover(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, pos, item);
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
                        function(event, pos, item) {
                            if (item != null || !onlyOnDatapoint) {
                                listener.@ca.nanometrics.gflot.client.event.PlotClickListener::onPlotClick(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, pos, item);
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

    public final native PlotOptions getPlotOptions()
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
                        function(event, plot) {
                            listener.@ca.nanometrics.gflot.client.event.PlotPanListener::onPlotPan(Lca/nanometrics/gflot/client/jsni/Plot;)(plot);
                        });
    }-*/;

    public final native void addPlotZoomListener( Element container, PlotZoomListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotzoom",
                        function(event, plot) {
                            listener.@ca.nanometrics.gflot.client.event.PlotZoomListener::onPlotZoom(Lca/nanometrics/gflot/client/jsni/Plot;)(plot);
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
}
