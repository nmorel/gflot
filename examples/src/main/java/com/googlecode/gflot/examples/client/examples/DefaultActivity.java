package com.googlecode.gflot.examples.client.examples;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.resources.Resources;

public abstract class DefaultActivity
    extends AbstractActivity
    implements IsWidget
{
    private Resources resources;

    private Widget widget;

    public DefaultActivity( Resources resources )
    {
        this.resources = resources;
    }

    @Override
    public void start( final AcceptsOneWidget panel, EventBus eventBus )
    {
        if ( null == widget )
        {
            widget = createPlot();
        }
        // deferred to be sure the panel is visible before creating the plot
        Scheduler.get().scheduleDeferred( new ScheduledCommand()
        {
            @Override
            public void execute()
            {
                panel.setWidget( widget );
                Scheduler.get().scheduleDeferred( new ScheduledCommand()
                {
                    @Override
                    public void execute()
                    {
                        // trick under IE6 to force a dom reconstruction
                        PopupPanel pop = new PopupPanel();
                        pop.show();
                        pop.hide();
                    }
                } );
            }
        } );
    }

    @UiFactory
    public Resources getResources()
    {
        return resources;
    }

    protected abstract Widget createPlot();

    @Override
    public Widget asWidget()
    {
        return widget;
    }
}
