package com.googlecode.gflot.examples.client.examples.hover;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotItem;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.PointsSeriesOptions;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( HoverPlace.UI_RAW_SOURCE_FILENAME )
public class HoverExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, HoverExample>
    {
    }

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    private static final String INSTRUCTIONS = "Point your mouse to a data point on the chart";

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    @UiField( provided = true )
    Label hoverPoint = new Label( INSTRUCTIONS );

    @UiField
    Label cursorPosition;

    public HoverExample( Resources resources )
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
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions.create()
            .setLineSeriesOptions( LineSeriesOptions.create().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( PointsSeriesOptions.create().setRadius( 2 ).setShow( true ) ).setShadowSize( 0d ) );
        // add tick formatter to the options
        plotOptions.addXAxisOptions( AxisOptions.create().setTicks( 12 ).setTickFormatter( new TickFormatter() {
            public String formatTickValue( double tickValue, Axis axis )
            {
                return MONTH_NAMES[(int) ( tickValue - 1 )];
            }
        } ) );

        // >>>>>>> You need make the grid hoverable <<<<<<<<<
        plotOptions.setGridOptions( GridOptions.create().setHoverable( true ) );
        // >>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

        // create a series
        SeriesHandler handler = model.addSeries( "Ottawa's Month Temperatures (Daily Average in &deg;C)", "#007f00" );

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
        plot = new SimplePlot( model, plotOptions );

        final PopupPanel popup = new PopupPanel();
        final Label label = new Label();
        popup.add( label );

        // add hover listener
        plot.addHoverListener( new PlotHoverListener() {
            public void onPlotHover( Plot plot, PlotPosition position, PlotItem item )
            {
                if ( position != null )
                {
                    cursorPosition.setText( "Position : {x=" + position.getX() + ", y=" + position.getY() + "}" );
                }
                if ( item != null )
                {
                    String text = "x: " + item.getDataPoint().getX() + ", y: " + item.getDataPoint().getY();

                    hoverPoint.setText( text );

                    label.setText( text );
                    popup.setPopupPosition( item.getPageX() + 10, item.getPageY() - 25 );
                    popup.show();
                }
                else
                {
                    hoverPoint.setText( INSTRUCTIONS );
                    popup.hide();
                }
            }
        }, false );

        return binder.createAndBindUi( this );
    }

}
