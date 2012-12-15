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
package ca.nanometrics.gflot.client;

/**
 * @author Alexander De Leon
 */
public final class PlotModelStrategy
{

    /**
     * A plot model strategy that allows unlimited amount of datapoints.
     */
    public static SeriesDataStrategy defaultStrategy()
    {
        return new DefaultSeriesDataStrategy();
    }

    public static SeriesDataStrategy downSamplingStrategy( final int capacity )
    {
        return downSamplingStrategy( capacity, 0 );
    }

    public static SeriesDataStrategy downSamplingStrategy( final int capacity, final long maximumXValueSpan )
    {
        if ( maximumXValueSpan <= 0 )
        {
            return new DownsamplingSeriesDataStrategy( capacity );
        }
        return new FixedSpanDownsamplingSeriesDataStrategy( capacity, maximumXValueSpan );
    }

    public static SeriesDataStrategy slidingWindowStrategy( final int capacity, final long maximumXValueSpan )
    {
        if ( maximumXValueSpan <= 0 )
        {
            return new FixedSizeSeriesDataStrategy( capacity );
        }
        else
        {
            return new FixedSpanFixedSizeSeriesDataStrategy( capacity, maximumXValueSpan );
        }
    }

    public static SeriesDataStrategy slidingWindowStrategy( final int capacity )
    {
        return slidingWindowStrategy( capacity, 0 );
    }

}
