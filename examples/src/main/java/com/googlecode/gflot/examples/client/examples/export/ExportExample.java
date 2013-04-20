package com.googlecode.gflot.examples.client.examples.export;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.Axis;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.TickFormatter;
import com.googlecode.gflot.client.options.LegendOptions.LegendPosition;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( ExportPlace.UI_RAW_SOURCE_FILENAME )
public class ExportExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, ExportExample>
    {
    }

    /**
     * Month names used by the tick formatter
     */
    @GFlotExamplesData
    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    /**
     * Panel that will contain the exported image
     */
    @GFlotExamplesData
    @UiField
    SimplePanel imgPanel;

    public ExportExample( Resources resources )
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
        PlotOptions plotOptions = PlotOptions.create();

        // Enables the rendering of text in canvas to export the axis label too
        plotOptions.setCanvasEnabled( true );

        // Add tick formatter to the options
        plotOptions.addXAxisOptions( AxisOptions.create().setTicks( 12 ).setTickFormatter( new TickFormatter() {
            public String formatTickValue( double tickValue, Axis axis )
            {
                return MONTH_NAMES[(int) ( tickValue - 1 )];
            }
        } ) );
        plotOptions.setLegendOptions( LegendOptions.create().setBackgroundOpacity( 0 )
            .setPosition( LegendPosition.NORTH_WEST ) );

        // create a series
        SeriesHandler series1 = model.addSeries( Series.of( "Random Series 1" ) );
        SeriesHandler series2 = model.addSeries( Series.of( "Random Series 2" ) );

        // add data
        for ( int i = 1; i < 13; i++ )
        {
            series1.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
            series2.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
        }

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

    /**
     * When the user clicks on the create image button
     */
    @GFlotExamplesSource
    @UiHandler( "createImage" )
    void onClickCreateImage( ClickEvent e )
    {
        if ( plot.isExportAsImageEnabled() )
        {
            imgPanel.setWidget( plot.getImage() );
        }
        else
        {
            Window.alert( "The export option isn't supported by your browser." );
        }
    }

    /**
     * When the user clicks on the save image button
     */
    @GFlotExamplesSource
    @UiHandler( "saveImage" )
    void onClickSaveImage( ClickEvent e )
    {
        if ( plot.isExportAsImageEnabled() )
        {
            plot.saveAsImage();
        }
        else
        {
            Window.alert( "The export option isn't supported by your browser." );
        }
    }

}
