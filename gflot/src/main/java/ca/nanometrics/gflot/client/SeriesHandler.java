package ca.nanometrics.gflot.client;

public interface SeriesHandler
{

    /**
     * Add a datapoint
     * 
     * @param datapoint datapoint to add
     */
    void add( DataPoint datapoint );

    /**
     * Clear data
     */
    void clear();

    /**
     * Set if the series is visible or not
     * 
     * @param visible true if the series is visible, false otherwise
     */
    void setVisible( boolean visible );

    /**
     * @return true if the series is visible, false otherwise
     */
    boolean isVisible();

    /**
     * @return the series associated to this handler
     */
    Series getSeries();

    /**
     * @return the data associated to this handler
     */
    SeriesData getData();

    /**
     * Sets the data
     * 
     * @param newData
     */
    void setData( SeriesData newData );

}