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
package com.googlecode.gflot.client;


import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.gflot.client.jsni.JsonObject;
import com.googlecode.gflot.client.options.Range;

/**
 * @author Nicolas Morel
 */
public class PlotSelectionArea
    extends JsonObject
{
    /**
     * Creates a {@link PlotSelectionArea}
     *
     * @return a {@link PlotSelectionArea}
     */
    public static PlotSelectionArea create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String X_AXIS_KEY_PREFIX = "x";
    private static final String X_AXIS_KEY_SUFFIX = "axis";
    private static final String Y_AXIS_KEY_PREFIX = "y";
    private static final String Y_AXIS_KEY_SUFFIX = "axis";

    protected PlotSelectionArea()
    {
    }

    public final PlotSelectionArea setX( Range xRange )
    {
        return setX( xRange, 1 );
    }

    public final PlotSelectionArea setX( Range xRange, int xAxisNumber )
    {
        if ( xAxisNumber == 1 )
        {
            put( X_AXIS_KEY_PREFIX + X_AXIS_KEY_SUFFIX, xRange );
        }
        else
        {
            put( X_AXIS_KEY_PREFIX + xAxisNumber + X_AXIS_KEY_SUFFIX, xRange );
        }
        return this;
    }

    /**
     * @return the range for x axis
     */
    public final Range getX()
    {
        return getX( 1 );
    }

    /**
     * @param xAxisNumber the x axis number
     *
     * @return the range for x axis
     */
    public final Range getX( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        if ( xAxisNumber == 1 )
        {
            return getJsObject( X_AXIS_KEY_PREFIX + X_AXIS_KEY_SUFFIX );
        }
        else
        {
            return getJsObject( X_AXIS_KEY_PREFIX + xAxisNumber + X_AXIS_KEY_SUFFIX );
        }
    }

    public final PlotSelectionArea setY( Range yRange )
    {
        return setY( yRange, 1 );
    }

    public final PlotSelectionArea setY( Range yRange, int yAxisNumber )
    {
        if ( yAxisNumber == 1 )
        {
            put( Y_AXIS_KEY_PREFIX + Y_AXIS_KEY_SUFFIX, yRange );
        }
        else
        {
            put( Y_AXIS_KEY_PREFIX + yAxisNumber + Y_AXIS_KEY_SUFFIX, yRange );
        }
        return this;
    }

    /**
     * @return the range for y axis
     */
    public final Range getY()
    {
        return getY( 1 );
    }

    /**
     * @param yAxisNumber the y axis number
     *
     * @return the range for y axis
     */
    public final Range getY( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        if ( yAxisNumber == 1 )
        {
            return getJsObject( Y_AXIS_KEY_PREFIX + Y_AXIS_KEY_SUFFIX );
        }
        else
        {
            return getJsObject( Y_AXIS_KEY_PREFIX + yAxisNumber + Y_AXIS_KEY_SUFFIX );
        }
    }

}
