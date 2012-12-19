package ca.nanometrics.gflot.client;

public class DefaultSeriesDataStrategy
    implements SeriesDataStrategy
{
    protected final SeriesData data;

    public DefaultSeriesDataStrategy()
    {
        this( SeriesData.create() );
    }

    public DefaultSeriesDataStrategy( SeriesData data )
    {
        this.data = data;
    }

    @Override
    public void add( DataPoint datapoint )
    {
        data.push( datapoint );
    }

    @Override
    public void clear()
    {
        data.clear();
    }

    @Override
    public SeriesData getData()
    {
        return data;
    }

    @Override
    public void setData( SeriesData newData )
    {
        // FIXME overlay voir pour le faire de maniere plus efficace. Avec splice par exemple
        data.clear();
        for ( int i = 0; i < newData.length(); i++ )
        {
            data.push( newData.get( i ) );
        }
    }

}
