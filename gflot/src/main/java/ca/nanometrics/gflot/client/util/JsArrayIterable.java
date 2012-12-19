package ca.nanometrics.gflot.client.util;

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;

public class JsArrayIterable<T extends JavaScriptObject>
    extends com.google.gwt.core.client.JsArray<T>
    implements Iterable<T>
{

    protected JsArrayIterable()
    {
    }

    @Override
    public Iterator<T> iterator()
    {
        return new JsArrayIterator<T>( this );
    }

}
