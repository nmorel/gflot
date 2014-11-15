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

import com.google.gwt.core.client.JsArrayMixed;
import com.googlecode.gflot.client.jsni.JsArrayUtils;

/**
 * @author AlexanderDeleon
 */
public class DataPoint
    extends JsArrayMixed
{
    /**
     * Creates an empty {@link DataPoint}
     *
     * @return an empty {@link DataPoint}
     */
    public static DataPoint create()
    {
        return createArray().cast();
    }

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     *
     * @param x Coordinate for x axis
     * @param y Coordinate for y axis
     *
     * @return a {@link DataPoint} with the specified coordinates.
     */
    public static native DataPoint of( double x, double y )
    /*-{
        return [ x, y ];
    }-*/;

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     *
     * @param x Coordinate for x axis
     * @param y Coordinate for y axis
     * @param third Third coordinate. Can be the bottom of the filled area/bar for filled lines and bars. Or the xerr, yerr value for the error bars.
     *
     * @return a {@link DataPoint} with the specified coordinates.
     */
    public static native DataPoint of( double x, double y, double third )
    /*-{
        return [ x, y, third ];
    }-*/;

    /**
     * Creates a {@link DataPoint}. This method uses {@link JsArrayUtils#readOnlyJsArray(double[])} so be careful of the differences between dev and production mode.
     *
     * @param array source array
     *
     * @return JS array, which may be a copy or an alias of the input array
     */
    public static DataPoint of( double... array )
    {
        return JsArrayUtils.readOnlyJsArray( array ).cast();
    }

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     *
     * @param x Category for x axis
     * @param y Coordinate for y axis
     *
     * @return a {@link DataPoint} with the specified coordinates
     */
    public static native DataPoint of( String x, double y )
    /*-{
        return [ x, y ];
    }-*/;

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     *
     * @param x Coordinate for x axis
     * @param y Category for y axis
     *
     * @return a {@link DataPoint} with the specified coordinates.
     */
    public static native DataPoint of( double x, String y )
    /*-{
        return [ x, y ];
    }-*/;

    protected DataPoint()
    {
    }

    /**
     * @param x the x coordinate
     *
     * @return the {@link DataPoint}
     */
    public final DataPoint setX( double x )
    {
        set( 0, x );
        return this;
    }

    /**
     * @param y the y coordinate
     *
     * @return the {@link DataPoint}
     */
    public final DataPoint setY( double y )
    {
        set( 1, y );
        return this;
    }

    /**
     * @param bottomY the bottom y coordinate
     *
     * @return the {@link DataPoint}
     */
    public final DataPoint setBottomY( double bottomY )
    {
        set( 2, bottomY );
        return this;
    }

    public final double getX()
    {
        return getNumber( 0 );
    }

    public final double getY()
    {
        return getNumber( 1 );
    }

    public final double getBottomY()
    {
        return getNumber( 2 );
    }

    public final native String stringify()
    /*-{
        return JSON.stringify(this);
    }-*/;

}
