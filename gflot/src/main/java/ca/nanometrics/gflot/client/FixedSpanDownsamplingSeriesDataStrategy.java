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
public class FixedSpanDownsamplingSeriesDataStrategy
    extends DownsamplingSeriesDataStrategy
{

    private final long maximumSpan;

    public FixedSpanDownsamplingSeriesDataStrategy( int capacity, long maximumSpan )
    {
        this( capacity, maximumSpan, SeriesData.create() );
    }

    public FixedSpanDownsamplingSeriesDataStrategy( int capacity, long maximumSpan, SeriesData seriesData )
    {
        super( capacity, seriesData );
        this.maximumSpan = maximumSpan;
    }

    @Override
    public void add( DataPoint dataPoint )
    {
        // The super class will observe the downsampling requirement
        super.add( dataPoint );

        // Now that the new point has been added, drop any point(s) from the
        // beginning that are outside the span requirement
        double lowerXbound = dataPoint.getX() - maximumSpan;
        boolean done = false;
        while ( !done )
        {
            double xValue = data.getX( 0 );
            if ( xValue < lowerXbound )
            {
                data.shift();
            }
            else
            {
                done = true;
            }
        }
    }

}
