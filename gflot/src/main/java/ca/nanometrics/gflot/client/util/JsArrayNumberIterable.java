package ca.nanometrics.gflot.client.util;

import java.util.Iterator;

public class JsArrayNumberIterable
    extends com.google.gwt.core.client.JsArrayNumber
    implements Iterable<Double>
{

    protected JsArrayNumberIterable()
    {
    }

    @Override
    public Iterator<Double> iterator()
    {
        return new JsArrayNumberIterator( this );
    }

}
