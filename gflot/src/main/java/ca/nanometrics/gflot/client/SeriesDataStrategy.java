package ca.nanometrics.gflot.client;

public interface SeriesDataStrategy
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
     * @return the data associated to this handler
     */
    SeriesData getData();

    /**
     * Set the data
     * 
     * @param newData new data to set
     */
    void setData( SeriesData newData );

}
