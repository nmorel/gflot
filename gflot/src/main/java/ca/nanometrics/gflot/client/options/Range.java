package ca.nanometrics.gflot.client.options;

import com.google.gwt.json.client.JSONObject;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

/**
 * @author Mohamed M. El-Kalioby
 * @since Septmeber 21, 2009
 */
public class Range
    extends JSONObjectWrapper
{
    private static final String FROM_KEY = "from";

    private static final String TO_KEY = "to";

    public Range()
    {
        super();
    }

    Range( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    public Range( double from, double to )
    {
        super();
        put( FROM_KEY, from );
        put( TO_KEY, to );
    }

    public Range setFrom( double from )
    {
        put( FROM_KEY, from );
        return this;
    }

    public Double getFrom()
    {
        return getDouble( FROM_KEY );
    }

    public Range setTo( double to )
    {
        put( TO_KEY, to );
        return this;
    }

    public Double getTo()
    {
        return getDouble( TO_KEY );
    }

}
