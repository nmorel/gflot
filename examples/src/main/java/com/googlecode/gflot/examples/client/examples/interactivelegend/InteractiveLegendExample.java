package com.googlecode.gflot.examples.client.examples.interactivelegend;

import java.util.Date;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.PlotWithInteractiveLegend;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LineSeriesOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.PointsSeriesOptions;
import com.googlecode.gflot.client.options.TimeSeriesAxisOptions;
import com.googlecode.gflot.client.options.PointsSeriesOptions.PointSymbol;
import com.googlecode.gflot.client.options.TimeSeriesAxisOptions.TickTimeUnit;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( InteractiveLegendPlace.UI_RAW_SOURCE_FILENAME )
public class InteractiveLegendExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, InteractiveLegendExample>
    {
    }

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    PlotWithInteractiveLegend plot;

    public InteractiveLegendExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    @SuppressWarnings( "deprecation" )
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions
            .create()
            .setLineSeriesOptions( LineSeriesOptions.create().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions(
                PointsSeriesOptions.create().setRadius( 3 ).setShow( true ).setSymbol( PointSymbol.DIAMOND ) ) );
        plotOptions.setLegendOptions( LegendOptions.create().setShow( false ) );

        // add tick formatter to the options
        plotOptions.addXAxisOptions( TimeSeriesAxisOptions.create().setTickSize( 2, TickTimeUnit.MONTH )
            .setMonthNames( MONTH_NAMES ) );

        plotOptions.addYAxisOptions( AxisOptions.create().setLabel( "Temperature (C\u00b0)" ) );

        // create a series
        SeriesHandler ottawaSeries = model.addSeries( Series.of( "Ottawa" ).setColor( "red" ) );
        SeriesHandler vancouverSeries =
            model
                .addSeries( Series.of( "Vancouver" ).setPointsOptions( PointsSeriesOptions.create().setShow( false ) ) );

        // add data
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 0, 1, 0, 0, 0 ), -10.5 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 1, 1, 0, 0, 0 ), -8.6 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 2, 1, 0, 0, 0 ), -2.4 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 3, 1, 0, 0, 0 ), 6 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 4, 1, 0, 0, 0 ), 13.6 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 5, 1, 0, 0, 0 ), 18.4 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 6, 1, 0, 0, 0 ), 21 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 7, 1, 0, 0, 0 ), 19.7 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 8, 1, 0, 0, 0 ), 14.7 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 9, 1, 0, 0, 0 ), 8.2 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 10, 1, 0, 0, 0 ), 1.5 ) );
        ottawaSeries.add( DataPoint.of( Date.UTC( 110, 11, 1, 0, 0, 0 ), -6.6 ) );

        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 0, 1, 0, 0, 0 ), 4.8 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 1, 1, 0, 0, 0 ), 5.9 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 2, 1, 0, 0, 0 ), 7.6 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 3, 1, 0, 0, 0 ), 10 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 4, 1, 0, 0, 0 ), 13.2 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 5, 1, 0, 0, 0 ), 15.9 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 6, 1, 0, 0, 0 ), 18.1 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 7, 1, 0, 0, 0 ), 18.3 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 8, 1, 0, 0, 0 ), 15.4 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 9, 1, 0, 0, 0 ), 11.1 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 10, 1, 0, 0, 0 ), 7.1 ) );
        vancouverSeries.add( DataPoint.of( Date.UTC( 110, 11, 1, 0, 0, 0 ), 4.8 ) );

        // create the plot
        plot = new PlotWithInteractiveLegend( new SimplePlot( model, plotOptions ) );

        return binder.createAndBindUi( this );
    }
}
