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

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNull;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Wraps a JSONObject and provides write methods that accept Java primitives. This class is meant to be extended by
 * client classes
 *
 * @author AlexanderDeleon
 */
public class JSONObjectWrapper
    extends JSONWrapper
{

    private final JSONObject m_jsonObj;

    protected JSONObjectWrapper()
    {
        m_jsonObj = new JSONObject();
    }

    protected JSONObjectWrapper( JSONObject jsonObj )
    {
        m_jsonObj = jsonObj;
    }

    protected void put( String key, String value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONString( value );
        }
        m_jsonObj.put( key, val );
    }

    protected void put( String key, Number value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = new JSONNumber( value.doubleValue() );
        }
        m_jsonObj.put( key, val );
    }

    protected void put( String key, boolean value )
    {
        m_jsonObj.put( key, JSONBoolean.getInstance( value ) );
    }

    protected void put( String key, JSONWrapper value )
    {
        JSONValue val = JSONNull.getInstance();
        if ( value != null )
        {
            val = value.getWrappedObj();
        }
        m_jsonObj.put( key, val );
    }

    protected Double getDouble( String key )
    {
        JSONNumber num = getNumber( key );
        return num == null ? null : new Double( num.doubleValue() );
    }

    protected Integer getInteger( String key )
    {
        JSONNumber num = getNumber( key );
        return num == null ? null : new Integer( (int) num.doubleValue() );
    }

    protected String getString( String key )
    {
        JSONValue value = get( key );
        if ( value == null )
        {
            return null;
        }
        JSONString str = value.isString();
        return str == null ? null : str.stringValue();
    }

    protected String[] getStringArray( String key )
    {
        JSONArray array = getArray( key );
        if ( array == null )
        {
            return null;
        }
        String[] result = new String[array.size()];
        for ( int i = 0; i < array.size(); i++ )
        {
            JSONString value = array.get( i ).isString();
            if ( null != value )
            {
                result[i] = value.stringValue();
            }
        }
        return result;
    }

    protected Boolean getBoolean( String key )
    {
        JSONValue value = get( key );
        if ( value == null )
        {
            return null;
        }
        JSONBoolean str = value.isBoolean();
        return str == null ? null : str.booleanValue();
    }

    protected JSONNumber getNumber( String key )
    {
        JSONValue val = get( key );
        if ( val == null )
        {
            return null;
        }
        return val.isNumber();
    }

    protected JSONArray getArray( String key )
    {
        JSONValue value = get( key );
        if ( value == null )
        {
            return null;
        }
        return value.isArray();
    }

    protected JSONObject getObject( String key )
    {
        JSONValue value = get( key );
        if ( value == null )
        {
            return null;
        }
        return value.isObject();
    }

    protected JSONValue get( String key )
    {
        return getWrappedObj().get( key );
    }

    protected JSONObject getWrappedObj()
    {
        return m_jsonObj;
    }

    public String toString()
    {
        return m_jsonObj.toString();
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == this )
        {
            return true;
        }
        if ( obj instanceof JSONObjectWrapper )
        {
            return m_jsonObj.equals( ( (JSONObjectWrapper) obj ).m_jsonObj );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return m_jsonObj.hashCode();
    }

}
