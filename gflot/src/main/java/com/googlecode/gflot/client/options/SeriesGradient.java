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
package com.googlecode.gflot.client.options;


import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.googlecode.gflot.client.jsni.JsonObject;

public class SeriesGradient
    extends JsonObject
{
    public static class Gradient
        extends JsonObject
    {
        /**
         * Creates a {@link Gradient} with specified gradient
         */
        public static final Gradient of( Double opacity, Double brightness )
        {
            Gradient gradient = JavaScriptObject.createObject().cast();
            if ( null != opacity )
            {
                gradient.put( OPACITY_KEY, opacity );
            }
            if ( null != brightness )
            {
                gradient.put( BRIGHTNESS_KEY, brightness );
            }
            return gradient;
        }

        protected Gradient()
        {
        }

        public final Double getOpacity()
        {
            return getDouble( OPACITY_KEY );
        }

        public final Double getBrightness()
        {
            return getDouble( BRIGHTNESS_KEY );
        }
    }

    /**
     * Creates a {@link SeriesGradient} with specified gradient
     */
    public static SeriesGradient of( Double fromOpacity, Double fromBrightness, Double toOpacity, Double toBrightness )
    {
        SeriesGradient gradient = JavaScriptObject.createObject().cast();
        JsArray<Gradient> array = JavaScriptObject.createArray().cast();
        array.push( Gradient.of( fromOpacity, fromBrightness ) );
        array.push( Gradient.of( toOpacity, toBrightness ) );
        gradient.put( FILL_COLOR_COLORS_KEY, array );
        return gradient;
    }

    private static final String FILL_COLOR_COLORS_KEY = "colors";
    private static final String OPACITY_KEY = "opacity";
    private static final String BRIGHTNESS_KEY = "brightness";

    protected SeriesGradient()
    {
    }

    public final Gradient getFrom()
    {
        return getArray( FILL_COLOR_COLORS_KEY ).getObject( 0 );
    }

    public final Gradient getTo()
    {
        return getArray( FILL_COLOR_COLORS_KEY ).getObject( 1 );
    }
}
