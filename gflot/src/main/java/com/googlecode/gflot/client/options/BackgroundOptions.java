/*
 * Copyright (c) 2014 naBerTech
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
import com.googlecode.gflot.client.jsni.JsonObject;


/**
 * @author Igor Baldachini
 */
public class BackgroundOptions
    extends JsonObject
{
    /**
     * Creates a {@link BackgroundOptions}
     */
    public static final BackgroundOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String IMAGE_URL_KEY = "imageurl";
    private static final String ALPHA_KEY = "alpha";

    protected BackgroundOptions()
    {
    }

    /**
     * Set the background image url
     */
    public final BackgroundOptions setImageUrl( String url )
    {
        put( IMAGE_URL_KEY, url );
        return this;
    }

    /**
     * @return the background image url
     */
    public final String getImageUrl()
    {
        return getString( IMAGE_URL_KEY );
    }

    /**
     * Clear the background image url
     */
    public final BackgroundOptions clearImageUrl()
    {
        clear( IMAGE_URL_KEY );
        return this;
    }

    /**
     * Set background alpha channel
     */
    public final BackgroundOptions setAlpha( Double alpha)
    {
        put( ALPHA_KEY, alpha );
        return this;
    }

    /**
     * @return the background alpha channel
     */
    public final Double getAlpha()
    {
        return getDouble( ALPHA_KEY );
    }

    /**
     * Clear the background alpha channel
     */
    public final BackgroundOptions clearAlpha()
    {
        clear( ALPHA_KEY );
        return this;
    }
}
