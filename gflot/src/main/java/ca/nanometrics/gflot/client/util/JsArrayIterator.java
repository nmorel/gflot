package ca.nanometrics.gflot.client.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class JsArrayIterator<T extends JavaScriptObject>
    implements Iterator<T>
{
    /** Index into the data array */
    private int i = -1;

    private final JsArray<T> array;

    public JsArrayIterator( JsArray<T> array )
    {
        this.array = array;
    }

    public boolean hasNext()
    {
        int length = array.length();
        return length > 0 && ( i + 1 ) < length;
    }

    public T next()
    {
        i++;
        if ( i >= array.length() )
        {
            throw new NoSuchElementException();
        }
        return array.get( i );
    }

    public void remove()
    {
        // FIXME overlay
        throw new UnsupportedOperationException();
    }
}
