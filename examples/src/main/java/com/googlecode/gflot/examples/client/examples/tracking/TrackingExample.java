package com.googlecode.gflot.examples.client.examples.tracking;

import ca.nanometrics.gflot.client.Axes;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.SeriesData;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotItem;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.CrosshairOptions;
import ca.nanometrics.gflot.client.options.CrosshairOptions.Mode;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( TrackingPlace.UI_RAW_SOURCE_FILENAME )
public class TrackingExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, TrackingExample>
    {
    }

    /**
     * NumberFormat with two decimals
     */
    @GFlotExamplesData
    private static final NumberFormat numberFormat = NumberFormat.getFormat( "#0.00" );

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    /**
     * Label to show the tracking
     */
    @GFlotExamplesData
    @UiField
    Label tracker;

    /**
     * PlotModel
     */
    @GFlotExamplesData
    private PlotModel model;

    /**
     * If the timer is scheduled
     */
    @GFlotExamplesData
    private boolean updateTrackingTimeout = false;

    /**
     * Last mouse position
     */
    @GFlotExamplesData
    private PlotPosition latestPosition = null;

    /**
     * Timer to not calculate the data points at each mouse hover event
     */
    @GFlotExamplesData
    private Timer updateTrackingTimer = new Timer() {
        @Override
        public void run()
        {
            updateTracking();
        }
    };

    public TrackingExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @Override
    @GFlotExamplesSource
    public Widget createPlot()
    {
        model = new PlotModel();

        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setLineSeriesOptions( new LineSeriesOptions()
            .setShow( true ) ) );
        plotOptions.setCrosshairOptions( new CrosshairOptions().setMode( Mode.X ) );
        plotOptions.setGridOptions( new GridOptions().setHoverable( true ).setAutoHighlight( false ) );
        plotOptions.addYAxisOptions( new AxisOptions().setMinimum( -1.2 ).setMaximum( 1.2 ) );

        // create a series
        final SeriesHandler sin = model.addSeries( "sin(x)" );
        final SeriesHandler cos = model.addSeries( "cos(x)" );

        // add data
        for ( double i = 0; i < 14; i += 0.1 )
        {
            sin.add( new DataPoint( i, Math.sin( i ) ) );
            cos.add( new DataPoint( i, Math.cos( i ) ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        plot.addHoverListener( new PlotHoverListener() {
            @Override
            public void onPlotHover( Plot plot, PlotPosition position, PlotItem item )
            {
                latestPosition = position;
                if ( !updateTrackingTimeout )
                {
                    updateTrackingTimeout = true;
                    updateTrackingTimer.schedule( 100 );
                }
            }
        }, false );

        return binder.createAndBindUi( this );
    }

    /**
     * Update the tracking
     */
    @GFlotExamplesSource
    private void updateTracking()
    {
        updateTrackingTimeout = false;

        PlotPosition position = latestPosition;

        Axes axes = plot.getAxes();
        double xPos = position.getX();
        double yPos = position.getY();
        double xAxisMin = axes.getX().getMinimumValue();
        double xAxisMax = axes.getX().getMaximumValue();
        double yAxisMin = axes.getY().getMinimumValue();
        double yAxisMax = axes.getY().getMaximumValue();

        if ( xPos < xAxisMin || xPos > xAxisMax || yPos < yAxisMin || yPos > yAxisMax )
        {
            return;
        }

        int i = 0;
        int j = 0;
        Series[] dataset = model.getSeries();
        StringBuilder builder = new StringBuilder();
        for ( i = 0; i < dataset.length; i++ )
        {
            Series series = dataset[i];
            SeriesData data = series.getData();
            // find the nearest points, x-wise
            for ( j = 0; j < data.size(); j++ )
            {
                if ( data.getX( j ) > xPos )
                {
                    break;
                }
            }

            // now interpolate
            double y;
            DataPoint p1 = data.getDataPoint( j - 1 );
            DataPoint p2 = data.getDataPoint( j );
            if ( p1 == null )
            {
                y = p2.getY();
            }
            else if ( p2 == null )
            {
                y = p1.getY();
            }
            else
            {
                y = p1.getY() + ( p2.getY() - p1.getY() ) * ( xPos - p1.getX() ) / ( p2.getX() - p1.getX() );
            }

            if ( builder.length() > 0 )
            {
                builder.append( " | " );
            }
            builder.append( series.getLabel() );
            builder.append( " = " );
            builder.append( numberFormat.format( y ) );
        }
        tracker.setText( builder.toString() );
    }
}
