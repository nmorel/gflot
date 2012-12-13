package ca.nanometrics.gflot.client.jsni;

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
        this[key] = Object(value);
    }-*/;

    public final native void put( String key, double value )
    /*-{
        this[key] = Object(value);
    }-*/;

    public final native void put( String key, boolean value )
    /*-{
        this[key] = Object(value);
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

    public final native Integer getInteger( String key )
    /*-{
        return this[key] == null ? null
                : @java.lang.Integer::valueOf(I)(this[key]);
    }-*/;

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

}
