package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.util.JSONArrayWrapper;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

/**
 * Class to ease the manipulation of axes options
 *
 * @author Nicolas Morel
 */
public class AxesOptions
    extends JSONArrayWrapper
{
    public AxesOptions()
    {
        super();
    }

    AxesOptions( JSONArray jsonArray )
    {
        super( jsonArray );
    }

    /**
     * Add an axis options
     */
    public AxesOptions addAxisOptions( AbstractAxisOptions<?> axis )
    {
        push( axis );
        return this;
    }

    /**
     * Add axes options
     */
    public AxesOptions addAxesOptions( AbstractAxisOptions<?>[] axes )
    {
        pushAll( axes );
        return this;
    }

    /**
     * Return axis options at given index, starting at 1
     */
    public AbstractAxisOptions<?> getAxisOptions( int axisNumber )
    {
        if ( axisNumber > size() )
        {
            return null;
        }
        JSONObject obj = getObject( axisNumber - 1 );
        if ( null == obj )
        {
            return null;
        }
        else
        {
            return AbstractAxisOptions.createAxisOptions( obj );
        }
    }

    /**
     * Return axes options
     */
    public AbstractAxisOptions<?>[] getAxesOptions()
    {
        AbstractAxisOptions<?>[] axes = new AbstractAxisOptions<?>[size()];
        for ( int i = 0; i < size(); i++ )
        {
            axes[i] = AbstractAxisOptions.createAxisOptions( getObject( i ) );
        }
        return axes;
    }

}
