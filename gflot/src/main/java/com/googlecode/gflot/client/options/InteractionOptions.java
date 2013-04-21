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
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * @author Nicolas Morel
 */
public class InteractionOptions
    extends JsonObject
{
    private static final String REDRAW_OVERLAY_INTERVAL_KEY = "redrawOverlayInterval";

    /**
     * Creates a {@link com.googlecode.gflot.client.options.InteractionOptions}
     */
    public static final InteractionOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected InteractionOptions()
    {
    }

    /**
     * Set the maximum time to delay a redraw
     * of interactive things (this works as a rate limiting device). The
     * default is capped to 60 frames per second. You can set it to -1 to
     * disable the rate limiting.
     */
    public final InteractionOptions setRedrawOverlayInterval( double redrawOverlayInterval )
    {
        put( REDRAW_OVERLAY_INTERVAL_KEY, redrawOverlayInterval );
        return this;
    }

    /**
     * @return the redraw overlay interval
     */
    public final Double getRedrawOverlayInterval()
    {
        return getDouble( REDRAW_OVERLAY_INTERVAL_KEY );
    }

    /**
     * Clear the redraw overlay interval
     */
    public final InteractionOptions clearRedrawOverlayInterval()
    {
        clear( REDRAW_OVERLAY_INTERVAL_KEY );
        return this;
    }

}
