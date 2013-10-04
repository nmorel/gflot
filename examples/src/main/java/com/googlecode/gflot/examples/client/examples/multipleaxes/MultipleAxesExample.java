package com.googlecode.gflot.examples.client.examples.multipleaxes;

import java.util.Comparator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.Axis;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesData;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotItem;
import com.googlecode.gflot.client.event.PlotPosition;
import com.googlecode.gflot.client.jsni.Plot;
import com.googlecode.gflot.client.options.AbstractAxisOptions.AxisPosition;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.GridOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LegendOptions.LegendPosition;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.TickFormatter;
import com.googlecode.gflot.client.options.TimeSeriesAxisOptions;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( MultipleAxesPlace.UI_RAW_SOURCE_FILENAME )
public class MultipleAxesExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, MultipleAxesExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;
    /**
     * Loading mask
     */
    @GFlotExamplesData
    @UiField
    DivElement loadingMask;
    /**
     * Position label
     */
    @GFlotExamplesData
    @UiField
    Label cursorPosition;
    /**
     * Left button
     */
    @GFlotExamplesData
    @UiField
    Button left;
    /**
     * Right button
     */
    @GFlotExamplesData
    @UiField
    Button right;

    public MultipleAxesExample( Resources resources )
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

        plotOptions.setGridOptions( GridOptions.create().setHoverable( true ) );
        plotOptions.setLegendOptions( LegendOptions.create().setPosition( LegendPosition.SOUTH_WEST )
            .setSortedComparator( new Comparator<LegendOptions.LegendComparable>()
            {
                @Override
                public int compare( LegendOptions.LegendComparable o1, LegendOptions.LegendComparable o2 )
                {
                    return o2.getLabel().compareToIgnoreCase( o1.getLabel() );
                }
            } ) );

        // add tick formatter to the options
        plotOptions.addXAxisOptions( TimeSeriesAxisOptions.create() );
        plotOptions.addYAxisOptions( AxisOptions.create().setMinimum( 0 ) );
        plotOptions.addYAxisOptions( AxisOptions.create().setAlignTicksWithAxis( 1 ).setPosition( AxisPosition.RIGHT ).setTickDecimals( 3 )
            .setTickFormatter( new TickFormatter()
            {
                @Override
                public String formatTickValue( double tickValue, Axis axis )
                {
                    double round = Math.pow( 10, axis.getTickDecimals() );
                    return (Math.round( tickValue * round ) / round) + "\u20ac";
                }
            } ) );

        // create a series
        final SeriesHandler oilPrices = model.addSeries( Series.of( "Oil price ($)" ) );
        loadData( "multipleaxes/oilprices.json", new SeriesDataCallback()
        {
            @Override
            protected void onSuccess( SeriesData data )
            {
                oilPrices.setData( data );
                onDataLoaded();
            }
        } );

        final SeriesHandler exchangeRates = model.addSeries( Series.of( "USD/EUR exchange rate" ).setYAxis( 2 ) );
        loadData( "multipleaxes/exchangerates.json", new SeriesDataCallback()
        {
            @Override
            protected void onSuccess( SeriesData data )
            {
                exchangeRates.setData( data );
                onDataLoaded();
            }
        } );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        // add hover listener
        plot.addHoverListener( new PlotHoverListener()
        {
            public void onPlotHover( Plot plot, PlotPosition position, PlotItem item )
            {
                if ( position != null )
                {
                    cursorPosition.setText( "{x=" + position.getX() + ", y1=" + position.getY() + ", y2=" + position.getY( 2 ) + "}" );
                }
            }
        }, false );

        return binder.createAndBindUi( this );
    }

    /**
     * When user clicks on left button
     */
    @UiHandler( "left" )
    @GFlotExamplesSource
    void onClickLeft( ClickEvent e )
    {
        plot.getOptions().getYAxisOptions( 2 ).setPosition( AxisPosition.LEFT );
        plot.redraw();
        left.setEnabled( false );
        right.setEnabled( true );
    }

    /**
     * When user clicks on right button
     */
    @UiHandler( "right" )
    @GFlotExamplesSource
    void onClickRight( ClickEvent e )
    {
        plot.getOptions().getYAxisOptions( 2 ).setPosition( AxisPosition.RIGHT );
        plot.redraw();
        left.setEnabled( true );
        right.setEnabled( false );
    }

    /**
     * Called when data from a series is loaded
     */
    @GFlotExamplesSource
    private void onDataLoaded()
    {
        for ( SeriesHandler handler : plot.getModel().getHandlers() )
        {
            if ( handler.getData().isEmpty() )
            {
                return;
            }
        }
        left.setEnabled( true );
        loadingMask.getStyle().setDisplay( Display.NONE );
        plot.redraw();
    }

}
