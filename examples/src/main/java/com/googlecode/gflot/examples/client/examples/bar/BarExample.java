package com.googlecode.gflot.examples.client.examples.bar;

import java.util.ArrayList;
import java.util.List;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions.BarAlignment;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.Marking;
import ca.nanometrics.gflot.client.options.Markings;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.Range;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( BarPlace.UI_RAW_SOURCE_FILENAME )
public class BarExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, BarExample>
    {
    }

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    /**
     * First Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot1;

    /**
     * Second Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot2;

    public BarExample( Resources resources )
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
        createFirstPlot();
        createSecondPlot();

        return binder.createAndBindUi( this );
    }

    /**
     * Create the first plot
     */
    @GFlotExamplesSource
    private void createFirstPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();

        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setBarsSeriesOptions( new BarSeriesOptions()
            .setShow( true ).setLineWidth( 1 ).setBarWidth( 1 ).setAlignment( BarAlignment.CENTER ) ) );

        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // add tick formatter to the options
        plotOptions.addXAxisOptions( new AxisOptions().setTicks( 12 ).setTickFormatter( new TickFormatter() {
            @Override
            public String formatTickValue( double tickValue, Axis axis )
            {
                if ( tickValue > 0 && tickValue <= 12 )
                {
                    return MONTH_NAMES[(int) ( tickValue - 1 )];
                }
                return "";
            }
        } ) );

        // create a series
        SeriesHandler handler = model.addSeries( "Ottawa's Month Temperatures (Daily Average in &deg;C)", "blue" );

        // add data
        handler.add( DataPoint.of( 1, -10.5 ) );
        handler.add( DataPoint.of( 2, -8.6 ) );
        handler.add( DataPoint.of( 3, -2.4 ) );
        handler.add( DataPoint.of( 4, 6 ) );
        handler.add( DataPoint.of( 5, 13.6 ) );
        handler.add( DataPoint.of( 6, 18.4 ) );
        handler.add( DataPoint.of( 7, 21 ) );
        handler.add( DataPoint.of( 8, 19.7 ) );
        handler.add( DataPoint.of( 9, 14.7 ) );
        handler.add( DataPoint.of( 10, 8.2 ) );
        handler.add( DataPoint.of( 11, 1.5 ) );
        handler.add( DataPoint.of( 12, -6.6 ) );

        // create the plot
        plot1 = new SimplePlot( model, plotOptions );
    }

    /**
     * Create the second plot
     */
    @GFlotExamplesSource
    private void createSecondPlot()
    {
        int nbSeries = 4;

        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();

        GlobalSeriesOptions globalSeriesOptions = new GlobalSeriesOptions();
        globalSeriesOptions.setBarsSeriesOptions( new BarSeriesOptions().setShow( true ).setBarWidth( 0.9 / nbSeries ) );


        // Activate the plugin for multiple bars support
        plotOptions.setMultipleBars( true );
        // Activate the patch for flot. see http://code.google.com/p/flot/issues/detail?id=159
        globalSeriesOptions.setMultipleBars( true );

        plotOptions.setGlobalSeriesOptions( globalSeriesOptions );

        plotOptions.setLegendOptions( new LegendOptions().setMargin( -120, 0 ) );

        // create a series
        List<SeriesHandler> series = new ArrayList<SeriesHandler>();
        for ( int i = 0; i < nbSeries; i++ )
        {
            series.add( model.addSeries( "Random series " + ( i + 1 ) ) );
        }

        Markings markings = new Markings();

        // add data
        for ( int i = 0; i < 10; i++ )
        {
            for ( SeriesHandler serie : series )
            {
                serie.add( DataPoint.of( i, Random.nextInt( 50 ) ) );
            }
            if ( i % 2 != 0 )
            {
                markings.addMarking( new Marking().setX( new Range( i - 0.5, i + 0.5 ) ).setColor(
                    "rgba(153, 153, 153, 0.3)" ) );
            }
        }
        plotOptions.setGridOptions( new GridOptions().setMarkings( markings ) );

        // create the plot
        plot2 = new SimplePlot( model, plotOptions );
    }

}
