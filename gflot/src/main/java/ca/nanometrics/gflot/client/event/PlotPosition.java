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
package ca.nanometrics.gflot.client.event;

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * A position in a plot. If the plot has dual axis x2 and/or y2 will be not null.
 * 
 * @author Alexander De Leon
 */
public class PlotPosition
    extends JsonObject
{
    public static final PlotPosition create()
    {
        return JavaScriptObject.createObject().cast();
    }

    public static final PlotPosition of( double x, double y )
    {
        PlotPosition plotPosition = create();
        plotPosition.setX( x );
        plotPosition.setY( y );
        return plotPosition;
    }

    private static final String X = "x";
    private static final String Y = "y";
    private static final String PAGE_X = "pageX";
    private static final String PAGE_Y = "pageY";

    protected PlotPosition()
    {
    }

    /**
     * @return the x axis coordinate for the first x axis
     */
    public final Double getX()
    {
        return getX( 1 );
    }

    /**
     * @param xAxisNumber number of the axis, starting at 1
     * @return the x axis coordinate for the x axis number xAxisNumber
     */
    public final Double getX( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        return getDouble( X + xAxisNumber );
    }

    /**
     * Set the x axis coordinate for the first x axis
     * 
     * @param x x coordinate
     */
    public final PlotPosition setX( double x )
    {
        return setX( x, 1 );
    }

    /**
     * Set the x axis coordinate for the xAxisNumber x axis
     * 
     * @param x x coordinate
     * @param xAxisNumber number of the axis, starting at 1
     */
    public final PlotPosition setX( double x, int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        put( X + xAxisNumber, x );
        return this;
    }

    /**
     * @return the y axis coordinate for the first y axis
     */
    public final Double getY()
    {
        return getY( 1 );
    }

    /**
     * @param yAxisNumber number of the axis, starting at 1
     * @return they axis coordinate for the y axis number yAxisNumber
     */
    public final Double getY( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        return getDouble( Y + yAxisNumber );
    }

    /**
     * Set the y axis coordinate for the first y axis
     * 
     * @param y y coordinate
     */
    public final PlotPosition setY( double y )
    {
        return setY( y, 1 );
    }

    /**
     * Set the y axis coordinate for the yAxisNumber y axis
     * 
     * @param y y coordinate
     * @param yAxisNumber number of the axis, starting at 1
     */
    public final PlotPosition setY( double y, int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        put( Y + yAxisNumber, y );
        return this;
    }

    /**
     * @return the global screen x coordinates
     */
    public final Integer getPageX()
    {
        return getInteger( PAGE_X );
    }

    /**
     * @return the global screen y coordinates
     */
    public final Integer getPageY()
    {
        return getInteger( PAGE_Y );
    }

}
