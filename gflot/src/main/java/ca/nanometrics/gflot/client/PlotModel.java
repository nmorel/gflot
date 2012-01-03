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
import java.util.List;

/**
 * @author Alexander De Leon
 */
public class PlotModel
{

    private PlotModelStrategy m_strategy;

    private final List<SeriesHandler> m_handlers = new ArrayList<SeriesHandler>();

    private final List<PlotModelListener> m_listeners = new ArrayList<PlotModelListener>();

    public PlotModel()
    {
        this( PlotModelStrategy.defaultStrategy() );
    }

    public PlotModel( PlotModelStrategy strategy )
    {
        m_strategy = strategy;
    }

    public SeriesHandler addSeries( String label )
    {
        return addSeries( label, null );
    }

    public SeriesHandler addSeries( String label, String color )
    {
        Series series = new Series();
        series.setLabel( label );

        if ( color != null )
        {
            series.setColor( color );
        }
        return addSeries( series );
    }

    public SeriesHandler addSeries( Series series )
    {
        SeriesData data = m_strategy.createSeriesData();
        series.setData( data );

        SeriesHandler handler = createSeriesHandler( series, data );
        m_handlers.add( handler );
        fireOnAddSeries( series.getLabel(), series.getColor(), handler );
        return handler;
    }

    public void removeSeries( SeriesHandler handler )
    {
        m_handlers.remove( handler );
        fireOnRemoveSeries( handler );
    }

    public void setStrategy( PlotModelStrategy strategy )
    {
        m_strategy = strategy;
        for ( SeriesHandler handler : m_handlers )
        {
            DataPoint[] currentData = handler.getData().getDatapoints();
            handler.setData( m_strategy.createSeriesData() );
            for ( DataPoint datapoint : currentData )
            {
                handler.add( datapoint );
            }
        }
    }

    public void clear()
    {
        for ( SeriesHandler handler : m_handlers )
        {
            handler.clear();
        }
    }

    public void addListener( PlotModelListener listener )
    {
        m_listeners.add( listener );
    }

    public void removeListener( PlotModelListener listener )
    {
        m_listeners.remove( listener );
    }

    public Series[] getSeries()
    {
        Series[] seriesArray = new Series[m_handlers.size()];
        int i = 0;
        for ( SeriesHandler handler : m_handlers )
        {
            seriesArray[i++] = handler.getSeries();
        }
        return seriesArray;
    }

    protected List<SeriesHandler> getHandlers()
    {
        return m_handlers;
    }

    protected SeriesHandler createSeriesHandler( Series series, SeriesData data )
    {
        return new SeriesHandler( series, data );
    }

    private void fireOnRemoveSeries( SeriesHandler handler )
    {
        for ( PlotModelListener listener : m_listeners )
        {
            listener.onRemoveSeries( this, handler );
        }
    }

    private void fireOnAddSeries( String label, String color, SeriesHandler handler )
    {
        for ( PlotModelListener listener : m_listeners )
        {
            listener.onAddSeries( this, label, color, handler );
        }
    }

    public interface PlotModelListener
    {

        void onAddSeries( PlotModel model, String label, String color, SeriesHandler handler );

        void onRemoveSeries( PlotModel model, SeriesHandler handler );
    }

}
