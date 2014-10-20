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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author AlexanderDeleon
 */
public class SeriesData
extends JavaScriptObject
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

	/**
	 * Gets the number of datapoint.
	 *
	 * @return the number of datapoint
	 */
	public final native int length()
	/*-{
        return this.length;
    }-*/;

	/**
	 * Reset the length of the array.
	 *
	 * @param newLength the new length of the array
	 */
	public final native void setLength( int newLength ) /*-{
        this.length = newLength;
    }-*/;

	/**
	 * Gets the datapoint at a given index.
	 *
	 * @param index the index to be retrieved
	 * @return the datapoint at the given index, or <code>null</code> if none exists
	 */
	public final native DataPoint get( int index )
	/*-{
        return this[index];
    }-*/;

	/**
	 * Sets the object value at a given index. If the index is out of bounds, the value will still be set. The array's
	 * length will be updated to encompass the bounds implied by the added object.
	 *
	 * @param index the index to be set
	 * @param value the object to be stored
	 */
	final native void set( int index, DataPoint value )
	/*-{
        this[index] = value;
    }-*/;

	/**
	 * Pushes the given value onto the end of the array.
	 */
	public final native void push( DataPoint value )
	/*-{
        this[this.length] = value;
    }-*/;

	/**
	 * Shifts the first value off the array.
	 *
	 * @return the shifted value
	 */
	public final native DataPoint shift()
	/*-{
        return this.shift();
    }-*/;

	/**
	 * Shifts a value onto the beginning of the array.
	 *
	 * @param value the value to the stored
	 */
	public final native void unshift( DataPoint value )
	/*-{
        this.unshift(value);
    }-*/;

	public final boolean isEmpty()
	{
		return length() == 0;
	}

	public final double getX( final int index )
	{
		return get( index ).getX();
	}

	public final double getY( final int index )
	{
		return get( index ).getY();
	}

	/**
	 * Returns a shallow copy of a portion of an array.
	 *
	 * @param begin Zero-based index at which to begin extraction.
	 * As a negative index, begin indicates an offset from the end of the sequence. slice(-2) extracts the
	 * second-to-last element and the last element in the sequence.
	 * @return a shallow copy of a portion of an array
	 */
	public final native SeriesData slice( int begin )
	/*-{
        return this.slice(begin);
    }-*/;

	/**
	 * Returns a shallow copy of a portion of an array.
	 *
	 * @param begin Zero-based index at which to begin extraction.
	 * As a negative index, begin indicates an offset from the end of the sequence. slice(-2) extracts the
	 * second-to-last element and the last element in the sequence.
	 * @param end Zero-based index at which to end extraction. slice extracts up to but not including end.
	 * slice(1,4) extracts the second element through the fourth element (elements indexed 1, 2, and 3).
	 * As a negative index, end indicates an offset from the end of the sequence. slice(2,
	 * -1) extracts the third element through the second-to-last element in the sequence.
	 * @return a shallow copy of a portion of an array
	 */
	public final native SeriesData slice( int begin, int end )
	/*-{
        return this.slice(begin, end);
    }-*/;

	public final void clear()
	{
		setLength( 0 );
	}

	public final void setData( final SeriesData data )
	{
		clear();
		for ( int i = 0; i < data.length(); i++ )
		{
			push( data.get( i ) );
		}
	}

}
