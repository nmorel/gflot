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
package ca.nanometrics.gflot.client.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Wraps a JSONArray and provides write methods that accept Java primitives. This class is meant to be extended by
 * client classes
 *
 * @author AlexanderDeleon
 */
public class JSONArrayWrapper
    extends JSONWrapper
{
    private final JSONArray m_jsonArray;

    private int m_currentIndex;

    private static native void shiftJavaScriptArray( JavaScriptObject arrayObject )
    /*-{
        arrayObject.shift();
    }-*/;

    private static native void clearJavaScriptArray( JavaScriptObject arrayObject )
    /*-{
        arrayObject.length = 0;
    }-*/;

    protected JSONArrayWrapper()
    {
        m_jsonArray = new JSONArray();
        m_currentIndex = -1;
    }

    protected JSONArrayWrapper( JSONArrayWrapper array )
    {
        this();
        for ( int i = 0; i < array.size(); i++ )
        {
            set( i, array.get( i ) );
        }
    }

    protected JSONArrayWrapper( JSONArray array )
    {
        m_jsonArray = array;
        m_currentIndex = m_jsonArray.size() - 1;
    }

    protected void set( int index, String value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONString( value );
        }
        set( index, val );
    }

    protected void push( String value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONString( value );
        }
        set( ++m_currentIndex, val );
    }

    protected void pushAll( String[] values )
    {

        for ( int i = 0; i < values.length; i++ )
        {
            push( values[i] );
        }
    }

    protected void set( int index, Number value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONNumber( value.doubleValue() );
        }
        set( index, val );
    }

    protected void push( Number value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONNumber( value.doubleValue() );
        }
        set( ++m_currentIndex, val );
    }

    protected void pushAll( Number[] values )
    {
        for ( int i = 0; i < values.length; i++ )
        {
            push( values[i] );
        }
    }

    protected void set( int index, boolean value )
    {
        set( index, JSONBoolean.getInstance( value ) );
    }

    protected void push( boolean value )
    {
        set( ++m_currentIndex, JSONBoolean.getInstance( value ) );
    }

    protected void pushAll( boolean[] values )
    {
        for ( int i = 0; i < values.length; i++ )
        {
            push( values[i] );
        }
    }

    protected void set( int index, JSONWrapper value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = value.getWrappedObj();
        }
        set( index, val );
    }

    protected void push( JSONWrapper value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = value.getWrappedObj();
        }
        set( ++m_currentIndex, val );
    }

    protected void pushAll( JSONWrapper[] values )
    {
        for ( int i = 0; i < values.length; i++ )
        {
            push( values[i] );
        }
    }

    protected void set( int index, JSONValue value )
    {
        m_jsonArray.set( index, value );
        if ( index > m_currentIndex )
        {
            m_currentIndex = index;
        }
    }

    protected Double getDouble( int index )
    {
        JSONNumber num = getNumber( index );
        return num == null ? null : new Double( num.doubleValue() );
    }

    protected Integer getInteger( int index )
    {
        JSONNumber num = getNumber( index );
        return num == null ? null : new Integer( (int) num.doubleValue() );
    }

    protected String getString( int index )
    {
        JSONValue value = get( index );
        if ( value == null )
        {
            return null;
        }
        JSONString str = value.isString();
        return str == null ? null : str.stringValue();
    }

    protected JSONNumber getNumber( int index )
    {
        JSONValue val = get( index );
        if ( val == null )
        {
            return null;
        }
        return val.isNumber();
    }

    protected JSONArray getArray( int index )
    {
        JSONValue value = get( index );
        if ( value == null )
        {
            return null;
        }
        return value.isArray();
    }

    protected JSONObject getObject( int index )
    {
        JSONValue value = get( index );
        if ( value == null )
        {
            return null;
        }
        return value.isObject();
    }

    protected JSONValue get( int index )
    {
        return m_jsonArray.get( index );
    }

    protected int size()
    {
        return m_jsonArray.size();
    }

    protected JSONValue getWrappedObj()
    {
        return m_jsonArray;
    }

    protected void shift()
    {
        shiftJavaScriptArray( m_jsonArray.getJavaScriptObject() );
        m_currentIndex = m_jsonArray.size() - 1;
    }

    protected void clear()
    {
        clearJavaScriptArray( m_jsonArray.getJavaScriptObject() );
        m_currentIndex = -1;
    }

    protected JSONArrayWrapper _slice( int start, int end )
    {
        JSONArrayWrapper newArray = new JSONArrayWrapper();
        int i = 0;
        for ( int j = start; j <= end; j++ )
        {
            newArray.set( i++, get( j ) );
        }
        return newArray;
    }

    protected JSONArrayWrapper _slice( int start )
    {
        return _slice( start, size() - 1 );
    }

    public String toString()
    {
        return m_jsonArray.toString();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == this )
        {
            return true;
        }
        if ( obj instanceof JSONArrayWrapper )
        {
            return m_jsonArray.equals( ( (JSONArrayWrapper) obj ).m_jsonArray );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return m_jsonArray.hashCode();
    }
}
