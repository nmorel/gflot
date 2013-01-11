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
package com.googlecode.gflot.client.jsni;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

public class JsonObject
    extends JavaScriptObject
{
    protected JsonObject()
    {
    }

    public final native boolean hasKey( String key )
    /*-{
        return key in this;
    }-*/;

    public final native void clear( String key )
    /*-{
        delete this[key];
    }-*/;

    public final native void put( String key, String value )
    /*-{
        this[key] = value;
    }-*/;

    public final native void put( String key, int value )
    /*-{
        this[key] = value;
    }-*/;

    public final native void put( String key, double value )
    /*-{
        this[key] = value;
    }-*/;

    public final native void put( String key, boolean value )
    /*-{
        this[key] = value;
    }-*/;

    public final native void put( String key, JavaScriptObject value )
    /*-{
        this[key] = value;
    }-*/;

    public final native Object getObject( String key )
    /*-{
        var value = this[key];

        if (null == value) {
            return null;
        }

        var typeValue = typeof (value);
        if (typeValue == "number") {
            if (value % 1 == 0) {
                value = @java.lang.Integer::valueOf(I)(value);
            } else {
                value = @java.lang.Double::valueOf(D)(value);
            }
        } else if (typeValue == "boolean") {
            value = @java.lang.Boolean::valueOf(Z)(value);
        }

        return value;
    }-*/;

    public final native Double getDouble( String key )
    /*-{
        return this[key] == null ? null
                : @java.lang.Double::valueOf(D)(this[key]);
    }-*/;

    public final Integer getInteger( String key )
    {
        Double value = getDouble( key );
        return value == null ? null : value.intValue();
    }

    public final native String getString( String key )
    /*-{
        return this[key];
    }-*/;

    public final native Boolean getBoolean( String key )
    /*-{
        return this[key] == null ? null
                : @java.lang.Boolean::valueOf(Z)(this[key]);
    }-*/;

    public final native <T extends JavaScriptObject> T getJsObject( String key )
    /*-{
        return this[key];
    }-*/;

    public final JsArrayMixed getArray( String key )
    {
        return getJsObject( key );
    }

    public final JsArrayString getStringArray( String key )
    {
        return getJsObject( key );
    }

    public final JsArrayInteger getIntegerArray( String key )
    {
        return getJsObject( key );
    }

    public final JsArrayNumber getDoubleArray( String key )
    {
        return getJsObject( key );
    }

    public final native String stringify()
    /*-{
        return JSON.stringify(this);
    }-*/;

}
