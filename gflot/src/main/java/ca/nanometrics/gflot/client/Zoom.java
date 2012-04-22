package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

public class Zoom
    extends Navigate<Zoom>
{
    public static class Center
        extends JSONObjectWrapper
    {
        private static final String LEFT_KEY = "left";
        private static final String TOP_KEY = "top";

        public Center()
        {
        }

        public Center setLeft( double left )
        {
            put( LEFT_KEY, left );
            return this;
        }

        public Center setTop( double top )
        {
            put( TOP_KEY, top );
            return this;
        }
    }

    private static final String AMOUNT_KEY = "amount";
    private static final String CENTER_KEY = "center";

    /**
     * Pan the plot to the left or right.
     *
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public Zoom setAmount( double amount )
    {
        put( AMOUNT_KEY, amount );
        return this;
    }

    /**
     * Pan the plot to the left or right.
     *
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public Zoom setCenter( Center center )
    {
        put( CENTER_KEY, center );
        return this;
    }
}
