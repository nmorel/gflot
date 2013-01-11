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

public class DefaultSeriesDataStrategy
    implements SeriesDataStrategy
{
    protected final SeriesData data;

    public DefaultSeriesDataStrategy()
    {
        this( SeriesData.create() );
    }

    public DefaultSeriesDataStrategy( SeriesData data )
    {
        this.data = data;
    }

    @Override
    public void add( DataPoint datapoint )
    {
        data.push( datapoint );
    }

    @Override
    public void clear()
    {
        data.clear();
    }

    @Override
    public SeriesData getData()
    {
        return data;
    }

    @Override
    public void setData( SeriesData newData )
    {
        // FIXME overlay voir pour le faire de maniere plus efficace. Avec splice par exemple
        data.clear();
        for ( int i = 0; i < newData.length(); i++ )
        {
            data.push( newData.get( i ) );
        }
    }

}
