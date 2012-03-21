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

import java.util.ArrayList;
import java.util.List;

import ca.nanometrics.gflot.client.util.JSONArrayWrapper;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;

/**
 * @author AlexanderDeleon
 */
public class SeriesData
    extends JSONArrayWrapper
{
    /**
     * default constructor
     */
    public SeriesData()
    {
        // empty
    }

    public SeriesData( DataPoint[] points )
    {
        for ( int i = 0; i < points.length; i++ )
        {
            add( points[i] );
        }
    }

    protected SeriesData( JSONArray array )
    {
        super( array );
    }

    public void add( double x, double y )
    {
        push( new DataPoint( x, y ) );
    }

    public void add( DataPoint dataPoint )
    {
        push( dataPoint );
    }

    public int size()
    {
        return super.size();
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public double getX( int index )
    {
        return ( (JSONNumber) ( (JSONArray) super.get( index ) ).get( 0 ) ).doubleValue();
    }

    public double getY( int index )
    {
        return ( (JSONNumber) ( (JSONArray) super.get( index ) ).get( 1 ) ).doubleValue();
    }

    public SeriesData slice( int start )
    {
        return slice( start, size() - 1 );
    }

    public SeriesData slice( int start, int end )
    {
        SeriesData newData = new SeriesData();
        for ( int j = start; j <= end; j++ )
        {
            newData.add( new DataPoint( getArray( j ) ) );
        }
        return newData;
    }

    public DataPoint[] getDatapoints()
    {
        List<DataPoint> list = new ArrayList<DataPoint>( size() );
        for ( int i = 0; i < size(); i++ )
        {
            JSONArray array = getArray( i );
            if ( array != null )
            {
                DataPoint datapoint = new DataPoint( array );
                list.add( datapoint );
            }
        }
        return list.toArray( new DataPoint[list.size()] );
    }

    public void clear()
    {
        super.clear();
    }

    protected void setData( SeriesData data )
    {
        DataPoint[] points = data.getDatapoints();
        super.clear();
        for ( DataPoint point : points )
        {
            super.push( point );
        }
    }
}
