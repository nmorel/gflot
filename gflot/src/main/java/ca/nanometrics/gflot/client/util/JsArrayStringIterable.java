package ca.nanometrics.gflot.client.util;

import java.util.Iterator;

public class JsArrayStringIterable
    extends com.google.gwt.core.client.JsArrayString
    implements Iterable<String>
{

    protected JsArrayStringIterable()
    {
    }

    @Override
    public Iterator<String> iterator()
    {
        return new JsArrayStringIterator( this );
    }

}
