package com.googlecode.gflot.examples.client.examples.markings;

import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.PlotModelStrategy;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.Marking;
import ca.nanometrics.gflot.client.options.Markings;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.Range;

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
@GFlotExamplesRaw( MarkingsPlace.UI_RAW_SOURCE_FILENAME )
public class MarkingsExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, MarkingsExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public MarkingsExample( Resources resources )
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
        PlotModel model = new PlotModel( PlotModelStrategy.defaultStrategy() );
        PlotOptions plotOptions = new PlotOptions();
        plotOptions.setGlobalSeriesOptions( new GlobalSeriesOptions()
            .setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( new PointsSeriesOptions().setRadius( 2 ).setShow( true ) ).setShadowSize( 1 ) );
        plotOptions.setLegendOptions( new LegendOptions().setShow( false ) );

        SeriesHandler s = model.addSeries( "Series 1" );
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
        Marking m = new Marking().setX( new Range( 2, 2 ) ).setLineWidth( 2 ).setColor( "#3BEFc3" );
        Marking m2 = new Marking().setX( new Range( 5, 7 ) ).setY( new Range( 2, 6 ) ).setColor( "#cccccc" );
        Marking m3 = new Marking().setX( new Range().setFrom( 8 ) ).setColor( "#000000" );
        // End of Marking Code

        // Add Markings Objects to Grid Options
        plotOptions.setGridOptions( new GridOptions().setMarkings( new Markings().addMarking( m ).addMarking( m2 )
            .addMarking( m3 ) ) );

        // create plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

}
