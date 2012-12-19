package ca.nanometrics.gflot.client.options;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * Class to ease the manipulation of axes options
 * 
 * @author Nicolas Morel
 */
public class AxesOptions
    extends JsArray<AbstractAxisOptions<?>>
{
    /**
     * Creates a {@link AxesOptions}
     */
    public static final AxesOptions create()
    {
        return JavaScriptObject.createArray().cast();
    }

    protected AxesOptions()
    {
    }

    /**
     * Add an axis options
     */
    public final AxesOptions addAxisOptions( AbstractAxisOptions<?> axis )
    {
        push( axis );
        return this;
    }

    /**
     * Return axis options at given index, starting at 1
     */
    public final AbstractAxisOptions<?> getAxisOptions( int axisNumber )
    {
        if ( axisNumber > length() )
        {
            return null;
        }
        return get( axisNumber - 1 );
    }

}
