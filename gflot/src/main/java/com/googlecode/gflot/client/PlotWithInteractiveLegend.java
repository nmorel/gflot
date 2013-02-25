/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.PlotModel.PlotModelListener;
import com.googlecode.gflot.client.event.PlotClickListener;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotLoadEvent;
import com.googlecode.gflot.client.event.PlotRedrawEvent;
import com.googlecode.gflot.client.event.PlotSelectedListener;
import com.googlecode.gflot.client.event.PlotSelectingListener;
import com.googlecode.gflot.client.event.PlotUnselectedListener;
import com.googlecode.gflot.client.jsni.JsonObject;
import com.googlecode.gflot.client.jsni.Plot;

/**
 * @author Alexander De Leon
 */
public class PlotWithInteractiveLegend
    extends Composite
    implements PlotWidget, PlotModelListener, PlotLoadEvent.Handler, PlotRedrawEvent.Handler
{

    protected final PlotWidget plot;
    protected Panel legendPanel;
    private final Map<SeriesHandler, LegendItem> legend;

    public PlotWithInteractiveLegend( PlotWidget plot )
    {
        legend = new HashMap<SeriesHandler, LegendItem>();
        this.plot = plot;
        addLoadHandler( this );
        addRedrawHandler( this );
        initWidget( createUi() );
        buildLegendFromModel( plot.getModel() );
    }

    private void buildLegendFromModel( PlotModel model )
    {
        for ( SeriesHandler seriesHandler : model.getHandlers() )
        {
            addSeriesToLegend( seriesHandler );
        }
        model.addListener( this );
    }

    public void addClickListener( PlotClickListener listener, boolean onlyOnDatapoint )
    {
        plot.addClickListener( listener, onlyOnDatapoint );
    }

    public void addHoverListener( PlotHoverListener listener, boolean onlyOnDatapoint )
    {
        plot.addHoverListener( listener, onlyOnDatapoint );
    }

    public void addSelectedListener( PlotSelectedListener listener )
    {
        plot.addSelectedListener( listener );
    }

    @Override
    public void addSelectingListener( final PlotSelectingListener listener )
    {
        plot.addSelectingListener( listener );
    }

    @Override
    public void addUnselectedListener( final PlotUnselectedListener listener )
    {
        plot.addUnselectedListener( listener );
    }

    @Override
    public PlotSelectionArea getSelection()
    {
        return plot.getSelection();
    }

    @Override
    public void setSelection( PlotSelectionArea area )
    {
        plot.setSelection( area );
    }

    @Override
    public void setSelection( PlotSelectionArea area, boolean preventEvent )
    {
        plot.setSelection( area, preventEvent );
    }

    @Override
    public void clearSelection()
    {
        plot.clearSelection();
    }

    @Override
    public void clearSelection( boolean preventEvent )
    {
        plot.clearSelection( preventEvent );
    }

    public int getHeight()
    {
        return plot.getHeight();
    }

    public int getWidth()
    {
        return plot.getWidth();
    }

    public void setHeight( int height )
    {
        plot.setHeight( height );
    }

    public void setLinearSelection( double x1, double x2 )
    {
        plot.setLinearSelection( x1, x2 );
    }

    public void setRectangularSelection( double x1, double y1, double x2, double y2 )
    {
        plot.setRectangularSelection( x1, y1, x2, y2 );
    }

    public void setWidth( int width )
    {
        plot.setWidth( width );
    }

    public void redraw()
    {
        plot.redraw();
    }

    public void redraw( boolean force )
    {
        plot.redraw( force );
    }

    public Widget getWidget()
    {
        return this;
    }

    public PlotModel getModel()
    {
        return plot.getModel();
    }

    public void onAddSeries( PlotModel model, SeriesHandler handler )
    {
        addSeriesToLegend( handler );
    }

    public void onRemoveSeries( PlotModel model, SeriesHandler handler )
    {
        legendPanel.remove( legend.get( handler ) );
        legend.remove( handler );
    }

    /* --------------------- helper methods -- */
    private void addSeriesToLegend( final SeriesHandler handler )
    {
        LegendItem item = createLegendItem();
        item.addValueChangeHandler( new ValueChangeHandler<Boolean>()
        {
            @Override
            public void onValueChange( ValueChangeEvent<Boolean> event )
            {
                handler.setVisible( event.getValue() );
                plot.redraw();
            }
        } );
        legend.put( handler, item );
        legendPanel.add( item );
    }

    protected LegendItem createLegendItem()
    {
        return new DefaultLegendItem();
    }

    protected Widget createUi()
    {
        VerticalPanel panel = new VerticalPanel();

        legendPanel = new HorizontalPanel();
        legendPanel.getElement().getStyle().setMarginBottom( 5, Unit.PX );

        panel.add( legendPanel );
        panel.add( plot );

        return panel;
    }

    public void setSerieVisible( SeriesHandler handler, boolean visible, boolean redraw )
    {
        handler.setVisible( visible );
        legend.get( handler ).setValue( visible, false );
        if ( redraw )
        {
            plot.redraw( true );
        }
    }

    protected abstract class LegendItem
        extends Composite
        implements HasValue<Boolean>
    {
        protected LegendItem()
        {
            init();
        }

        protected abstract void init();

        protected abstract void update( String color, String label );
    }

    /**
     * Class for the legend item widget
     */
    protected class DefaultLegendItem
        extends LegendItem
    {
        protected static final String COLOR_BAND_HEIGHT = "3px";

        protected SimplePanel panel;

        protected CheckBox checkBox;

        @Override
        protected void init()
        {
            panel = new SimplePanel();
            panel.getElement().getStyle().setPaddingTop( 2, Unit.PX );
            panel.getElement().getStyle().setMarginRight( 5, Unit.PX );
            panel.getElement().getStyle().setProperty( "borderTopStyle", BorderStyle.SOLID.getCssName() );
            panel.getElement().getStyle().setProperty( "borderTopWidth", 3, Unit.PX );

            checkBox = new CheckBox();
            checkBox.setValue( true );

            panel.setWidget( checkBox );

            initWidget( panel );
        }

        @Override
        protected void update( String color, String seriesLabel )
        {
            panel.getElement().getStyle().setProperty( "borderTopColor", color );
            checkBox.setText( seriesLabel );
        }

        @Override
        public HandlerRegistration addValueChangeHandler( ValueChangeHandler<Boolean> handler )
        {
            return checkBox.addValueChangeHandler( handler );
        }

        @Override
        public Boolean getValue()
        {
            return checkBox.getValue();
        }

        @Override
        public void setValue( Boolean value )
        {
            checkBox.setValue( value );
        }

        @Override
        public void setValue( Boolean value, boolean fireEvents )
        {
            checkBox.setValue( value, fireEvents );
        }

    }

    @Override
    public HandlerRegistration addLoadHandler( PlotLoadEvent.Handler handler )
    {
        return plot.addLoadHandler( handler );
    }

    @Override
    public HandlerRegistration addRedrawHandler( PlotRedrawEvent.Handler handler )
    {
        return plot.addRedrawHandler( handler );
    }

    @Override
    public void onLoad( PlotLoadEvent event )
    {
        updateLegend();
    }

    @Override
    public void onRedraw( PlotRedrawEvent event )
    {
        updateLegend();
    }

    private void updateLegend()
    {
        for ( Entry<SeriesHandler, LegendItem> entry : legend.entrySet() )
        {
            int index = plot.getModel().indexOf( entry.getKey() );
            JsonObject series = plot.getPlot().getData().get( index );

            // if no data is given to flot, it puts the label inside a data object...
            String label = null;
            if ( series.hasKey( "label" ) )
            {
                label = series.getString( "label" );
            }
            else if ( series.hasKey( "data" ) )
            {
                JsonObject data = series.getJsObject( "data" );
                if ( data.hasKey( "label" ) )
                {
                    label = data.getString( "label" );
                }
            }

            String color = series.getString( "color" );
            entry.getValue().update( color, label );
        }
    }

    @Override
    public Plot getPlot()
    {
        return plot.getPlot();
    }

}
