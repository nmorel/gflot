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

/**
 * Model of a plot axis.
 * 
 * @author David Easton
 */
public class Axis
    extends JsonObject
{
    /**
     * Creates a {@link Axis}
     *
     * @return a {@link Axis}
     */
    public static Axis create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String MIN = "min";
    private static final String MAX = "max";
    private static final String TICK_DECIMALS = "tickDecimals";
    private static final String TICK_SIZE = "tickSize";

    protected Axis()
    {
    }

    public final Double getMinimumValue()
    {
        return getDouble( MIN );
    }

    public final Double getMaximumValue()
    {
        return getDouble( MAX );
    }

    public final Integer getTickDecimals()
    {
        return getInteger( TICK_DECIMALS );
    }

    public final Integer getTickSize()
    {
        return getInteger( TICK_SIZE );
    }
}
