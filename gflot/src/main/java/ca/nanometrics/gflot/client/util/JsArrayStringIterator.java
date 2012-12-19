package ca.nanometrics.gflot.client.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.JsArrayString;

public class JsArrayStringIterator
    implements Iterator<String>
{
    /** Index into the data array */
    private int i = -1;

    private final JsArrayString array;

    public JsArrayStringIterator( JsArrayString array )
    {
        this.array = array;
    }

    public boolean hasNext()
    {
        int length = array.length();
        return length > 0 && ( i + 1 ) < length;
    }

    public String next()
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
