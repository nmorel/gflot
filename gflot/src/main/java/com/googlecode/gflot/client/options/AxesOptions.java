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

/**
 * Class to ease the manipulation of axes options
 * 
 * @author Nicolas Morel
 */
public class AxesOptions
    extends JsArray<AbstractAxisOptions<?>>
{
    /**
     * Creates a {@link AxesOptions}
     */
    public static final AxesOptions create()
    {
        return JavaScriptObject.createArray().cast();
    }

    protected AxesOptions()
    {
    }

    /**
     * Add an axis options
     */
    public final AxesOptions addAxisOptions( AbstractAxisOptions<?> axis )
    {
        push( axis );
        return this;
    }

    /**
     * Return axis options at given index, starting at 1
     */
    public final AbstractAxisOptions<?> getAxisOptions( int axisNumber )
    {
        if ( axisNumber > length() )
        {
            return null;
        }
        return get( axisNumber - 1 );
    }

}
