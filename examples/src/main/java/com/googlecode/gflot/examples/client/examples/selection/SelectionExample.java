package com.googlecode.gflot.examples.client.examples.selection;

import ca.nanometrics.gflot.client.Axis;
import ca.nanometrics.gflot.client.DataPoint;
import ca.nanometrics.gflot.client.PlotModel;
import ca.nanometrics.gflot.client.PlotSelectionArea;
import ca.nanometrics.gflot.client.SeriesHandler;
import ca.nanometrics.gflot.client.SimplePlot;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotItem;
import ca.nanometrics.gflot.client.event.PlotPosition;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.jsni.Plot;
import ca.nanometrics.gflot.client.options.AxisOptions;
import ca.nanometrics.gflot.client.options.GridOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.Range;
import ca.nanometrics.gflot.client.options.SelectionOptions;
import ca.nanometrics.gflot.client.options.SelectionOptions.SelectionMode;
import ca.nanometrics.gflot.client.options.TickFormatter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
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
@GFlotExamplesRaw( SelectionPlace.UI_RAW_SOURCE_FILENAME )
public class SelectionExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, SelectionExample>
    {
    }

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    private static final String SELECTING = "Selecting :";

    private static final String SELECTED = "Selected :";

    private static final String UNSELECTED = "No selection";

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    @UiField
    Label selectionLabel;

    @UiField
    Button clear;

    public SelectionExample( Resources resources )
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

        // add tick formatter to the options
        plotOptions.addXAxisOptions( new AxisOptions().setTicks( 12 ).setTickFormatter( new TickFormatter()
        {
            public String formatTickValue( double tickValue, Axis axis )
            {
                return MONTH_NAMES[(int) ( tickValue - 1 )];
            }
        } ) );

        plotOptions.setSelectionOptions( new SelectionOptions().setMode( SelectionMode.XY ).setColor( "red" ) );
        plotOptions.setGridOptions( new GridOptions().setHoverable( true ) );

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
        plot = new SimplePlot( model, plotOptions );

        final PopupPanel popup = new PopupPanel();
        final Label hoverLabel = new Label( "You are hovering the selected zone" );
        popup.setWidget( hoverLabel );

        plot.addHoverListener( new PlotHoverListener()
        {
            @Override
            public void onPlotHover( Plot plott, PlotPosition position, PlotItem item )
            {
                PlotSelectionArea area = plot.getSelection();
                if ( null == area )
                {
                    popup.hide();
                    return;
                }
                double xFrom = area.getX().getFrom();
                double xTo = area.getX().getTo();
                double yFrom = area.getY().getFrom();
                double yTo = area.getY().getTo();
                if ( xFrom <= position.getX() && xTo >= position.getX() && yFrom <= position.getY()
                    && yTo >= position.getY() )
                {
                    popup.setPopupPosition( position.getPageX() + 10, position.getPageY() - 25 );
                    popup.show();
                }
                else
                {
                    popup.hide();
                }
            }
        }, false );

        plot.addSelectedListener( new PlotSelectedListener()
        {
            @Override
            public void onPlotSelected( PlotSelectionArea area )
            {
                selectionLabel.setText( buildSelectString( SELECTED, area ) );
                clear.setEnabled( true );
            }
        } );
        plot.addSelectingListener( new PlotSelectingListener()
        {
            @Override
            public void onPlotSelecting( PlotSelectionArea area )
            {
                selectionLabel.setText( buildSelectString( SELECTING, area ) );
            }
        } );
        plot.addUnselectedListener( new PlotUnselectedListener()
        {
            @Override
            public void onPlotUnselected()
            {
                selectionLabel.setText( UNSELECTED );
                clear.setEnabled( false );
            }
        } );

        plot.setSelection( new PlotSelectionArea().setX( new Range( 2, 4 ) ).setY( new Range( 0, 20 ) ), false );

        return binder.createAndBindUi( this );
    }

    private String buildSelectString( String start, PlotSelectionArea area )
    {
        StringBuilder builder = new StringBuilder( start );
        builder.append( "x=[from:\"" ).append( area.getX().getFrom() ).append( "\", to=\"" )
            .append( area.getX().getTo() ).append( "\"], y=[from:\"" ).append( area.getY().getFrom() )
            .append( "\", to=\"" ).append( area.getY().getTo() ).append( "\"]" );
        return builder.toString();
    }

    @UiHandler( "clear" )
    void onClickClear( ClickEvent e )
    {
        plot.clearSelection( false );
        selectionLabel.setText( UNSELECTED );
        clear.setEnabled( false );
    }

}
