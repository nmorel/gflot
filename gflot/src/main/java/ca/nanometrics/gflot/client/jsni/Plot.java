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
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.event.PlotZoomListener;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.util.JSONHelper;

import com.google.gwt.core.client.JavaScriptObject;
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

    public static Plot create( Element container, Series[] series )
    {
        return PlotImpl.create( container, JSONHelper.getJSONArray( JSONHelper.wrapArray( series ) ).getJavaScriptObject() );
    }

    public static Plot create( Element container, Series[] series, PlotOptions options )
    {
        JavaScriptObject optionsJs = null;
        if ( null == options )
        {
            optionsJs = null;
        }
        else
        {
            optionsJs = JSONHelper.getJSONObject( options ).getJavaScriptObject();
        }
        return PlotImpl.create( container, JSONHelper.getJSONArray( JSONHelper.wrapArray( series ) ).getJavaScriptObject(), optionsJs );
    }

    public static Plot create( Element container, JavaScriptObject data, JavaScriptObject options )
    {
        return PlotImpl.create( container, data, options );
    }

    public static void loadDataImages( Series[] series, PlotOptions options, LoadImagesCallback callback )
    {
        JavaScriptObject optionsJs = null;
        if ( null == options )
        {
            optionsJs = null;
        }
        else
        {
            optionsJs = JSONHelper.getJSONObject( options ).getJavaScriptObject();
        }
        PlotImpl.loadDataImages( JSONHelper.getJSONArray( JSONHelper.wrapArray( series ) ).getJavaScriptObject(), optionsJs, callback );
    }

    public final void setData( Series[] series )
    {
        PlotImpl.setData( this, JSONHelper.getJSONArray( JSONHelper.wrapArray( series ) ).getJavaScriptObject() );
    }

    public final void setData( Series series )
    {
        setData( new Series[] { series } );
    }

    public final void draw()
    {
        PlotImpl.draw( this );
    }

    public final void setupGrid()
    {
        PlotImpl.setupGrid( this );
    }

    public final void addPlotSelectedListener( Element container, PlotSelectedListener listener )
    {
        PlotImpl.addPlotSelectedListener( container, listener );
    }

    public final void addPlotSelectingListener( Element container, PlotSelectingListener listener )
    {
        PlotImpl.addPlotSelectingListener( container, listener );
    }

    public final void addPlotUnselectedListener( Element container, PlotUnselectedListener listener )
    {
        PlotImpl.addPlotUnselectedListener( container, listener );
    }

    public final PlotSelectionArea getSelection( Element container )
    {
        return PlotImpl.getSelection( this );
    }

    public final void setSelection( PlotSelectionArea area, boolean preventEvent )
    {
        PlotImpl.setSelection( this, JSONHelper.getJSONObject( area ).getJavaScriptObject(), preventEvent );
    }

    public final void clearSelection( boolean preventEvent )
    {
        PlotImpl.clearSelection( this, preventEvent );
    }

    public final void addPlotHoverListener( Element container, PlotHoverListener listener, boolean onlyOnDatapoint )
    {
        PlotImpl.addPlotHoverListener( container, listener, onlyOnDatapoint, this );
    }

    public final void addPlotClickListener( Element container, PlotClickListener listener, boolean onlyOnDatapoint )
    {
        PlotImpl.addPlotClickListener( container, listener, onlyOnDatapoint, this );
    }

    public final int getPlotOffsetLeft()
    {
        return PlotImpl.getPlotOffsetLeft( this );
    }

    public final int getPlotOffsetRight()
    {
        return PlotImpl.getPlotOffsetRight( this );
    }

    public final int getPlotOffsetTop()
    {
        return PlotImpl.getPlotOffsetTop( this );
    }

    public final int getPlotOffsetBottom()
    {
        return PlotImpl.getPlotOffsetBottom( this );
    }

    public final PlotOptions getPlotOptions()
    {
        return PlotImpl.getPlotOptions( this );
    }

    public final void saveAsImage()
    {
        PlotImpl.saveAsImage( this );
    }

    public final void saveAsImage( int width, int height )
    {
        PlotImpl.saveAsImage( this, width, height );
    }

    public final Image getImage()
    {
        Element img = PlotImpl.getImage( this );
        if ( null == img )
        {
            return null;
        }
        return new PlotImage( img );
    }

    public final Image getImage( int width, int height )
    {
        Element img = PlotImpl.getImage( this, width, height );
        if ( null == img )
        {
            return null;
        }
        return new PlotImage( img );
    }

    public final Axes getAxes()
    {
        return PlotImpl.getAxes( this );
    }

    public final void addPlotPanListener( Element container, PlotPanListener listener )
    {
        PlotImpl.addPlotPanListener( container, listener );
    }

    public final void addPlotZoomListener( Element container, PlotZoomListener listener )
    {
        PlotImpl.addPlotZoomListener( container, listener );
    }

    public final void zoom( Zoom zoom )
    {
        PlotImpl.zoom( this, JSONHelper.getJSONObject( zoom ).getJavaScriptObject() );
    }

    public final void zoomOut( Zoom zoom )
    {
        PlotImpl.zoomOut( this, JSONHelper.getJSONObject( zoom ).getJavaScriptObject() );
    }

    public final void pan( Pan pan )
    {
        PlotImpl.pan( this, JSONHelper.getJSONObject( pan ).getJavaScriptObject() );
    }
}
