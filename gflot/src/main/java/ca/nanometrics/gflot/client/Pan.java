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
package ca.nanometrics.gflot.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Pan
    extends Navigate<Pan>
{

    public static final Pan create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String LEFT_KEY = "left";
    private static final String TOP_KEY = "top";

    protected Pan()
    {
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Pan setLeft( double left )
    {
        put( LEFT_KEY, left );
        return this;
    }

    /**
     * Pan the plot to the top or bottom.
     * 
     * @param top Pixels to pan the plot to. Negative value to go up, positive value to go down
     */
    public final Pan setTop( double top )
    {
        put( TOP_KEY, top );
        return this;
    }
}
