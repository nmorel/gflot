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

public class Zoom
    extends Navigate<Zoom>
{
    public static class Center
        extends JsonObject
    {
        public static final Center create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String LEFT_KEY = "left";
        private static final String TOP_KEY = "top";

        protected Center()
        {
        }

        public final Center setLeft( double left )
        {
            put( LEFT_KEY, left );
            return this;
        }

        public final Center setTop( double top )
        {
            put( TOP_KEY, top );
            return this;
        }
    }

    public static final Zoom create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String AMOUNT_KEY = "amount";
    private static final String CENTER_KEY = "center";

    protected Zoom()
    {
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Zoom setAmount( double amount )
    {
        put( AMOUNT_KEY, amount );
        return this;
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Zoom setCenter( Center center )
    {
        put( CENTER_KEY, center );
        return this;
    }
}
