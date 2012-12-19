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
package ca.nanometrics.gflot.client;

import com.google.gwt.core.client.JsArrayMixed;

/**
 * @author AlexanderDeleon
 */
public class DataPoint
    extends JsArrayMixed
{
    /**
     * Creates an empty {@link DataPoint}
     */
    public static final DataPoint create()
    {
        return createArray().cast();
    }

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     * 
     * @param x Coordinate for x axis
     * @param y Coordinate for y axis
     */
    public static final native DataPoint of( double x, double y )
    /*-{
        return [ x, y ];
    }-*/;

    /**
     * Creates a {@link DataPoint} with the specified coordinates.
     * 
     * @param x Coordinate for x axis
     * @param y Coordinate for y axis
     * @param bottomY Bottom of the filled area/bar for filled lines and bars. Default to 0.
     */
    public static final native DataPoint of( double x, double y, double bottomY )
    /*-{
        return [ x, y, bottomY ];
    }-*/;

    protected DataPoint()
    {
    }

    public final DataPoint setX( double x )
    {
        set( 0, x );
        return this;
    }

    public final DataPoint setY( double y )
    {
        set( 1, y );
        return this;
    }

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

}
