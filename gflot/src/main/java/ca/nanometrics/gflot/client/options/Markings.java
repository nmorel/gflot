/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.options;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * @author Alexander De Leon
 * @author Mohamed M. El-Kalioby
 */
public class Markings
    extends JsArray<Marking>
{
    /**
     * Creates a {@link Markings}
     */
    public static final Markings create()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected Markings()
    {
    }

    public final Markings addMarking( Marking marking )
    {
        push( marking );
        return this;
    }

    public final Markings addMarkings( Marking... markings )
    {
        if ( null != markings )
        {
            for ( Marking marking : markings )
            {
                push( marking );
            }
        }
        return this;
    }

    public final Marking getMarking( int index )
    {
        if ( index >= length() )
        {
            return null;
        }
        return get( index );
    }

}
