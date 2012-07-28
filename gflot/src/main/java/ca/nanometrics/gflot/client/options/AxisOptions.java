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

import com.google.gwt.json.client.JSONObject;

/**
 * @author Alexander De Leon
 */
public class AxisOptions
    extends AbstractAxisOptions<AxisOptions>
{

    private static final String TICK_DECIMALS_KEY = "tickDecimals";

    public AxisOptions()
    {
        super();
    }

    AxisOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Set the tick interval size. If you set it to 2, you'll get ticks at 2, 4, 6, etc.
     */
    public AxisOptions setTickSize( double tickSize )
    {
        put( TICK_SIZE_KEY, new Double( tickSize ) );
        return this;
    }

    /**
     * @return the tick interval size
     */
    public Double getTickSize()
    {
        return getDouble( TICK_SIZE_KEY );
    }

    /**
     * Set that you don't want ticks at a size less than the specified one
     */
    public AxisOptions setMinTickSize( double minTickSize )
    {
        put( MIN_TICK_SIZE_KEY, new Double( minTickSize ) );
        return this;
    }

    /**
     * @return the minimal tick size
     */
    public Double getMinTickSize()
    {
        return getDouble( MIN_TICK_SIZE_KEY );
    }

    /**
     * Set the number of decimals to display (default is auto-detected).
     */
    public AxisOptions setTickDecimals( double tickDecimals )
    {
        put( TICK_DECIMALS_KEY, new Double( tickDecimals ) );
        return this;
    }

    /**
     * @return the number of decimals to display
     */
    public Double getTickDecimals()
    {
        return getDouble( TICK_DECIMALS_KEY );
    }

    /**
     * Clear the number of decimals to display
     */
    public AxisOptions clearTickDecimals()
    {
        clear( TICK_DECIMALS_KEY );
        return this;
    }
}
