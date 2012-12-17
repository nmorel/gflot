/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client;

/**
 * Handler used to manipulate a series and its data.
 * 
 * @author Alexander De Leon
 */
public class DefaultSeriesHandler
    implements SeriesHandler
{
    private final Series series;
    private final SeriesDataStrategy strategy;

    public DefaultSeriesHandler( Series series, SeriesDataStrategy strategy )
    {
        this.series = series;
        this.strategy = strategy;
        this.series.setData( strategy.getData() );
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#add(ca.nanometrics.gflot.client.DataPoint)
     */
    @Override
    public void add( DataPoint datapoint )
    {
        strategy.add( datapoint );
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#clear()
     */
    @Override
    public void clear()
    {
        strategy.clear();
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#setVisible(boolean)
     */
    @Override
    public void setVisible( boolean visible )
    {
        if ( visible )
        {
            series.setData( getData() );
        }
        else
        {
            series.setData( null );
        }
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#isVisible()
     */
    @Override
    public boolean isVisible()
    {
        return null != series.getData();
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#getSeries()
     */
    @Override
    public Series getSeries()
    {
        return series;
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.ISeriesHandler#getData()
     */
    @Override
    public SeriesData getData()
    {
        return strategy.getData();
    }

    /*
     * (non-Javadoc)
     * @see ca.nanometrics.gflot.client.SeriesHandler#setData(ca.nanometrics.gflot.client.SeriesData)
     */
    public void setData( SeriesData newData )
    {
        strategy.setData( newData );
        // set the data if the series is visible
        if ( isVisible() )
        {
            series.setData( getData() );
        }
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == this )
        {
            return true;
        }
        if ( obj instanceof DefaultSeriesHandler )
        {
            return series.equals( ( (DefaultSeriesHandler) obj ).series );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return series.hashCode();
    }

}
