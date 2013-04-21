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

/**
 * @author Nicolas Morel
 */
public class IntegerSideOptions extends AbstractSideOptions<IntegerSideOptions>
{

    /**
     * Creates a {@link IntegerSideOptions}
     */
    public static final IntegerSideOptions create()
    {
        return createObject().cast();
    }

    /**
     * Creates a {@link IntegerSideOptions}
     */
    public static final IntegerSideOptions of( int top, int right, int bottom, int left )
    {
        IntegerSideOptions margin = IntegerSideOptions.create();
        margin.setTop( top );
        margin.setRight( right );
        margin.setBottom( bottom );
        margin.setLeft( left );
        return margin;
    }

    protected IntegerSideOptions()
    {
    }

    /**
     * Set the top option
     */
    public final IntegerSideOptions setTop( int top )
    {
        put( TOP_KEY, top );
        return this;
    }

    /**
     * @return the top option
     */
    public final Integer getTop()
    {
        return getInteger( TOP_KEY );
    }

    /**
     * Set the right option
     */
    public final IntegerSideOptions setRight( int right )
    {
        put( RIGHT_KEY, right );
        return this;
    }

    /**
     * @return the right option
     */
    public final Integer getRight()
    {
        return getInteger( RIGHT_KEY );
    }

    /**
     * Set the bottom option
     */
    public final IntegerSideOptions setBottom( int bottom )
    {
        put( BOTTOM_KEY, bottom );
        return this;
    }

    /**
     * @return the bottom option
     */
    public final Integer getBottom()
    {
        return getInteger( BOTTOM_KEY );
    }

    /**
     * Set the left option
     */
    public final IntegerSideOptions setLeft( int left )
    {
        put( LEFT_KEY, left );
        return this;
    }

    /**
     * @return the left option
     */
    public final Integer getLeft()
    {
        return getInteger( LEFT_KEY );
    }
}
