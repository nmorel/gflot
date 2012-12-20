/*
 * Copyright (c) 2012 Nicolas Morel
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
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