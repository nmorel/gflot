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

    /**
     * Set the color of the grid itself
     */
    public GridOptions setColor( String color )
    {
        put( "color", color );
        return this;
    }

    /**
     * Set the background color inside the grid area. null (default) means that the background is transparent.
     */
    public GridOptions setBackgroundColor( String color )
    {
        put( "backgroundColor", color );
        return this;
    }

    /**
     * Set the background color inside the grid area as a gradient.
     */
    public GridOptions setBackgroundColor( String fromColor, String toColor )
    {
        put( "backgroundColor", JSONHelper.wrapArrayIntoObject( "colors", new String[] { fromColor, toColor } ) );
        return this;
    }

    /**
     * Set if the whole grid including tick labels should be shown.
     */
    public GridOptions setShow( boolean show )
    {
        put( "show", show );
        return this;
    }

    /**
     * Set whether the grid is drawn above the data or below (below is default).
     */
    public GridOptions setAboveData( boolean aboveData )
    {
        put( "aboveData", aboveData );
        return this;
    }

    /**
     * Set the space in pixels between tick labels and axis line
     */
    public GridOptions setLabelMargin( int labelMargin )
    {
        put( "labelMargin", labelMargin );
        return this;
    }

    /**
     * Set the space in pixels between axes when there are two next to each other
     */
    public GridOptions setAxisMargin( int axisMargin )
    {
        put( "axisMargin", axisMargin );
        return this;
    }

    public GridOptions setMarkings( Markings markings )
    {
        put( "markings", markings );
        return this;
    }

    /**
     * Set the width of the border around the plot. Set it to 0 to disable the border.
     */
    public GridOptions setBorderWidth( int borderWidth )
    {
        assert borderWidth >= 0 : "borderWith must be positive";

        put( "borderWidth", borderWidth );
        return this;
    }

    /**
     * Set the color of the border. By default, it's the same color than the grid lines.
     */
    public GridOptions setBorderColor( String borderColor )
    {
        put( "borderColor", borderColor );
        return this;
    }

    /**
     * "minBorderMargin" controls the default minimum margin around the border - it's used to make sure that points
     * aren't accidentally clipped by the canvas edge so by default the value is computed from the point radius
     */
    public GridOptions setMinBorderMargin( int minBorderMargin )
    {
        assert minBorderMargin >= 0 : "minBorderMargin must be positive";

        put( "minBorderMargin", minBorderMargin );
        return this;
    }

    /**
     * Set if the plot will listen for click events on the plot area. Add a PlotClickListener to the plot to listen to
     * the event.
     */
    public GridOptions setClickable( boolean clickable )
    {
        put( "clickable", clickable );
        return this;
    }

    /**
     * Set if the plot will listen for mouse move events on the plot area. Add a PlotHoverListener to the plot to listen
     * to the event.
     */
    public GridOptions setHoverable( boolean hoverable )
    {
        put( "hoverable", hoverable );
        return this;
    }

    /**
     * Set if nearby data items are highlighted automatically (default is true)
     */
    public GridOptions setAutoHighlight( boolean autoHighlight )
    {
        put( "autoHighlight", autoHighlight );
        return this;
    }

    /**
     * Set how far the mouse can be from an item and still activate it. If there are two or more points within this
     * radius, Flot chooses the closest item. For bars, the top-most bar (from the latest specified data series) is
     * chosen.
     */
    public GridOptions setMouseActiveRadius( int mouseActiveRadius )
    {
        put( "mouseActiveRadius", new Integer( mouseActiveRadius ) );
        return this;
    }

}
