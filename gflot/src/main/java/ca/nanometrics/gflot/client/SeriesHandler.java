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
public class SeriesHandler
{
    private final Series series;

    private SeriesData data;

    public SeriesHandler( Series series, SeriesData data )
    {
        this.series = series;
        this.data = data;
    }

    /**
     * Add a datapoint
     *
     * @param datapoint datapoint to add
     */
    public void add( DataPoint datapoint )
    {
        data.add( datapoint );
    }

    /**
     * Clear data
     */
    public void clear()
    {
        data.clear();
    }

    /**
     * Set if the series is visible or not
     *
     * @param visible true if the series is visible, false otherwise
     */
    public void setVisible( boolean visible )
    {
        if ( visible )
        {
            series.setData( data );
        }
        else
        {
            series.setData( null );
        }
    }

    /**
     * @return true if the series is visible, false otherwise
     */
    public boolean isVisible()
    {
        return null != series.getData();
    }

    /**
     * @return the series associated to this handler
     */
    public Series getSeries()
    {
        return series;
    }

    /**
     * @return the data associated to this handler
     */
    public SeriesData getData()
    {
        return data;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == this )
        {
            return true;
        }
        if ( obj instanceof SeriesHandler )
        {
            return series.equals( ( (SeriesHandler) obj ).series );
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return series.hashCode();
    }

    /* ------------- for internal use */
    void setData( SeriesData newData )
    {
        data = newData;
        // set the data if the series is visible
        if ( isVisible() )
        {
            series.setData( data );
        }
    }

}
