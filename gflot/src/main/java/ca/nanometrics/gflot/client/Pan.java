package ca.nanometrics.gflot.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Pan
    extends Navigate<Pan>
{

    public static final Pan create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String LEFT_KEY = "left";
    private static final String TOP_KEY = "top";

    protected Pan()
    {
    }

    /**
     * Pan the plot to the left or right.
     * 
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public final Pan setLeft( double left )
    {
        put( LEFT_KEY, left );
        return this;
    }

    /**
     * Pan the plot to the top or bottom.
     * 
     * @param top Pixels to pan the plot to. Negative value to go up, positive value to go down
     */
    public final Pan setTop( double top )
    {
        put( TOP_KEY, top );
        return this;
    }
}
