package ca.nanometrics.gflot.sample.client.samples.interactivelegend;

import java.util.Date;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithInteractiveLegend;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions.PointSymbol;
import ca.nanometrics.gflot.client.options.TimeSeriesAxisOptions;
import ca.nanometrics.gflot.client.options.TimeSeriesAxisOptions.TickTimeUnit;
import ca.nanometrics.gflot.sample.client.mvp.DefaultActivity;
import ca.nanometrics.gflot.sample.client.resources.Resources;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Nicolas Morel
 */
public class InteractiveLegendExample
    extends DefaultActivity
{

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

    public InteractiveLegendExample( Resources resources )
    {
        super( resources );
    }

    @SuppressWarnings( "deprecation" )
    public Widget createWidget()
    {
        PlotModel model = new PlotModel( PlotModelStrategy.defaultStrategy() );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) ).setPointsOptions(
                new PointsSeriesOptions().setRadius( 3 ).setShow( true ).setSymbol( PointSymbol.DIAMOND ) ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // add tick formatter to the options
        plotOptions.addXAxisOptions( new TimeSeriesAxisOptions().setTickSize( 2, TickTimeUnit.MONTH ).setMonthNames( MONTH_NAMES ) );

        // create a series
        // Note: you need to specified the colors in other for the legend to
        // work properly
        SeriesHandler ottawaSeries = model.addSeries( "Ottawa", "#edc240" );
        SeriesHandler vancouverSeries =
            model.addSeries( new Series( "Vancouver" ).setColor( "#afd8f8" ).setPointsOptions( new PointsSeriesOptions().setShow( false ) ) );

        // add data
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 0, 1, 0, 0, 0 ), -10.5 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 1, 1, 0, 0, 0 ), -8.6 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 2, 1, 0, 0, 0 ), -2.4 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 3, 1, 0, 0, 0 ), 6 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 4, 1, 0, 0, 0 ), 13.6 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 5, 1, 0, 0, 0 ), 18.4 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 6, 1, 0, 0, 0 ), 21 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 7, 1, 0, 0, 0 ), 19.7 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 8, 1, 0, 0, 0 ), 14.7 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 9, 1, 0, 0, 0 ), 8.2 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 10, 1, 0, 0, 0 ), 1.5 ) );
        ottawaSeries.add( new DataPoint( Date.UTC( 110, 11, 1, 0, 0, 0 ), -6.6 ) );

        vancouverSeries.add( new DataPoint( Date.UTC( 110, 0, 1, 0, 0, 0 ), 4.8 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 1, 1, 0, 0, 0 ), 5.9 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 2, 1, 0, 0, 0 ), 7.6 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 3, 1, 0, 0, 0 ), 10 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 4, 1, 0, 0, 0 ), 13.2 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 5, 1, 0, 0, 0 ), 15.9 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 6, 1, 0, 0, 0 ), 18.1 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 7, 1, 0, 0, 0 ), 18.3 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 8, 1, 0, 0, 0 ), 15.4 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 9, 1, 0, 0, 0 ), 11.1 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 10, 1, 0, 0, 0 ), 7.1 ) );
        vancouverSeries.add( new DataPoint( Date.UTC( 110, 11, 1, 0, 0, 0 ), 4.8 ) );

        // create the plot
        SimplePlot plot = new SimplePlot( model, plotOptions );

        // put it on a panel
        FlowPanel panel = new FlowPanel();
        panel.add( new HTML(
            "<div style=\"font-weight: bold; align: center; margin: 0px 0px 5px 5px;\">Month Temperatures (Daily Average in &deg;C)</div>" ) );
        panel.add( new PlotWithInteractiveLegend( plot ) );

        return panel;
    }

}
