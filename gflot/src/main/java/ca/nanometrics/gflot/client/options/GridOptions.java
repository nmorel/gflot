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

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

import ca.nanometrics.gflot.client.util.JSONHelper;
import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

/**
 * The grid is the thing with the axes and a number of ticks.
 *
 * @author Alexander De Leon
 */
public class GridOptions
    extends JSONObjectWrapper
{
    private static final String COLOR_KEY = "color";

    private static final String BACKGROUND_COLOR_KEY = "backgroundColor";

    private static final String BACKGROUND_COLORS_KEY = "colors";

    private static final String SHOW_KEY = "show";

    private static final String ABOVE_DATA_KEY = "aboveData";

    private static final String LABEL_MARGIN_KEY = "labelMargin";

    private static final String AXIS_MARGIN_KEY = "axisMargin";

    private static final String MARKINGS_KEY = "markings";

    private static final String MARKINGS_COLOR_KEY = "markingsColor";

    private static final String MARKINGS_LINE_WIDTH_KEY = "markingsLineWidth";

    private static final String BORDER_WIDTH_KEY = "borderWidth";

    private static final String BORDER_COLOR_KEY = "borderColor";

    private static final String MIN_BORDER_MARGIN_KEY = "minBorderMargin";

    private static final String CLICKABLE_KEY = "clickable";

    private static final String HOVERABLE_KEY = "hoverable";

    private static final String AUTO_HIGHLIGHT_KEY = "autoHighlight";

    private static final String MOUSE_ACTIVE_RADIUS_KEY = "mouseActiveRadius";

    public GridOptions()
    {
        super();
    }

    GridOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the color of the grid itself
     */
    public GridOptions setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /**
     * @return the color of the grid itself
     */
    public String getColor()
    {
        return getString( COLOR_KEY );
    }

    /**
     * Set the background color inside the grid area. null (default) means that the background is transparent.
     */
    public GridOptions setBackgroundColor( String color )
    {
        put( BACKGROUND_COLOR_KEY, color );
        return this;
    }

    /**
     * Set the background color inside the grid area as a gradient.
     */
    public GridOptions setBackgroundColor( String fromColor, String toColor )
    {
        put( BACKGROUND_COLOR_KEY, JSONHelper.wrapArrayIntoObject( BACKGROUND_COLORS_KEY, new String[] { fromColor, toColor } ) );
        return this;
    }

    /**
     * @return the background color inside the grid area. The array can contains one color or two colors if it's a
     * gradient
     */
    public String[] getBackgroundColor()
    {
        JSONValue value = get( BACKGROUND_COLOR_KEY );
        if ( value == null )
        {
            return null;
        }
        JSONString str = value.isString();
        if ( null != str )
        {
            return new String[] { str.stringValue() };
        }
        JSONObject obj = value.isObject();
        if ( null != obj )
        {
            JSONValue colors = obj.get( BACKGROUND_COLORS_KEY );
            JSONArray array = colors.isArray();
            if ( null != array )
            {
                return new String[] { array.get( 0 ).isString().stringValue(), array.get( 1 ).isString().stringValue() };
            }
        }
        return null;
    }

    /**
     * Set if the whole grid including tick labels should be shown.
     */
    public GridOptions setShow( boolean show )
    {
        put( SHOW_KEY, show );
        return this;
    }

    /**
     * @return true if the grid is visible
     */
    public Boolean getShow()
    {
        return getBoolean( SHOW_KEY );
    }

    /**
     * Set whether the grid is drawn above the data or below (below is default).
     */
    public GridOptions setAboveData( boolean aboveData )
    {
        put( ABOVE_DATA_KEY, aboveData );
        return this;
    }

    /**
     * @return whether the grid is drawn above the data or below
     */
    public Boolean getAboveData()
    {
        return getBoolean( ABOVE_DATA_KEY );
    }

    /**
     * Set the space in pixels between tick labels and axis line
     */
    public GridOptions setLabelMargin( int labelMargin )
    {
        put( LABEL_MARGIN_KEY, labelMargin );
        return this;
    }

    /**
     * @return the space in pixels between tick labels and axis line
     */
    public Integer getLabelMargin()
    {
        return getInteger( LABEL_MARGIN_KEY );
    }

    /**
     * Set the space in pixels between axes when there are two next to each other
     */
    public GridOptions setAxisMargin( int axisMargin )
    {
        put( AXIS_MARGIN_KEY, axisMargin );
        return this;
    }

    /**
     * @return the space in pixels between axes when there are two next to each other
     */
    public Integer getAxisMargin()
    {
        return getInteger( AXIS_MARGIN_KEY );
    }

    /**
     * Sets markings to the grid. It is used to draw simple lines and rectangular areas in the background of the plot.
     */
    public GridOptions setMarkings( Markings markings )
    {
        put( MARKINGS_KEY, markings );
        return this;
    }

    /**
     * @return the markings
     */
    public Markings getMarkings()
    {
        JSONArray array = getArray( MARKINGS_KEY );
        if ( null == array )
        {
            return null;
        }
        else
        {
            return new Markings( array );
        }
    }

    /**
     * Sets the colors of all markings. Specific color can be defined in Marking object
     */
    public GridOptions setMarkingsColor( String color )
    {
        put( MARKINGS_COLOR_KEY, color );
        return this;
    }

    /**
     * @return the markings color
     */
    public String getMarkingsColor()
    {
        return getString( MARKINGS_COLOR_KEY );
    }

    /**
     * Sets the line width of all markings. Specific line width can be defined in Marking object
     */
    public GridOptions setMarkingsLineWidth( int lineWidth )
    {
        put( MARKINGS_LINE_WIDTH_KEY, lineWidth );
        return this;
    }

    /**
     * @return the markings line width
     */
    public Integer getMarkingsLineWidth()
    {
        return getInteger( MARKINGS_LINE_WIDTH_KEY );
    }

    /**
     * Set the width of the border around the plot. Set it to 0 to disable the border.
     */
    public GridOptions setBorderWidth( int borderWidth )
    {
        assert borderWidth >= 0 : "borderWith must be positive";

        put( BORDER_WIDTH_KEY, borderWidth );
        return this;
    }

    /**
     * @return the width of the border around the plot
     */
    public Integer getBorderWidth()
    {
        return getInteger( BORDER_WIDTH_KEY );
    }

    /**
     * Set the color of the border. By default, it's the same color than the grid lines.
     */
    public GridOptions setBorderColor( String borderColor )
    {
        put( BORDER_COLOR_KEY, borderColor );
        return this;
    }

    /**
     * @return the color of the border
     */
    public String getBorderColor()
    {
        return getString( BORDER_COLOR_KEY );
    }

    /**
     * "minBorderMargin" controls the default minimum margin around the border - it's used to make sure that points
     * aren't accidentally clipped by the canvas edge so by default the value is computed from the point radius
     */
    public GridOptions setMinBorderMargin( int minBorderMargin )
    {
        assert minBorderMargin >= 0 : "minBorderMargin must be positive";

        put( MIN_BORDER_MARGIN_KEY, minBorderMargin );
        return this;
    }

    /**
     * @return the default minimum margin around the border
     */
    public Integer getMinBorderMargin()
    {
        return getInteger( MIN_BORDER_MARGIN_KEY );
    }

    /**
     * Set if the plot will listen for click events on the plot area. Add a PlotClickListener to the plot to listen to
     * the event.
     */
    public GridOptions setClickable( boolean clickable )
    {
        put( CLICKABLE_KEY, clickable );
        return this;
    }

    /**
     * @return true if the grid is clickable
     */
    public Boolean getClickable()
    {
        return getBoolean( CLICKABLE_KEY );
    }

    /**
     * Set if the plot will listen for mouse move events on the plot area. Add a PlotHoverListener to the plot to listen
     * to the event.
     */
    public GridOptions setHoverable( boolean hoverable )
    {
        put( HOVERABLE_KEY, hoverable );
        return this;
    }

    /**
     * @return true if the grid is hoverable
     */
    public Boolean getHoverable()
    {
        return getBoolean( HOVERABLE_KEY );
    }

    /**
     * Set if nearby data items are highlighted automatically (default is true)
     */
    public GridOptions setAutoHighlight( boolean autoHighlight )
    {
        put( AUTO_HIGHLIGHT_KEY, autoHighlight );
        return this;
    }

    /**
     * @return true if nearby data items are highlighted automatically (default is true)
     */
    public Boolean getAutoHighlight()
    {
        return getBoolean( AUTO_HIGHLIGHT_KEY );
    }

    /**
     * Set how far the mouse can be from an item and still activate it. If there are two or more points within this
     * radius, Flot chooses the closest item. For bars, the top-most bar (from the latest specified data series) is
     * chosen.
     */
    public GridOptions setMouseActiveRadius( int mouseActiveRadius )
    {
        put( MOUSE_ACTIVE_RADIUS_KEY, mouseActiveRadius );
        return this;
    }

    /**
     * @return the mouse active radius
     */
    public Integer getMouseActiveRadius()
    {
        return getInteger( MOUSE_ACTIVE_RADIUS_KEY );
    }

}
