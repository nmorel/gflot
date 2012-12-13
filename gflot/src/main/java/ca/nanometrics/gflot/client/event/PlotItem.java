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
package ca.nanometrics.gflot.client.event;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.jsni.JsonObject;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Alexander De Leon
 */
public class PlotItem
    extends JsonObject
{
    public static final PlotItem create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String DATAPOINT = "datapoint";
    private static final String DATA_INDEX = "dataIndex";
    private static final String SERIES = "series";
    private static final String SERIES_INDEX = "seriesIndex";
    private static final String PAGE_X = "pageX";
    private static final String PAGE_Y = "pageY";

    protected PlotItem()
    {
    }

    /**
     * @return the datapoint hovered
     */
    public final DataPoint getDataPoint()
    {
        return getJsObject( DATAPOINT );
    }

    /**
     * @return the index of the data point inside the data array
     */
    public final Integer getDataIndex()
    {
        return getInteger( DATA_INDEX );
    }

    /**
     * @return the series hovered
     */
    public final Series getSeries()
    {
        return getJsObject( SERIES );
    }

    /**
     * @return the index of the series, starting at 0
     */
    public final Integer getSeriesIndex()
    {
        return getInteger( SERIES_INDEX );
    }

    /**
     * @return the global screen x coordinates
     */
    public final Integer getPageX()
    {
        return getInteger( PAGE_X );
    }

    /**
     * @return the global screen y coordinates
     */
    public final Integer getPageY()
    {
        return getInteger( PAGE_Y );
    }
}
