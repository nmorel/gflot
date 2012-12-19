package ca.nanometrics.gflot.client.util;

import java.util.Iterator;

public class JsArrayIntegerIterable
    extends com.google.gwt.core.client.JsArrayInteger
    implements Iterable<Integer>
{

    protected JsArrayIntegerIterable()
    {
    }

    @Override
    public Iterator<Integer> iterator()
    {
        return new JsArrayIntegerIterator( this );
    }

}
