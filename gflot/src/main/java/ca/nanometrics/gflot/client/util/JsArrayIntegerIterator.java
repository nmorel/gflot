package ca.nanometrics.gflot.client.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.JsArrayInteger;

public class JsArrayIntegerIterator
    implements Iterator<Integer>
{
    /** Index into the data array */
    private int i = -1;

    private final JsArrayInteger array;

    public JsArrayIntegerIterator( JsArrayInteger array )
    {
        this.array = array;
    }

    public boolean hasNext()
    {
        int length = array.length();
        return length > 0 && ( i + 1 ) < length;
    }

    public Integer next()
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
