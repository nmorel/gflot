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


/**
 * @author AlexanderDeleon
 */
public class LineSeriesOptions
    extends AbstractBasicSeriesOptions<LineSeriesOptions>
{
    /**
     * Creates a {@link LineSeriesOptions}
     */
    public static final LineSeriesOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String STEPS_KEY = "steps";

    protected LineSeriesOptions()
    {
    }

    /**
     * Set whether two adjacent data points are connected with a straight (possibly diagonal) line or with first a
     * horizontal and then a vertical line. Note that this transforms the data by adding extra points.
     */
    public final LineSeriesOptions setSteps( boolean steps )
    {
        put( STEPS_KEY, steps );
        return this;
    }

    /**
     * @return true if two adjacent data points are connected with a straight (possibly diagonal) line or with first a
     * horizontal and then a vertical line
     */
    public final Boolean getSteps()
    {
        return getBoolean( STEPS_KEY );
    }

    /**
     * Clear the steps option
     */
    public final LineSeriesOptions clearSteps()
    {
        clear( STEPS_KEY );
        return this;
    }

}
