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
 * @author Mohamed M. El-Kalioby
 * @since Septmeber 21, 2009
 */
public class Range
    extends JsonObject
{
    /**
     * Creates a {@link Range}
     */
    public static final Range create()
    {
        return JavaScriptObject.createObject().cast();
    }

    /**
     * Creates a {@link Range} with specified range
     */
    public static final Range of( double from, double to )
    {
        Range range = JavaScriptObject.createObject().cast();
        range.setFrom( from );
        range.setTo( to );
        return range;
    }

    private static final String FROM_KEY = "from";
    private static final String TO_KEY = "to";

    protected Range()
    {
    }

    public final Range setFrom( double from )
    {
        put( FROM_KEY, from );
        return this;
    }

    public final Double getFrom()
    {
        return getDouble( FROM_KEY );
    }

    public final Range setTo( double to )
    {
        put( TO_KEY, to );
        return this;
    }

    public final Double getTo()
    {
        return getDouble( TO_KEY );
    }

}
