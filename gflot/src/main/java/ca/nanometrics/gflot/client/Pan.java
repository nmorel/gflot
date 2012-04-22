package ca.nanometrics.gflot.client;

public class Pan
    extends Navigate<Pan>
{
    private static final String LEFT_KEY = "left";
    private static final String TOP_KEY = "top";

    /**
     * Pan the plot to the left or right.
     *
     * @param left Pixels to pan the plot to. Negative value to go to the left, positive value to go to the right
     */
    public Pan setLeft( double left )
    {
        put( LEFT_KEY, left );
        return this;
    }

    /**
     * Pan the plot to the top or bottom.
     *
     * @param top Pixels to pan the plot to. Negative value to go up, positive value to go down
     */
    public Pan setTop( double top )
    {
        put( TOP_KEY, top );
        return this;
    }
}
