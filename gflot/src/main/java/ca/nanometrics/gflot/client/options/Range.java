package ca.nanometrics.gflot.client.options;

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Mohamed M. El-Kalioby
 * @since Septmeber 21, 2009
 */
public class Range
    extends JsonObject
{
    /**
     * Creates a {@link Range}
     */
    public static final Range create()
    {
        return JavaScriptObject.createObject().cast();
    }

    /**
     * Creates a {@link Range} with specified range
     */
    public static final Range of( double from, double to )
    {
        Range range = JavaScriptObject.createObject().cast();
        range.setFrom( from );
        range.setTo( to );
        return range;
    }

    private static final String FROM_KEY = "from";
    private static final String TO_KEY = "to";

    protected Range()
    {
    }

    public final Range setFrom( double from )
    {
        put( FROM_KEY, from );
        return this;
    }

    public final Double getFrom()
    {
        return getDouble( FROM_KEY );
    }

    public final Range setTo( double to )
    {
        put( TO_KEY, to );
        return this;
    }

    public final Double getTo()
    {
        return getDouble( TO_KEY );
    }

}
