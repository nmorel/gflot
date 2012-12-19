package ca.nanometrics.gflot.client.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.core.client.JsArrayNumber;

public class JsArrayNumberIterator
    implements Iterator<Double>
{
    /** Index into the data array */
    private int i = -1;

    private final JsArrayNumber array;

    public JsArrayNumberIterator( JsArrayNumber array )
    {
        this.array = array;
    }

    public boolean hasNext()
    {
        int length = array.length();
        return length > 0 && ( i + 1 ) < length;
    }

    public Double next()
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
