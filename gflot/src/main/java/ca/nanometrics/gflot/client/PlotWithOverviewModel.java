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

import java.util.List;

import ca.nanometrics.gflot.client.util.Algorithm;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Alexander De Leon
 */
public class PlotWithOverviewModel
    extends PlotModel
{

    public class PlotWithOverviewSeriesHandler
        implements SeriesHandler
    {
        private AsyncDataProvider provider;
        private final SeriesHandler overviewHandler;
        private final SeriesHandler windowHandler;
        private DataPoint lastDataPoint;
        private DataPoint firstDataPoint;
        private boolean lockSelection;

        public PlotWithOverviewSeriesHandler( Series series, SeriesDataStrategy strategy )
        {
            provider = new AsyncDataProviderWrapper( new LocalDataProvider( strategy.getData() ) );
            windowHandler = windowModel.addSeries( series, PlotModelStrategy.defaultStrategy() );
            overviewHandler = overviewModel.addSeries( Series.create(), strategy );
        }

        @Override
        public void add( DataPoint datapoint )
        {
            overviewHandler.add( datapoint );
            if ( lockSelection && selection[1] < datapoint.getX() )
            {
                double diff = datapoint.getX() - lastDataPoint.getX();
                double x1 = selection[0] + diff;
                double x2 = selection[1] + diff;
                setSelection( Math.max( x1, selection[0] ), Math.max( x2, selection[1] ) );
            }
            if ( firstDataPoint == null )
            {
                firstDataPoint = datapoint;
            }
            lastDataPoint = datapoint;
        }

        @Override
        public void clear()
        {
            windowHandler.clear();
            overviewHandler.clear();
            lastDataPoint = null;
            firstDataPoint = null;
            lockSelection = false;
        }

        @Override
        public SeriesData getData()
        {
            return overviewHandler.getData();
        }

        @Override
        public boolean isVisible()
        {
            return overviewHandler.isVisible();
        }

        @Override
        public void setData( SeriesData newData )
        {
            overviewHandler.setData( newData );
            windowHandler.clear();
        }

        @Override
        public void setVisible( boolean visisble )
        {
            overviewHandler.setVisible( visisble );
            windowHandler.setVisible( visisble );
        }

        public void setDataProvider( AsyncDataProvider provider )
        {
            this.provider = provider;
        }

        void populateWindowSeries( final Command toExcuteAfterSelection )
        {
            final double x1 = getWindowMinX();
            final double x2 = getWindowMaxX();
            windowHandler.clear();
            if ( x1 < x2 )
            {
                provider.getData( x1, x2, new AsyncCallback<SeriesData>() {
                    @Override
                    public void onFailure( Throwable caught )
                    {
                        GWT.log( "Failed to obtain data for PlotWithOverview", caught );
                        if ( toExcuteAfterSelection != null )
                        {
                            toExcuteAfterSelection.execute();
                        }
                    }

                    @Override
                    public void onSuccess( SeriesData result )
                    {
                        for ( int i = 0; i < result.length(); i++ )
                        {
                            windowHandler.add( result.get( i ) );
                        }
                        lockSelection = x2 >= lastDataPoint.getX();
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
            double x = selection[0];
            if ( x == overviewHandler.getData().getX( 0 ) )
            {
                return firstDataPoint.getX();
            }
            return x;
        }

        private double getWindowMaxX()
        {
            double x = selection[1];
            SeriesData data = overviewHandler.getData();
            int size = data.length();
            if ( size > 0 && x == data.getX( size - 1 ) )
            {
                return lastDataPoint.getX();
            }
            return x;
        }

        @Override
        public Series getSeries()
        {
            return getOverviewSeries();
        }

        public Series getOverviewSeries()
        {
            return overviewHandler.getSeries();
        }

        public Series getWindowSeries()
        {
            return windowHandler.getSeries();
        }

        @Override
        public boolean equals( Object obj )
        {
            if ( obj == this )
            {
                return true;
            }
            if ( obj instanceof PlotWithOverviewSeriesHandler )
            {
                return getSeries().equals( ( (PlotWithOverviewSeriesHandler) obj ).getSeries() );
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            return getSeries().hashCode();
        }
    }

    public interface DataProvider
    {
        SeriesData getData( double x1, double x2 );
    }

    private class LocalDataProvider
        implements DataProvider
    {

        private final SeriesData data;

        public LocalDataProvider( SeriesData data )
        {
            this.data = data;
        }

        @Override
        public SeriesData getData( double x1, double x2 )
        {
            if ( x2 < data.getX( 0 ) || x1 > data.getX( data.length() - 1 ) )
            {
                return SeriesData.create();
            }
            int start = Algorithm.xBinarySearch( data, x1 );
            if ( start == -1 )
            {
                start = 0;
            }
            int end = Algorithm.xBinarySearch( data, x2 );
            if ( end == -1 )
            {
                return data.slice( start );
            }
            return data.slice( start, end );
        }
    }

    public interface AsyncDataProvider
    {
        void getData( double x1, double x2, AsyncCallback<SeriesData> callback );
    }

    private class AsyncDataProviderWrapper
        implements AsyncDataProvider
    {
        private final DataProvider provider;

        public AsyncDataProviderWrapper( DataProvider provider )
        {
            this.provider = provider;
        }

        @Override
        public void getData( double x1, double x2, AsyncCallback<SeriesData> callback )
        {
            try
            {
                SeriesData result = provider.getData( x1, x2 );
                callback.onSuccess( result );
            }
            catch ( Throwable e )
            {
                callback.onFailure( e );
            }
        }
    }

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

    private final PlotModel windowModel;
    private final PlotModel overviewModel;
    private final double[] selection = new double[2];

    public PlotWithOverviewModel()
    {
        overviewModel = new PlotModel();
        windowModel = new PlotModel();
    }

    public void setDataProvider( PlotWithOverviewSeriesHandler handler, DataProvider provider )
    {
        setDataProvider( handler, new AsyncDataProviderWrapper( provider ) );
    }

    public void setDataProvider( PlotWithOverviewSeriesHandler handler, AsyncDataProvider provider )
    {
        handler.setDataProvider( provider );
    }

    @Override
    protected PlotWithOverviewSeriesHandler createSeriesHandler( Series series, SeriesDataStrategy strategy )
    {
        return new PlotWithOverviewSeriesHandler( series, strategy );
    }

    PlotModel getWindowPlotModel()
    {
        return windowModel;
    }

    PlotModel getOverviewPlotModel()
    {
        return overviewModel;
    }

    public JsArray<Series> getWindowsSeries()
    {
        return windowModel.getSeries();
    }

    public JsArray<Series> getOverviewSeries()
    {
        return overviewModel.getSeries();
    }

    void setSelection( double x1, double x2 )
    {
        setSelection( x1, x2, null );
    }

    void setSelection( double x1, double x2, Command toExcuteAfterSelection )
    {
        selection[0] = x1;
        selection[1] = x2;

        Command command = null;
        if ( null != toExcuteAfterSelection )
        {
            command = new PopulateCommand( toExcuteAfterSelection, getHandlers().size() );
        }

        for ( PlotWithOverviewSeriesHandler handler : getHandlers() )
        {
            handler.populateWindowSeries( command );
        }
    }

    double[] getSelection()
    {
        return selection;
    }

    @Override
    public void removeSeries( int index )
    {
        super.removeSeries( index );
        windowModel.removeSeries( index );
        overviewModel.removeSeries( index );
    }

    @Override
    public void removeSeries( SeriesHandler series )
    {
        super.removeSeries( series );
        windowModel.removeSeries( series );
        overviewModel.removeSeries( series );
    }

    @Override
    public void removeAllSeries()
    {
        super.removeAllSeries();
        windowModel.removeAllSeries();
        overviewModel.removeAllSeries();
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public List<PlotWithOverviewSeriesHandler> getHandlers()
    {
        return (List<PlotWithOverviewSeriesHandler>) super.getHandlers();
    }

}
