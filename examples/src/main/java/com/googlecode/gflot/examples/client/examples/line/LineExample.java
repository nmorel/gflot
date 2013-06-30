package com.googlecode.gflot.examples.client.examples.line;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.FontOptions;
import com.googlecode.gflot.client.options.GridOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.LegendOptions.LegendPosition;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( LinePlace.UI_RAW_SOURCE_FILENAME )
public class LineExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, LineExample>
    {
    }

    interface Style extends CssResource{
        String button();
        String darkTheme();
        String whiteTheme();
        String legendLabel();
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    /**
     * Button switch to dark
     */
    @GFlotExamplesData
    @UiField
    Button switchDark;

    /**
     * Button switch to white
     */
    @GFlotExamplesData
    @UiField
    Button switchWhite;

    /**
     * Access to UiBinder style
     */
    @GFlotExamplesData
    @UiField
    Style style;


    public LineExample( Resources resources )
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
        plotOptions.setLegendOptions( LegendOptions.create().setBackgroundOpacity( 0 )
            .setPosition( LegendPosition.NORTH_WEST ) );
        plotOptions.setGridOptions( GridOptions.create().setMargin( 5 ) );
        plotOptions.addXAxisOptions( AxisOptions.create().setFont( FontOptions.create().setColor("black").setWeight( "bold" ).setStyle( "italic" ) ) );
        plotOptions.addYAxisOptions( AxisOptions.create().setFont( FontOptions.create().setColor( "black" ).setWeight( "bold" ).setStyle( "italic" ) ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        // add data
        generateRandomData();

        return binder.createAndBindUi( this );
    }

    /**
     * On click on the generate button, we clear the current data and generate new ones
     *
     * @param e click event
     */
    @GFlotExamplesSource
    @UiHandler( "generate" )
    void onClickGenerate( ClickEvent e )
    {
        plot.getModel().removeAllSeries();
        generateRandomData();
        plot.redraw();
    }

    /**
     * Generate random data
     */
    @GFlotExamplesSource
    private void generateRandomData()
    {
        int nbSeries = Random.nextInt( 5 ) + 1;
        for ( int i = 0; i < nbSeries; i++ )
        {
            plot.getModel().addSeries( Series.of( "Random Series " + i ) );
        }
        for ( int i = 1; i < 13; i++ )
        {
            for ( SeriesHandler series : plot.getModel().getHandlers() )
            {
                series.add( DataPoint.of( i, Random.nextInt( 30 ) ) );
            }
        }
    }

    /**
     * Switch to dark theme
     *
     * @param e click event
     */
    @GFlotExamplesSource
    @UiHandler( "switchDark" )
    void onClickSwitchToDark( ClickEvent e )
    {
        switchDark.setVisible( false );
        switchWhite.setVisible( true );

        plot.removeStyleName( style.whiteTheme() );
        plot.addStyleName( style.darkTheme() );
        plot.getPlotOptions().getXAxisOptions().getFont().setColor( "white" );
        plot.getPlotOptions().getXAxisOptions().setTickColor( "rgba(255, 255, 255, 0.6)" );
        plot.getPlotOptions().getYAxisOptions().getFont().setColor( "white" );
        plot.getPlotOptions().getYAxisOptions().setTickColor( "rgba(255, 255, 255, 0.6)" );
        plot.getPlotOptions().getGridOptions().setBorderColor( "white" );
        plot.redraw(true);
    }

    /**
     * Switch to white theme
     *
     * @param e click event
     */
    @GFlotExamplesSource
    @UiHandler( "switchWhite" )
    void onClickSwitchToWhite( ClickEvent e )
    {
        switchDark.setVisible( true );
        switchWhite.setVisible( false );

        plot.removeStyleName( style.darkTheme() );
        plot.addStyleName( style.whiteTheme() );
        plot.getPlotOptions().getXAxisOptions().getFont().setColor( "black" );
        plot.getPlotOptions().getXAxisOptions().clearTickColor();
        plot.getPlotOptions().getYAxisOptions().getFont().setColor( "black" );
        plot.getPlotOptions().getYAxisOptions().clearTickColor();
        plot.getPlotOptions().getGridOptions().clearBorderColor();
        plot.redraw( true );
    }

}
