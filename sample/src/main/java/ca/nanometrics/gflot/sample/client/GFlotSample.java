package ca.nanometrics.gflot.sample.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class GFlotSample
    implements EntryPoint
{
    private TabLayoutPanel tabPanel;

    private List<SimpleLayoutPanel> panels;

    private List<GFlotExample> samples;

    private void addExamples()
    {
        panels = new ArrayList<SimpleLayoutPanel>();
        samples = new ArrayList<GFlotExample>();

        addExample( new SimplePlotExample() );
        addExample( new BarChartExample() );
        addExample( new SelectionExample() );
        addExample( new PlotWithInteractiveLegendExample() );
        addExample( new PlotWithOverviewExample() );
        addExample( new HoverExample() );
        addExample( new SlidingWindowExample() );
        addExample( new DecimationExample() );
        addExample( new MarkingsExample() );
        addExample( new ImageExample() );
        addExample( new MultipleAxesExample() );
        addExample( new PieExample() );
    }

    private void addExample( GFlotExample example )
    {
        SimpleLayoutPanel panel = new SimpleLayoutPanel();
        tabPanel.add( panel, example.getName() );
        panels.add( panel );
        samples.add( example );
    }

    public void onModuleLoad()
    {
        tabPanel = new TabLayoutPanel( 30, Unit.PX );

        addExamples();

        tabPanel.addSelectionHandler( new SelectionHandler<Integer>()
        {
            @Override
            public void onSelection( SelectionEvent<Integer> event )
            {
                initSample( event.getSelectedItem() );
            }
        } );

        RootLayoutPanel.get().add( tabPanel );

        // selected by default
        initSample( 0 );
    }

    private void initSample( final int index )
    {
        // deferred to prevent a bug where the axis are not correctly drawn
        Scheduler.get().scheduleDeferred( new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                SimpleLayoutPanel panel = panels.get( index );
                if ( null == panel.getWidget() )
                {
                    Widget sample = samples.get( index ).createExample();
                    DOM.setStyleAttribute( sample.getElement(), "marginTop", "10px" );
                    panel.setWidget( sample );

                    // setting size to 100% to avoid a bug on IE6 and IE7 where the panel don't take all the space
                    // available
                    panel.setSize( "100%", "100%" );
                }
            }
        } );
    }

}
