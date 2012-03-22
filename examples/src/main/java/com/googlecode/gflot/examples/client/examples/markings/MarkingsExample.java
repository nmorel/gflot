package com.googlecode.gflot.examples.client.examples.markings;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.PlotWithOverview;
import ca.nanometrics.gflot.client.PlotWithOverviewModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotItem;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.Marking;
import ca.nanometrics.gflot.client.options.Markings;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.Range;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
public class MarkingsExample
    extends DefaultActivity
{
    private static final String INSTRUCTION = "Hover over a point";

    public MarkingsExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createWidget()
    {
        final Label selectedPointLabel = new Label( INSTRUCTION );

        PlotWithOverviewModel model = new PlotWithOverviewModel( PlotModelStrategy.defaultStrategy() );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 1 ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        final PlotWithOverview plot = new PlotWithOverview( model, plotOptions );
        // add hover listener
        plot.addHoverListener( new PlotHoverListener()
        {
            public void onPlotHover( Plot plot, PlotPosition position, PlotItem item )
            {
                if ( item != null )
                {
                    selectedPointLabel.setText( "x: " + item.getDataPoint().getX() + ", y: "
                        + item.getDataPoint().getY() );
                }
                else
                {
                    selectedPointLabel.setText( INSTRUCTION );
                }
            }
        }, false );

        SeriesHandler s = plot.getModel().addSeries( "Series 1" );
        s.add( new DataPoint( 1, 2 ) );
        s.add( new DataPoint( 2, 5 ) );
        s.add( new DataPoint( 3, 7 ) );
        s.add( new DataPoint( 4, 5 ) );
        s.add( new DataPoint( 5, 3 ) );
        s.add( new DataPoint( 6, 2 ) );
        s.add( new DataPoint( 7, 5 ) );
        s.add( new DataPoint( 8, 7 ) );
        s.add( new DataPoint( 9, 5 ) );
        s.add( new DataPoint( 10, 3 ) );

        // Start of Marking Code
        Marking m = new Marking();
        m.setX( new Range( 2, 4 ) );
        m.setColor( "#3BEFc3" );

        Marking m2 = new Marking();
        m2.setX( new Range( 5, 7 ) );
        m2.setColor( "#cccccc" );

        Marking m3 = new Marking();
        Range a = new Range();
        a.setFrom( 8 );
        m3.setX( a );
        m3.setColor( "#000000" );

        Markings ms = new Markings();
        ms.addMarking( m );
        ms.addMarking( m2 );
        ms.addMarking( m3 );
        // End of Marking Code

        // Add Markings Objects to Grid Options
        plotOptions.setGridOptions( new GridOptions().setHoverable( true ).setMarkings( ms ) );

        plot.setHeight( 250 );
        plot.setOverviewHeight( 60 );

        plot.setLinearSelection( 1, 8 );

        FlowPanel panel = new FlowPanel();
        panel.add( selectedPointLabel );
        panel.add( plot );
        return panel;
    }

}
