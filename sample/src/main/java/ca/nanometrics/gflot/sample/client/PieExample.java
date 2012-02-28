package ca.nanometrics.gflot.sample.client;

import ca.nanometrics.gflot.client.PieDataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.PieSeriesOptions;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Background;
import ca.nanometrics.gflot.client.options.PieSeriesOptions.Label.Formatter;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class PieExample
    implements GFlotExample
{
    public String getName()
    {
        return "Pie";
    }

    public Widget createExample()
    {
        final PlotModel model = new PlotModel();
        final PlotOptions plotOptions = new PlotOptions();

        // activate the pie
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions().setPieSeriesOptions( new PieSeriesOptions()
            .setShow( true )
            .setRadius( 1 )
            .setInnerRadius( 0.2 )
            .setLabel(
                new Label().setShow( true ).setRadius( 3d / 4d ).setBackground( new Background().setOpacity( 0.8 ) ).setFormatter( new Formatter()
                {
                    @Override
                    public String format( String label, Series series )
                    {
                        return "<div style=\"font-size:8pt;text-align:center;padding:2px;color:white;\">" + label + "<br/>" + series.getPercent()
                            + "%</div>";
                    }
                } ) ) ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        // create series
        int nbSeries = Random.nextInt( 4 ) + 2;
        for ( int i = 0; i < nbSeries; i++ )
        {
            SeriesHandler handlerLine = model.addSeries( "Series " + ( i + 1 ) );
            handlerLine.add( new PieDataPoint( Random.nextInt( 100 ) + 1 ) );
        }

        SimplePlot plot = new SimplePlot( model, plotOptions );
        plot.setSize( "400px", "300px" );
        final FlowPanel panel = new FlowPanel();
        panel.add( plot );
        return panel;

    }
}
