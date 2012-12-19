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
public class FixedSizeSeriesDataStrategy
    extends DefaultSeriesDataStrategy
{

    private final int capacity;

    public FixedSizeSeriesDataStrategy( int capacity )
    {
        this( capacity, SeriesData.create() );
    }

    public FixedSizeSeriesDataStrategy( int capacity, SeriesData data )
    {
        super( data );
        this.capacity = capacity;
    }

    @Override
    public void add( DataPoint dataPoint )
    {
        int currentSize = data.length();
        if ( currentSize + 1 > capacity )
        {
            // TODO overlay doit y avoir mieux
            // Shift all of the values down one position
            shiftDown();
            data.set( currentSize - 1, dataPoint );
        }
        else
        {
            data.push( dataPoint );
        }
    }

    private final void shiftDown()
    {
        int currentSize = data.length();
        for ( int i = 0; i < currentSize - 1; i++ )
        {
            data.set( i, data.get( i + 1 ) );
        }
    }
}
