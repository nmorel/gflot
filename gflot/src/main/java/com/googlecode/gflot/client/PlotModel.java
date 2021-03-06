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
package com.googlecode.gflot.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

/**
 * @author Alexander De Leon
 */
public class PlotModel
{

    public interface PlotModelListener
    {
        void onAddSeries( PlotModel model, SeriesHandler handler );

        void onRemoveSeries( PlotModel model, SeriesHandler handler );
    }

    private final List<SeriesHandler> handlers;

    private final List<PlotModelListener> listeners;

    public PlotModel()
    {
        handlers = new ArrayList<SeriesHandler>();
        listeners = new ArrayList<PlotModelListener>();
    }

    public SeriesHandler addSeries()
    {
        return addSeries( Series.create(), PlotModelStrategy.defaultStrategy() );
    }

    public SeriesHandler addSeries( Series series )
    {
        return addSeries( series, PlotModelStrategy.defaultStrategy() );
    }

    public SeriesHandler addSeries( SeriesDataStrategy strategy )
    {
        return addSeries( Series.create(), strategy );
    }

    public SeriesHandler addSeries( Series series, SeriesDataStrategy strategy )
    {
        SeriesHandler handler = createSeriesHandler( series, strategy );
        handlers.add( handler );
        fireOnAddSeries( series.getLabel(), series.getColor(), handler );
        return handler;
    }

    /**
     * Clear the data of all series but does not remove the series!
     */
    public void clear()
    {
        for ( SeriesHandler handler : handlers )
        {
            handler.clear();
        }
    }

    /**
     * Clear the data of the specified series
     * 
     * @param index index of the series to remove
     */
    public void clearSeries( int index )
    {
        checkSeriesBound( index );
        handlers.get( index ).clear();
    }

    /**
     * Remove the series from the plot
     * 
     * @param index index of the series to remove
     */
    public void removeSeries( int index )
    {
        checkSeriesBound( index );
        removeSeries( handlers.get( index ) );
    }

    /**
     * Remove the series from the plot
     * 
     * @param series Series to remove
     */
    public void removeSeries( SeriesHandler series )
    {
        handlers.remove( series );
        fireOnRemoveSeries( series );
    }

    /**
     * Remove all the series from the plot
     */
    public void removeAllSeries()
    {
        Iterator<SeriesHandler> iterator = handlers.iterator();
        while ( iterator.hasNext() )
        {
            SeriesHandler series = iterator.next();
            iterator.remove();
            fireOnRemoveSeries( series );
        }
    }

    private void checkSeriesBound( int index )
    {
        assert index >= 0 && index < handlers.size() : "Index out of bounds";
    }

    public void addListener( PlotModelListener listener )
    {
        listeners.add( listener );
    }

    public void removeListener( PlotModelListener listener )
    {
        listeners.remove( listener );
    }

    public JsArray<Series> getSeries()
    {
        JsArray<Series> seriesArray = JavaScriptObject.createArray().cast();
        for ( SeriesHandler handler : handlers )
        {
            seriesArray.push( handler.getSeries() );
        }
        return seriesArray;
    }

    /**
     * @return a read-only list of the series handler
     */
    public List<? extends SeriesHandler> getHandlers()
    {
        return Collections.unmodifiableList( handlers );
    }

    private void fireOnRemoveSeries( SeriesHandler handler )
    {
        for ( PlotModelListener listener : listeners )
        {
            listener.onRemoveSeries( this, handler );
        }
    }

    private void fireOnAddSeries( String label, String color, SeriesHandler handler )
    {
        for ( PlotModelListener listener : listeners )
        {
            listener.onAddSeries( this, handler );
        }
    }

    protected SeriesHandler createSeriesHandler( Series series, SeriesDataStrategy strategy )
    {
        return new DefaultSeriesHandler( series, strategy );
    }

    public int indexOf( SeriesHandler series )
    {
        return handlers.indexOf( series );
    }

}
