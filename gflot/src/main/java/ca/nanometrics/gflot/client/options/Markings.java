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

import ca.nanometrics.gflot.client.util.JSONArrayWrapper;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * @author Alexander De Leon
 * @author Mohamed M. El-Kalioby
 */
public class Markings
    extends JSONArrayWrapper
{

    public Markings()
    {
        super();
    }

    Markings( JSONArray jsonArray )
    {
        super( jsonArray );
    }

    public Markings addMarking( Marking marking )
    {
        push( marking );
        return this;
    }

    public Markings addMarkings( Marking[] markings )
    {
        pushAll( markings );
        return this;
    }

    public Marking getMarking( int index )
    {
        if ( index >= size() )
        {
            return null;
        }
        JSONObject obj = getObject( index );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return new Marking( obj );
        }
    }

    public Marking[] getMarkings()
    {
        Marking[] markings = new Marking[size()];
        for ( int i = 0; i < size(); i++ )
        {
            markings[i] = new Marking( getObject( i ) );
        }
        return markings;
    }

}
