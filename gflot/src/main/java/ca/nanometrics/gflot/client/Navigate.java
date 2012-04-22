package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

@SuppressWarnings( "unchecked" )
public class Navigate<T extends Navigate<?>>
    extends JSONObjectWrapper
{
    private static final String PREVENT_EVENT_KEY = "preventEvent";

    public Navigate()
    {
    }

    /**
     * Prevent the corresponding event to fire
     */
    public T setPreventEvent( boolean preventEvent )
    {
        put( PREVENT_EVENT_KEY, preventEvent );
        return (T) this;
    }

}
