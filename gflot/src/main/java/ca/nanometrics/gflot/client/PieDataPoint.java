package ca.nanometrics.gflot.client;

public class PieDataPoint
    extends DataPoint
{
    /**
     * Creates a {@link PieDataPoint} with the specified percent
     */
    public static final native PieDataPoint of( double value )
    /*-{
        return [ 1, value ];
    }-*/;

    protected PieDataPoint()
    {
    }
}
