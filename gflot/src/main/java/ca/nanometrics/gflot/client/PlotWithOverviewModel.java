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

import ca.nanometrics.gflot.client.options.AbstractSeriesOptions;
import ca.nanometrics.gflot.client.util.Algorithm;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Alexander De Leon
 */
public class PlotWithOverviewModel
    extends PlotModel
{

    private static class PopulateCommand
        implements Command
    {
        private Command toExecuteAfterAllDataPopulated;

        private int nbSeries;

        private int nbSeriesPopulated;

        PopulateCommand( Command toExecuteAfterAllDataPopulated, int nbSeries )
        {
            this.toExecuteAfterAllDataPopulated = toExecuteAfterAllDataPopulated;
            this.nbSeries = nbSeries;
            this.nbSeriesPopulated = 0;
        }

        @Override
        public void execute()
        {
            nbSeriesPopulated++;
            if ( nbSeriesPopulated == nbSeries )
            {
                toExecuteAfterAllDataPopulated.execute();
            }
        }
    }

    private final PlotModel m_windowModel;
    private final PlotModel m_overviewModel;
    private final double[] m_selection = new double[2];

    public PlotWithOverviewModel( PlotModelStrategy strategy )
    {
        super( strategy );
        m_overviewModel = new PlotModel( strategy );
        m_windowModel = new PlotModel();
    }

    public void setDataProvider( SeriesHandler handler, DataProvider provider )
    {
        setDataProvider( handler, new AsyncDataProviderWrapper( provider ) );
    }

    public void setDataProvider( SeriesHandler handler, AsyncDataProvider provider )
    {
        ( (PlotWithOverviewSeriesHandler) handler ).setDataProvider( provider );
    }

    protected SeriesHandler createSeriesHandler( Series series, SeriesData data )
    {
        return new PlotWithOverviewSeriesHandler( series, data );
    }

    PlotModel getWindowPlotModel()
    {
        return m_windowModel;
    }

    PlotModel getOverviewPlotModel()
    {
        return m_overviewModel;
    }

    public Series[] getWindowsSeries()
    {
        return m_windowModel.getSeries();
    }

    public Series[] getOverviewSeries()
    {
        return m_overviewModel.getSeries();
    }

    void setSelection( double x1, double x2 )
    {
        setSelection( x1, x2, null );
    }

    void setSelection( double x1, double x2, Command toExcuteAfterSelection )
    {
        m_selection[0] = x1;
        m_selection[1] = x2;

        Command command = null;
        if ( null != toExcuteAfterSelection )
        {
            command = new PopulateCommand( toExcuteAfterSelection, getHandlers().size() );
        }

        for ( SeriesHandler handler : getHandlers() )
        {
            ( (PlotWithOverviewSeriesHandler) handler ).populateWindowSeries( command );
        }
    }

    double[] getSelection()
    {
        return m_selection;
    }

    class PlotWithOverviewSeriesHandler
        extends SeriesHandler
    {
        private AsyncDataProvider m_provider;
        private final SeriesHandler m_overviewHandler;
        private final SeriesHandler m_windowHandler;
        private DataPoint m_lastDataPoint;
        private DataPoint m_firstDataPoint;
        private boolean m_lockSelection;

        public PlotWithOverviewSeriesHandler( Series series, SeriesData data )
        {
            super( series, data );
            m_provider = new AsyncDataProviderWrapper( new LocalDataProvider( data ) );
            m_windowHandler = m_windowModel.addSeries( series.getLabel(), series.getColor() );
            m_overviewHandler = m_overviewModel.addSeries( series.getLabel(), series.getColor() );
        }

        @Override
        public void add( DataPoint datapoint )
        {
            super.add( datapoint );
            m_overviewHandler.add( datapoint );
            if ( m_lockSelection && m_selection[1] < datapoint.getX() )
            {
                double diff = datapoint.getX() - m_lastDataPoint.getX();
                double x1 = m_selection[0] + diff;
                double x2 = m_selection[1] + diff;
                setSelection( Math.max( x1, m_selection[0] ), Math.max( x2, m_selection[1] ) );
            }
            if ( m_firstDataPoint == null )
            {
                m_firstDataPoint = datapoint;
            }
            m_lastDataPoint = datapoint;
        }

        @Override
        public void clear()
        {
            super.clear();
            m_windowHandler.clear();
            m_overviewHandler.clear();
            m_lastDataPoint = null;
            m_firstDataPoint = null;
            m_lockSelection = false;
        }

        @Override
        void setData( SeriesData newData )
        {
            super.setData( newData );
            m_overviewHandler.setData( newData );
            m_windowHandler.clear();
        }

        @Override
        public void setOptions( SeriesType type, AbstractSeriesOptions<?> options )
        {
            super.setOptions( type, options );
            m_windowHandler.setOptions( type, options );
        }

        @Override
        public void setVisible( boolean visisble )
        {
            super.setVisible( visisble );
            m_overviewHandler.setVisible( visisble );
            m_windowHandler.setVisible( visisble );
        }

        public void setDataProvider( AsyncDataProvider provider )
        {
            m_provider = provider;
        }

        void populateWindowSeries( final Command toExcuteAfterSelection )
        {
            final double x1 = getWindowMinX();
            final double x2 = getWindowMaxX();
            m_windowHandler.clear();
            if ( x1 < x2 )
            {
                m_provider.getData( x1, x2, new AsyncCallback<DataPoint[]>()
                {
                    public void onFailure( Throwable caught )
                    {
                        GWT.log( "Failed to obtain data for PlotWithOverview", caught );
                        if ( toExcuteAfterSelection != null )
                        {
                            toExcuteAfterSelection.execute();
                        }
                    }

                    public void onSuccess( DataPoint[] result )
                    {
                        for ( DataPoint point : result )
                        {
                            m_windowHandler.add( point );
                        }
                        m_lockSelection = x2 >= m_lastDataPoint.getX();
                        if ( toExcuteAfterSelection != null )
                        {
                            toExcuteAfterSelection.execute();
                        }
                    }
                } );
            }
        }

        private double getWindowMinX()
        {
            double x = m_selection[0];
            if ( x == m_overviewHandler.getData().getX( 0 ) )
            {
                return m_firstDataPoint.getX();
            }
            return x;
        }

        private double getWindowMaxX()
        {
            double x = m_selection[1];
            SeriesData data = m_overviewHandler.getData();
            int size = data.size();
            if ( size > 0 && x == data.getX( size - 1 ) )
            {
                return m_lastDataPoint.getX();
            }
            return x;
        }
    }

    public interface DataProvider
    {
        DataPoint[] getData( double x1, double x2 );
    }

    public interface AsyncDataProvider
    {
        void getData( double x1, double x2, AsyncCallback<DataPoint[]> callback );
    }

    private class LocalDataProvider
        implements DataProvider
    {

        private final SeriesData m_data;

        public LocalDataProvider( SeriesData data )
        {
            m_data = data;
        }

        public DataPoint[] getData( double x1, double x2 )
        {
            int start = Algorithm.xBinarySearch( m_data, x1 );
            if ( start == -1 )
            {
                start = 0;
            }
            int end = Algorithm.xBinarySearch( m_data, x2 );
            if ( end == -1 )
            {
                return m_data.slice( start ).getDatapoints();
            }
            return m_data.slice( start, end ).getDatapoints();
        }

    }

    private class AsyncDataProviderWrapper
        implements AsyncDataProvider
    {
        private final DataProvider m_provider;

        public AsyncDataProviderWrapper( DataProvider provider )
        {
            m_provider = provider;
        }

        public void getData( double x1, double x2, AsyncCallback<DataPoint[]> callback )
        {
            try
            {
                DataPoint[] result = m_provider.getData( x1, x2 );
                callback.onSuccess( result );
            }
            catch ( Throwable e )
            {
                callback.onFailure( e );
            }
        }
    }
}
