/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
import ca.nanometrics.gflot.client.options.Range;
import ca.nanometrics.gflot.client.options.SelectionOptions;
import ca.nanometrics.gflot.client.options.SelectionOptions.SelectionMode;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class PlotWithOverview
    extends Composite
    implements PlotWidget, PlotSelectedListener
{
    public static final int DEFAULT_OVERVIEW_HEIGHT = 100; // px

    public static final PlotOptions DEFAULT_OVERVIEW_OPTIONS = PlotOptions
        .create()
        .setLegendOptions( LegendOptions.create().setShow( false ) )
        .setGlobalSeriesOptions(
            GlobalSeriesOptions.create()
                .setLineSeriesOptions( LineSeriesOptions.create().setLineWidth( 1 ).setFill( true ) )
                .setShadowSize( 0d ) ).setSelectionOptions( SelectionOptions.create().setMode( SelectionMode.X ) );

    private final SimplePlot windowPlot;

    private final SimplePlot overviewPlot;

    private final PlotWithOverviewModel model;

    public PlotWithOverview( PlotWithOverviewModel model )
    {
        this( model, PlotOptions.create() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions plotOptions )
    {
        this( model, plotOptions, DEFAULT_OVERVIEW_OPTIONS );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions, PlotOptions overviewPlotOptions )
    {
        this.model = model;
        this.windowPlot = new SimplePlot( model.getWindowPlotModel(), windowPlotOptions );
        this.overviewPlot = new SimplePlot( model.getOverviewPlotModel(), overviewPlotOptions );
        setupPlots();
        initWidget( createUi() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions, Element windowPlotContainer,
                             Element overviewPlotContainer )
    {
        this.model = model;
        this.windowPlot = new SimplePlot( windowPlotContainer, model.getWindowPlotModel(), windowPlotOptions );
        this.overviewPlot = new SimplePlot( overviewPlotContainer, model.getOverviewPlotModel(), null );
        setupPlots();
        initWidget( createUi() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions,
                             PlotOptions overviewPlotOptions, Element windowPlotContainer, Element overviewPlotContainer )
    {
        this.model = model;
        this.windowPlot = new SimplePlot( windowPlotContainer, model.getWindowPlotModel(), windowPlotOptions );
        this.overviewPlot = new SimplePlot( overviewPlotContainer, model.getOverviewPlotModel(), overviewPlotOptions );
        setupPlots();
        initWidget( createUi() );
    }

    /* ---------------------- PlotWidget API -- */
    public void addClickListener( PlotClickListener listener, boolean onlyOnDatapoint )
    {
        windowPlot.addClickListener( listener, onlyOnDatapoint );
    }

    public void addHoverListener( PlotHoverListener listener, boolean onlyOnDatapoint )
    {
        windowPlot.addHoverListener( listener, onlyOnDatapoint );
    }

    public void addSelectedListener( PlotSelectedListener listener )
    {
        overviewPlot.addSelectedListener( listener );
    }

    @Override
    public void addSelectingListener( final PlotSelectingListener listener )
    {
        overviewPlot.addSelectingListener( listener );
    }

    @Override
    public void addUnselectedListener( final PlotUnselectedListener listener )
    {
        overviewPlot.addUnselectedListener( listener );
    }

    @Override
    public PlotSelectionArea getSelection()
    {
        return overviewPlot.getSelection();
    }

    @Override
    public void setSelection( PlotSelectionArea area )
    {
        overviewPlot.setSelection( area );
    }

    @Override
    public void setSelection( PlotSelectionArea area, boolean preventEvent )
    {
        overviewPlot.setSelection( area, preventEvent );
    }

    @Override
    public void clearSelection()
    {
        overviewPlot.clearSelection();
    }

    @Override
    public void clearSelection( boolean preventEvent )
    {
        overviewPlot.clearSelection( preventEvent );
    }

    public int getHeight()
    {
        return windowPlot.getHeight() + overviewPlot.getHeight();
    }

    public int getWindowHeight()
    {
        return windowPlot.getHeight();
    }

    public int getOverviewHeight()
    {
        return overviewPlot.getHeight();
    }

    public PlotWithOverviewModel getModel()
    {
        return model;
    }

    public PlotOptions getWindowPlotOptions()
    {
        return windowPlot.getPlotOptions();
    }

    public PlotOptions getOverviewPlotOptions()
    {
        return overviewPlot.getPlotOptions();
    }

    public int getWidth()
    {
        return windowPlot.getWidth();
    }

    public void redraw()
    {
        double[] selection = model.getSelection();
        if ( selection[0] < selection[1] )
        {
            overviewPlot.setSelection( new PlotSelectionArea().setX( Range.of( selection[0], selection[1] ) ), false );
        }
        windowPlot.redraw();
        overviewPlot.redraw();
    }

    public void setHeight( int height )
    {
        windowPlot.setHeight( height - getOverviewHeight() );
    }

    public void setWindowHeight( int height )
    {
        windowPlot.setHeight( height );
    }

    public void setOverviewHeight( int height )
    {
        overviewPlot.setHeight( height );
    }

    public void setLinearSelection( double x1, double x2 )
    {
        overviewPlot.setSelection( new PlotSelectionArea().setX( Range.of( x1, x2 ) ), false );
    }

    public void setRectangularSelection( double x1, double y1, double x2, double y2 )
    {
        overviewPlot
            .setSelection( new PlotSelectionArea().setX( Range.of( x1, x2 ) ).setY( Range.of( y1, y2 ) ), false );
    }

    public void setWidth( int width )
    {
        overviewPlot.setWidth( width );
        windowPlot.setWidth( width );
    }

    public Widget getWidget()
    {
        return this;
    }

    public SimplePlot getWindowPlot()
    {
        return windowPlot;
    }

    public SimplePlot getOverviewPlot()
    {
        return overviewPlot;
    }

    /* ------------------------- SelectionListener API -- */
    public void onPlotSelected( PlotSelectionArea area )
    {
        Range xRange = area.getX();
        model.setSelection( xRange.getFrom(), xRange.getTo(), new Command() {
            public void execute()
            {
                windowPlot.redraw();
            }
        } );
    }

    /* -------------------------- Helper Methods -- */
    private Widget createUi()
    {
        FlexTable mainPanel = new FlexTable();
        mainPanel.setWidget( 0, 0, windowPlot );
        mainPanel.setWidget( 1, 0, overviewPlot );
        overviewPlot.setHeight( DEFAULT_OVERVIEW_HEIGHT );
        return mainPanel;
    }

    private void setupPlots()
    {
        overviewPlot.addSelectedListener( this );
    }

}
