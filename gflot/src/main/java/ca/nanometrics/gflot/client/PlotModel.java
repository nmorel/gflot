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
        void onAddSeries( PlotModel model, String label, String color, SeriesHandler handler );

        void onRemoveSeries( PlotModel model, SeriesHandler handler );
    }

    private PlotModelStrategy strategy;

    private final List<SeriesHandler> handlers = new ArrayList<SeriesHandler>();

    private final List<PlotModelListener> listeners = new ArrayList<PlotModelListener>();

    public PlotModel()
    {
        this( PlotModelStrategy.defaultStrategy() );
    }

    public PlotModel( PlotModelStrategy strategy )
    {
        this.strategy = strategy;
    }

    public SeriesHandler addSeries( String label )
    {
        return addSeries( label, null );
    }

    public SeriesHandler addSeries( String label, String color )
    {
        Series series = Series.create().setLabel( label );
        if ( color != null )
        {
            series.setColor( color );
        }
        return addSeries( series );
    }

    public SeriesHandler addSeries( Series series )
    {
        SeriesData data = strategy.createSeriesData();
        series.setData( data );

        SeriesHandler handler = createSeriesHandler( series, data );
        handlers.add( handler );
        fireOnAddSeries( series.getLabel(), series.getColor(), handler );
        return handler;
    }

    public void setStrategy( PlotModelStrategy strategy )
    {
        this.strategy = strategy;
        for ( SeriesHandler handler : handlers )
        {
            SeriesData currentData = handler.getData();
            handler.setData( this.strategy.createSeriesData() );
            for ( int i = 0; i < currentData.length(); i++ )
            {
                handler.add( currentData.get( i ) );
            }
        }
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
    public List<SeriesHandler> getHandlers()
    {
        return Collections.unmodifiableList( handlers );
    }

    protected SeriesHandler createSeriesHandler( Series series, SeriesData data )
    {
        return new SeriesHandler( series, data );
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
            listener.onAddSeries( this, label, color, handler );
        }
    }
}
