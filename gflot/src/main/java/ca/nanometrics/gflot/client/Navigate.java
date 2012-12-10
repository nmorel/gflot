package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.jsni.JsonObject;

@SuppressWarnings( "unchecked" )
public class Navigate<T extends Navigate<?>>
    extends JsonObject
{
    private static final String PREVENT_EVENT_KEY = "preventEvent";

    protected Navigate()
    {
    }

    /**
     * Prevent the corresponding event to fire
     */
    public final T setPreventEvent( boolean preventEvent )
    {
        put( PREVENT_EVENT_KEY, preventEvent );
        return (T) this;
    }

}
