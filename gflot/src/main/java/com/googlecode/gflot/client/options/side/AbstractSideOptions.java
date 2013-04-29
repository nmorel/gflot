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

package com.googlecode.gflot.client.options.side;

import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * @author Nicolas Morel
 */
public abstract class AbstractSideOptions<T extends AbstractSideOptions<?>>
    extends JsonObject
{
    protected static final String TOP_KEY = "top";
    protected static final String RIGHT_KEY = "right";
    protected static final String BOTTOM_KEY = "bottom";
    protected static final String LEFT_KEY = "left";

    protected AbstractSideOptions(){}

    /**
     * Clear the top option
     */
    public final T clearTop()
    {
        clear( TOP_KEY );
        return (T) this;
    }

    /**
     * Clear the right option
     */
    public final T clearRight()
    {
        clear( RIGHT_KEY );
        return (T) this;
    }

    /**
     * Clear the bottom option
     */
    public final T clearBottom()
    {
        clear( BOTTOM_KEY );
        return (T) this;
    }

    /**
     * Clear the left option
     */
    public final T clearLeft()
    {
        clear( LEFT_KEY );
        return (T) this;
    }
}
