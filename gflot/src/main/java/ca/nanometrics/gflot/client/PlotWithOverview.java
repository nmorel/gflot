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
import ca.nanometrics.gflot.client.event.SelectionListener;
import ca.nanometrics.gflot.client.options.GlobalSeriesOptions;
import ca.nanometrics.gflot.client.options.LegendOptions;
import ca.nanometrics.gflot.client.options.LineSeriesOptions;
import ca.nanometrics.gflot.client.options.PlotOptions;
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
    implements PlotWidget, SelectionListener
{
    public static final int DEFAULT_OVERVIEW_HEIGHT = 100; // px

    public static final PlotOptions DEFAULT_OVERVIEW_OPTIONS = new PlotOptions()
        .setLegendOptions( new LegendOptions().setShow( false ) )
        .setGlobalSeriesOptions(
            new GlobalSeriesOptions().setLineSeriesOptions( new LineSeriesOptions().setLineWidth( 1 ).setFill( true ) ).setShadowSize( 0d ) )
        .setSelectionOptions( new SelectionOptions().setMode( SelectionMode.X ) );

    private final SimplePlot m_windowPlot;

    private final SimplePlot m_overviewPlot;

    private final PlotWithOverviewModel m_model;

    private boolean m_ignoreSelectionEvent;

    public PlotWithOverview( PlotWithOverviewModel model )
    {
        this( model, new PlotOptions() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions plotOptions )
    {
        this( model, plotOptions, DEFAULT_OVERVIEW_OPTIONS );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions, PlotOptions overviewPlotOptions )
    {
        m_model = model;
        m_windowPlot = new SimplePlot( m_model.getWindowPlotModel(), windowPlotOptions );
        m_overviewPlot = new SimplePlot( m_model.getOverviewPlotModel(), overviewPlotOptions );
        setupPlots();
        initWidget( createUi() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions, Element windowPlotContainer, Element overviewPlotContainer )
    {
        m_model = model;
        m_windowPlot = new SimplePlot( windowPlotContainer, m_model.getWindowPlotModel(), windowPlotOptions );
        m_overviewPlot = new SimplePlot( overviewPlotContainer, m_model.getOverviewPlotModel(), null );
        setupPlots();
        initWidget( createUi() );
    }

    public PlotWithOverview( PlotWithOverviewModel model, PlotOptions windowPlotOptions, PlotOptions overviewPlotOptions,
                             Element windowPlotContainer, Element overviewPlotContainer )
    {
        m_model = model;
        m_windowPlot = new SimplePlot( windowPlotContainer, m_model.getWindowPlotModel(), windowPlotOptions );
        m_overviewPlot = new SimplePlot( overviewPlotContainer, m_model.getOverviewPlotModel(), overviewPlotOptions );
        setupPlots();
        initWidget( createUi() );
    }

    /* ---------------------- PlotWidget API -- */
    public void addClickListener( PlotClickListener listener, boolean onlyOnDatapoint )
    {
        m_windowPlot.addClickListener( listener, onlyOnDatapoint );
    }

    public void addHoverListener( PlotHoverListener listener, boolean onlyOnDatapoint )
    {
        m_windowPlot.addHoverListener( listener, onlyOnDatapoint );
    }

    public void addSelectionListener( SelectionListener listener )
    {
        m_windowPlot.addSelectionListener( listener );
    }

    public int getHeight()
    {
        return m_windowPlot.getHeight() + m_overviewPlot.getHeight();
    }

    public int getWindowHeight()
    {
        return m_windowPlot.getHeight();
    }

    public int getOverviewHeight()
    {
        return m_overviewPlot.getHeight();
    }

    public PlotModel getModel()
    {
        return m_model;
    }

    public PlotOptions getWindowPlotOptions()
    {
    	return m_windowPlot.getPlotOptions();
    }

    public PlotOptions getOverviewPlotOptions()
    {
    	return m_overviewPlot.getPlotOptions();
    }

    public int getWidth()
    {
        return m_windowPlot.getWidth();
    }

    public void redraw()
    {
        double[] selection = m_model.getSelection();
        if ( selection[0] < selection[1] )
        {
            m_ignoreSelectionEvent = true;
            m_overviewPlot.setLinearSelection( selection[0], selection[1] );
        }
        m_windowPlot.redraw();
        m_overviewPlot.redraw();
    }

    public void setHeight( int height )
    {
        m_windowPlot.setHeight( height - getOverviewHeight() );
    }

    public void setWindowHeight( int height )
    {
        m_windowPlot.setHeight( height );
    }

    public void setOverviewHeight( int height )
    {
        m_overviewPlot.setHeight( height );
    }

    public void setLinearSelection( double x1, double x2 )
    {
        m_overviewPlot.setLinearSelection( x1, x2 );
    }

    public void setRectangularSelection( double x1, double y1, double x2, double y2 )
    {
        m_overviewPlot.setRectangularSelection( x1, y1, x2, y2 );
    }

    public void setWidth( int width )
    {
        m_overviewPlot.setWidth( width );
        m_windowPlot.setWidth( width );
    }

    public Widget getWidget()
    {
        return this;
    }

    /* ------------------------- SelectionListener API -- */
    public void selected( double x1, double y1, double x2, double y2 )
    {
        if ( m_ignoreSelectionEvent )
        {
            m_ignoreSelectionEvent = false;
            return;
        }
        m_model.setSelection( x1, x2, new Command()
        {
            public void execute()
            {
                m_windowPlot.redraw();
            }
        } );

    }

    /* -------------------------- Helper Methods -- */
    private Widget createUi()
    {
        FlexTable mainPanel = new FlexTable();
        mainPanel.setWidget( 0, 0, m_windowPlot );
        mainPanel.setWidget( 1, 0, m_overviewPlot );
        m_overviewPlot.setHeight( DEFAULT_OVERVIEW_HEIGHT );
        return mainPanel;
    }

    private void setupPlots()
    {
        m_overviewPlot.addSelectionListener( this );
    }

    SimplePlot getWindowPlot()
    {
        return m_windowPlot;
    }

    SimplePlot getOverviewPlot()
    {
        return m_overviewPlot;
    }

}
