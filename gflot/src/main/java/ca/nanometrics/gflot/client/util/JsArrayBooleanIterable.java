package ca.nanometrics.gflot.client.util;

import java.util.Iterator;

public class JsArrayBooleanIterable
    extends com.google.gwt.core.client.JsArrayBoolean
    implements Iterable<Boolean>
{
    protected JsArrayBooleanIterable()
    {
    }

    @Override
    public Iterator<Boolean> iterator()
    {
        return new JsArrayBooleanIterator( this );
    }

}
