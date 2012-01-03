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
package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.util.JQueryHelper;
import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;

/**
 * The legend is generated as a table with the data series labels and small label boxes with the color of the series.
 *
 * @author AlexanderDeleon
 */
public class LegendOptions
    extends JSONObjectWrapper
{
    public interface LabelFormatter
    {
        String formatLabel( String label, Series series );
    }

    public enum LegendPosition
    {
        NORTH_EAST( "ne" ), NORTH_WEST( "nw" ), SOUTH_EAST( "se" ), SOUTH_WEST( "sw" );

        private String flotValue;

        LegendPosition( String flotValue )
        {
            this.flotValue = flotValue;
        }

        String getFlotValue()
        {
            return flotValue;
        }
    }

    /**
     * Set if the legend is shown or not
     */
    public LegendOptions setShow( boolean show )
    {
        put( "show", show );
        return this;
    }

    /**
     * Set the border color of the label box.
     */
    public LegendOptions setLabelBoxBorderColor( String cssColor )
    {
        put( "labelBoxBorderColor", cssColor );
        return this;
    }

    /**
     * Set the number of columns to divide the legend table into.
     */
    public LegendOptions setNumOfColumns( int cols )
    {
        put( "noColumns", new Integer( cols ) );
        return this;
    }

    /**
     * Set the overall placement of the legend within the plot (LegendPosition.NORTH_EAST, LegendPosition.NORTH_WEST,
     * LegendPosition.SOUTH_EAST or LegendPosition.SOUTH_WEST). By default, the placement is LegendPosition.NORTH_EAST.
     */
    public LegendOptions setPosition( LegendPosition position )
    {
        assert null != position : "position can't be null";

        put( "position", position.getFlotValue() );
        return this;
    }

    /**
     * Set the distance to the plot edge
     */
    public LegendOptions setMargin( double margin )
    {
        put( "margin", new Double( margin ) );
        return this;
    }

    /**
     * Set the distance to the plot edge
     */
    public LegendOptions setMargin( double marginX, double marginY )
    {
        put( "margin", JSONHelper.wrapArray( new Double[] { marginX, marginY } ) );
        return this;
    }

    /**
     * Set the background color
     */
    public LegendOptions setBackgroundColor( String cssColor )
    {
        put( "backgroundColor", cssColor );
        return this;
    }

    /**
     * Set the background opacity. Opacity range from 0.0 to 1.0.
     */
    public LegendOptions setBackgroundOpacity( double opacity )
    {
        assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

        put( "backgroundOpacity", new Double( opacity ) );
        return this;
    }

    /**
     * Set a custom container to put the legend table into. The "position" and "margin" etc. options will then be
     * ignored. Note that Flot will overwrite the contents of the container.
     */
    public LegendOptions setContainer( Element container )
    {
        assert null != container : "container can't be null";

        String id = DOM.getElementProperty( container, "id" );
        if ( id == null || id.length() == 0 )
        {
            id = "gflot-legend-container";
            DOM.setElementProperty( container, "id", id );
        }
        put( "container", JSONHelper.wrapObject( new JSONObject( JQueryHelper.getJQueryObj( id ) ) ) );
        return this;
    }

    /**
     * Set the labelFormatter if you want to format the labels in some way, e.g. make them to links.
     */
    public LegendOptions setLabelFormatter( LabelFormatter labelFormatter )
    {
        assert null != labelFormatter : "labelFormatter can't be null";

        setLabelFormatterNative( getWrappedObj().getJavaScriptObject(), labelFormatter );
        return this;
    }

    private static native void setLabelFormatterNative( JavaScriptObject legendOptions, LabelFormatter labelFormatter )
    /*-{
        legendOptions.labelFormatter = function(label, series)
        {
            var jsonSeriesObject = @com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)(series);
            var javaSeriesObject = @ca.nanometrics.gflot.client.Series::new(Lcom/google/gwt/json/client/JSONObject;)(jsonSeriesObject);
            return labelFormatter.@ca.nanometrics.gflot.client.options.LegendOptions.LabelFormatter::formatLabel(Ljava/lang/String;Lca/nanometrics/gflot/client/Series;)(label, javaSeriesObject);
        };
    }-*/;
}
