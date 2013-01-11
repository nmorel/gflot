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

/**
 * @author Nicolas Morel
 */
public class ImageDataPoint
    extends DataPoint
{
    /**
     * Creates an {@link ImageDataPoint} with the specified url and coordinates.
     * 
     * @param url Url of the image
     * @param xTopLeftCorner X coordinate for top left corner
     * @param yTopLeftCorner Y coordinate for top left corner
     * @param xBottomRightCorner X coordinate for bottom right corner
     * @param yBottomRightCorner Y coordinate for bottom right corner
     */
    public static final native ImageDataPoint of( String url, double xTopLeftCorner, double yTopLeftCorner,
                                                  double xBottomRightCorner, double yBottomRightCorner )
    /*-{
        return [ url, xTopLeftCorner, yTopLeftCorner, xBottomRightCorner,
                yBottomRightCorner ];
    }-*/;

    protected ImageDataPoint()
    {
    }

    public final String getUrl()
    {
        return getString( 0 );
    }

    public final ImageDataPoint setUrl( String url )
    {
        set( 0, url );
        return this;
    }

    public final double getXTopLeftCorner()
    {
        return getNumber( 1 );
    }

    public final ImageDataPoint setXTopLeftCorner( double xTopLeftCorner )
    {
        set( 1, xTopLeftCorner );
        return this;
    }

    public final double getYTopLeftCorner()
    {
        return getNumber( 2 );
    }

    public final ImageDataPoint setYTopLeftCorner( double yTopLeftCorner )
    {
        set( 2, yTopLeftCorner );
        return this;
    }

    public final double getXBottomRightCorner()
    {
        return getNumber( 3 );
    }

    public final ImageDataPoint setXBottomRightCorner( double xBottomRightCorner )
    {
        set( 3, xBottomRightCorner );
        return this;
    }

    public final double getYBottomRightCorner()
    {
        return getNumber( 4 );
    }

    public final ImageDataPoint setYBottomRightCorner( double yBottomRightCorner )
    {
        set( 4, yBottomRightCorner );
        return this;
    }
}
