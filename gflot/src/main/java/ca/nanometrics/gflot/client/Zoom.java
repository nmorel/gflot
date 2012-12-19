package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

public class Zoom
    extends Navigate<Zoom>
{
    public static class Center
        extends JsonObject
    {
        public static final Center create()
        {
            return JavaScriptObject.createObject().cast();
        }

        private static final String LEFT_KEY = "left";
        private static final String TOP_KEY = "top";

        protected Center()
        {
        }

        public final Center setLeft( double left )
        {
            put( LEFT_KEY, left );
            return this;
        }

        public final Center setTop( double top )
        {
            put( TOP_KEY, top );
            return this;
        }
    }

    public static final Zoom create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String AMOUNT_KEY = "amount";
    private static final String CENTER_KEY = "center";

    protected Zoom()
    {
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Zoom setAmount( double amount )
    {
        put( AMOUNT_KEY, amount );
        return this;
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Zoom setCenter( Center center )
    {
        put( CENTER_KEY, center );
        return this;
    }
}
