package ca.nanometrics.gflot.client.jsni;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

public abstract class JsonObject
    extends JavaScriptObject
{
    protected JsonObject()
    {
    }

    protected final native boolean hasKey( String key )
    /*-{
        return key in this;
    }-*/;

    protected final native void remove( String key )
    /*-{
        delete this[key];
    }-*/;

    protected final native void put( String key, String value )
    /*-{
        this[key] = value;
    }-*/;

    protected final native void put( String key, int value )
    /*-{
        this[key] = Object(value);
    }-*/;

    protected final native void put( String key, double value )
    /*-{
        this[key] = Object(value);
    }-*/;

    protected final native void put( String key, boolean value )
    /*-{
        this[key] = Object(value);
    }-*/;

    protected final native void put( String key, JavaScriptObject value )
    /*-{
        this[key] = value;
    }-*/;

    protected final native Object getObject( String key )
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

    protected final native Double getDouble( String key )
    /*-{
        return this[key] == null ? null : @java.lang.Double::valueOf(D)(this[key]);
    }-*/;

    protected final native Integer getInteger( String key )
    /*-{
        return this[key] == null ? null : @java.lang.Integer::valueOf(I)(this[key]);
    }-*/;

    protected final native String getString( String key )
    /*-{
        return this[key];
    }-*/;

    protected final native Boolean getBoolean( String key )
    /*-{
        return this[key] == null ? null : @java.lang.Boolean::valueOf(Z)(this[key]);
    }-*/;

    protected final native JavaScriptObject getJsObject( String key )
    /*-{
        return this[key];
    }-*/;

    protected final JsArrayMixed getArray( String key )
    {
        JavaScriptObject obj = getJsObject( key );
        JsArrayMixed result = null;
        if ( null != obj )
        {
            result = obj.cast();
        }
        return result;
    }

    protected final JsArrayString getStringArray( String key )
    {
        JavaScriptObject obj = getJsObject( key );
        JsArrayString result = null;
        if ( null != obj )
        {
            result = obj.cast();
        }
        return result;
    }

    protected final JsArrayInteger getIntegerArray( String key )
    {
        JavaScriptObject obj = getJsObject( key );
        JsArrayInteger result = null;
        if ( null != obj )
        {
            result = obj.cast();
        }
        return result;
    }

    protected final JsArrayNumber getDoubleArray( String key )
    {
        JavaScriptObject obj = getJsObject( key );
        JsArrayNumber result = null;
        if ( null != obj )
        {
            result = obj.cast();
        }
        return result;
    }

}
