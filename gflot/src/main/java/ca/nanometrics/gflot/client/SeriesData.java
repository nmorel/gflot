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

import com.google.gwt.core.client.JsArray;

/**
 * @author AlexanderDeleon
 */
public class SeriesData
    extends JsArray<DataPoint>
{
    public static final SeriesData create()
    {
        return createArray().cast();
    }

    /**
     * default constructor
     */
    protected SeriesData()
    {
        // empty
    }

    public final boolean isEmpty()
    {
        return length() == 0;
    }

    public final double getX( int index )
    {
        return get( index ).getX();
    }

    public final double getY( int index )
    {
        return get( index ).getY();
    }

    public final SeriesData slice( int start )
    {
        return slice( start, length() - 1 );
    }

    public final native SeriesData slice( int start, int end )
    /*-{
        return this.slice(start, end);
    }-*/;

    // FIXME overlay
    // public DataPoint[] getDatapoints()
    // {
    // List<DataPoint> list = new ArrayList<DataPoint>( size() );
    // for ( int i = 0; i < size(); i++ )
    // {
    // JSONArray array = getArray( i );
    // if ( array != null )
    // {
    // DataPoint datapoint = new DataPoint( array );
    // list.add( datapoint );
    // }
    // }
    // return list.toArray( new DataPoint[list.size()] );
    // }
    //
    // public DataPoint getDataPoint( int index )
    // {
    // JSONArray array = getArray( index );
    // if ( array == null )
    // {
    // return null;
    // }
    // else
    // {
    // return new DataPoint( array );
    // }
    // }

    public final void clear()
    {
        setLength( 0 );
    }
    //
    // protected void setData( SeriesData data )
    // {
    // DataPoint[] points = data.getDatapoints();
    // super.clear();
    // for ( DataPoint point : points )
    // {
    // super.push( point );
    // }
    // }
}
