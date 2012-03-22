package com.googlecode.gflot.examples.client.examples.bar;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions;
import ca.nanometrics.gflot.client.options.BarSeriesOptions.BarAlignment;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public BarExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();

        BarSeriesOptions barSeriesOptions = new BarSeriesOptions();
        barSeriesOptions.setShow( true );
        barSeriesOptions.setLineWidth( 1 );
        barSeriesOptions.setBarWidth( 1 );
        barSeriesOptions.setAlignment( BarAlignment.CENTER );

        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setBarsSeriesOptions( barSeriesOptions ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // add tick formatter to the options
        plotOptions.addXAxisOptions( new AxisOptions().setTicks( 12 ).setTickFormatter( new TickFormatter()
        {
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
        handler.add( new DataPoint( 1, -10.5 ) );
        handler.add( new DataPoint( 2, -8.6 ) );
        handler.add( new DataPoint( 3, -2.4 ) );
        handler.add( new DataPoint( 4, 6 ) );
        handler.add( new DataPoint( 5, 13.6 ) );
        handler.add( new DataPoint( 6, 18.4 ) );
        handler.add( new DataPoint( 7, 21 ) );
        handler.add( new DataPoint( 8, 19.7 ) );
        handler.add( new DataPoint( 9, 14.7 ) );
        handler.add( new DataPoint( 10, 8.2 ) );
        handler.add( new DataPoint( 11, 1.5 ) );
        handler.add( new DataPoint( 12, -6.6 ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

}
