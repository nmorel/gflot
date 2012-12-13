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
import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/**
 * The legend is generated as a table with the data series labels and small label boxes with the color of the series.
 * 
 * @author AlexanderDeleon
 */
public class LegendOptions
    extends JsonObject
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

        static LegendPosition findByFlotValue( String flotValue )
        {
            if ( null != flotValue && !"".equals( flotValue ) )
            {
                for ( LegendPosition mode : values() )
                {
                    if ( mode.getFlotValue().equals( flotValue ) )
                    {
                        return mode;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Creates a {@link LegendOptions}
     */
    public static final LegendOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String SHOW_KEY = "show";
    private static final String LABEL_BOX_BORDER_COLOR_KEY = "labelBoxBorderColor";
    private static final String NUM_COLUMNS_KEY = "noColumns";
    private static final String POSITION_KEY = "position";
    private static final String MARGIN_KEY = "margin";
    private static final String BACKGROUND_COLOR_KEY = "backgroundColor";
    private static final String BACKGROUND_OPACITY_KEY = "backgroundOpacity";
    private static final String CONTAINER_KEY = "container";

    protected LegendOptions()
    {
    }

    /**
     * Set if the legend is shown or not
     */
    public final LegendOptions setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return this;
    }

    /**
     * @return true if the legend is shown
     */
    public final Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Clear if the legend is shown or not
     */
    public final LegendOptions clearShow()
    {
        clear( SHOW_KEY );
        return this;
    }

    /**
     * Set the border color of the label box.
     */
    public final LegendOptions setLabelBoxBorderColor( String cssColor )
    {
        put( LABEL_BOX_BORDER_COLOR_KEY, cssColor );
        return this;
    }

    /**
     * @return the border color of the label box
     */
    public final String getLabelBoxBorderColor()
    {
        return getString( LABEL_BOX_BORDER_COLOR_KEY );
    }

    /**
     * Clear the border color of the label box
     */
    public final LegendOptions clearLabelBoxBorderColor()
    {
        clear( LABEL_BOX_BORDER_COLOR_KEY );
        return this;
    }

    /**
     * Set the number of columns to divide the legend table into.
     */
    public final LegendOptions setNumOfColumns( int cols )
    {
        put( NUM_COLUMNS_KEY, new Integer( cols ) );
        return this;
    }

    /**
     * @return the number of columns to divide the legend table into
     */
    public final Integer getNumOfColumns()
    {
        return getInteger( NUM_COLUMNS_KEY );
    }

    /**
     * Clear the number of columns to divide the legend table into
     */
    public final LegendOptions clearNumOfColumns()
    {
        clear( NUM_COLUMNS_KEY );
        return this;
    }

    /**
     * Set the overall placement of the legend within the plot (LegendPosition.NORTH_EAST, LegendPosition.NORTH_WEST,
     * LegendPosition.SOUTH_EAST or LegendPosition.SOUTH_WEST). By default, the placement is LegendPosition.NORTH_EAST.
     */
    public final LegendOptions setPosition( LegendPosition position )
    {
        assert null != position : "position can't be null";

        put( POSITION_KEY, position.getFlotValue() );
        return this;
    }

    /**
     * @return the overall placement of the legend within the plot
     */
    public final LegendPosition getPosition()
    {
        return LegendPosition.findByFlotValue( getString( POSITION_KEY ) );
    }

    /**
     * Clear the position
     */
    public final LegendOptions clearPosition()
    {
        clear( POSITION_KEY );
        return this;
    }

    /**
     * Set the distance to the plot edge
     */
    public final LegendOptions setMargin( double margin )
    {
        put( MARGIN_KEY, margin );
        return this;
    }

    /**
     * Set the distance to the plot edge
     */
    public final LegendOptions setMargin( double marginX, double marginY )
    {
        JsArrayNumber array = getMarginAsArray();
        if ( null == array )
        {
            array = JavaScriptObject.createArray().cast();
            put( MARGIN_KEY, array );
        }
        array.set( 0, marginX );
        array.set( 1, marginY );
        return this;
    }

    /**
     * @return the distance to the plot edge. The array can contains one value if the margin is applied to both x and y
     * axis or 2 values if the first one is applied to x axis and the second one to y axis.
     */
    public final Object getMargin()
    {
        return getObject( MARGIN_KEY );
    }

    /**
     * @return the distance to the plot edge. The array can contains one value if the margin is applied to both x and y
     * axis or 2 values if the first one is applied to x axis and the second one to y axis.
     */
    public final Double getMarginAsDouble()
    {
        Object margin = getMargin();
        return (Double) ( margin instanceof Double ? margin : null );
    }

    /**
     * @return the distance to the plot edge. The array can contains one value if the margin is applied to both x and y
     * axis or 2 values if the first one is applied to x axis and the second one to y axis.
     */
    public final JsArrayNumber getMarginAsArray()
    {
        Object margin = getMargin();
        return (JsArrayNumber) ( margin instanceof JavaScriptObject ? margin : null );
    }

    /**
     * Clear the margin
     */
    public final LegendOptions clearMargin()
    {
        clear( MARGIN_KEY );
        return this;
    }

    /**
     * Set the background color
     */
    public final LegendOptions setBackgroundColor( String cssColor )
    {
        put( BACKGROUND_COLOR_KEY, cssColor );
        return this;
    }

    /**
     * @return the background color
     */
    public final String getBackgroundColor()
    {
        return getString( BACKGROUND_COLOR_KEY );
    }

    /**
     * Clear the background color
     */
    public final LegendOptions clearBackgroundColor()
    {
        clear( BACKGROUND_COLOR_KEY );
        return this;
    }

    /**
     * Set the background opacity. Opacity range from 0.0 to 1.0.
     */
    public final LegendOptions setBackgroundOpacity( double opacity )
    {
        assert opacity >= 0 && opacity <= 1 : "opacity range from 0.0 to 1.0";

        put( BACKGROUND_OPACITY_KEY, opacity );
        return this;
    }

    /**
     * @return the background opacity
     */
    public final Double getBackgroundOpacity()
    {
        return getDouble( BACKGROUND_OPACITY_KEY );
    }

    /**
     * Clear the background opacity
     */
    public final LegendOptions clearBackgroundOpacity()
    {
        clear( BACKGROUND_OPACITY_KEY );
        return this;
    }

    /**
     * Set a custom container to put the legend table into. The "position" and "margin" etc. options will then be
     * ignored. Note that Flot will overwrite the contents of the container.
     */
    public final LegendOptions setContainer( Element container )
    {
        assert null != container : "container can't be null";

        String id = container.getId();
        if ( id == null || id.length() == 0 )
        {
            id = Document.get().createUniqueId();
            container.setId( id );
        }
        put( CONTAINER_KEY, "#" + id );
        return this;
    }

    /**
     * @return the custom container if it exists
     */
    public Element getContainer()
    {
        String containerId = getString( CONTAINER_KEY );
        if ( null == containerId || containerId.length() <= 1 || !containerId.startsWith( "#" ) )
        {
            return null;
        }
        return Document.get().getElementById( containerId.substring( 1 ) );
    }

    /**
     * Clear the container
     */
    public final LegendOptions clearContainer()
    {
        clear( CONTAINER_KEY );
        return this;
    }

    /**
     * Set the labelFormatter if you want to format the labels in some way, e.g. make them to links.
     */
    public final LegendOptions setLabelFormatter( LabelFormatter labelFormatter )
    {
        assert null != labelFormatter : "labelFormatter can't be null";

        setLabelFormatterNative( labelFormatter );
        return this;
    }

    private native void setLabelFormatterNative( LabelFormatter labelFormatter )
    /*-{
        this.labelFormatter = function(label, series) {
            return labelFormatter.@ca.nanometrics.gflot.client.options.LegendOptions.LabelFormatter::formatLabel(Ljava/lang/String;Lca/nanometrics/gflot/client/Series;)(label, series);
        };
    }-*/;

    /**
     * Clear the legend formatter
     */
    public final LegendOptions clearLabelFormatter()
    {
        clear( "labelFormatter" );
        return this;
    }
}
