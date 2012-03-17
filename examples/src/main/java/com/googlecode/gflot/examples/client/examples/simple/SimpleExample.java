package com.googlecode.gflot.examples.client.examples.simple;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
public class SimpleExample
    extends DefaultActivity
{

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

    public SimpleExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createWidget()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = new PlotOptions();

        // add tick formatter to the options
        plotOptions.addXAxisOptions( new AxisOptions().setTicks( 12 ).setTickFormatter( new TickFormatter()
        {
            public String formatTickValue( double tickValue, Axis axis )
            {
                return MONTH_NAMES[(int) ( tickValue - 1 )];
            }
        } ) );

        plotOptions.setGridOptions( new GridOptions().setAboveData( true ) );

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
        SimplePlot plot = new SimplePlot( model, plotOptions );

        // put it on a panel
        FlowPanel panel = new FlowPanel();
        panel.add( plot );
        return panel;
    }

}
