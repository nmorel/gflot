package com.googlecode.gflot.examples.client;

import java.util.ArrayList;
import java.util.List;


import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeEvent.Handler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gflot.examples.client.examples.bar.BarPlace;
import com.googlecode.gflot.examples.client.examples.decimation.DecimationPlace;
import com.googlecode.gflot.examples.client.examples.hover.HoverPlace;
import com.googlecode.gflot.examples.client.examples.image.ImagePlace;
import com.googlecode.gflot.examples.client.examples.interactivelegend.InteractiveLegendPlace;
import com.googlecode.gflot.examples.client.examples.markings.MarkingsPlace;
import com.googlecode.gflot.examples.client.examples.multipleaxes.MultipleAxesPlace;
import com.googlecode.gflot.examples.client.examples.overview.OverviewPlace;
import com.googlecode.gflot.examples.client.examples.pie.PiePlace;
import com.googlecode.gflot.examples.client.examples.selection.SelectionPlace;
import com.googlecode.gflot.examples.client.examples.simple.SimplePlace;
import com.googlecode.gflot.examples.client.examples.sliding.SlidingPlace;
import com.googlecode.gflot.examples.client.examples.stack.StackPlace;
import com.googlecode.gflot.examples.client.resources.Resources;

public class MainView
    extends Composite
    implements Handler
{

    private static MainViewUiBinder uiBinder = GWT.create( MainViewUiBinder.class );

    interface MainViewUiBinder
        extends UiBinder<Widget, MainView>
    {
    }

    private static interface Predicate
    {
        boolean apply( Place place );
    }

    private static class Link
    {
        private Hyperlink link;

        private Predicate predicate;

        public Link( Hyperlink link, Predicate predicate )
        {
            this.link = link;
            this.predicate = predicate;
        }

        public boolean isPlaceMatchLink( Place place )
        {
            return predicate.apply( place );
        }

        public Hyperlink getLink()
        {
            return link;
        }
    }

    private List<Link> links;

    @UiField( provided = true )
    Resources res;

    @UiField
    Hyperlink barLink;

    @UiField
    Hyperlink decimationLink;

    @UiField
    Hyperlink hoverLink;

    @UiField
    Hyperlink imageLink;

    @UiField
    Hyperlink interactiveLegendLink;

    @UiField
    Hyperlink markingsLink;

    @UiField
    Hyperlink multipleAxesLink;

    @UiField
    Hyperlink overviewLink;

    @UiField
    Hyperlink pieLink;

    @UiField
    Hyperlink selectionLink;

    @UiField
    Hyperlink simpleLink;

    @UiField
    Hyperlink slidingLink;

    @UiField
    Hyperlink stackLink;

    @UiField
    SimpleLayoutPanel container;

    public MainView( EventBus eventBus, Resources res )
    {
        this.res = res;

        initWidget( uiBinder.createAndBindUi( this ) );

        links = new ArrayList<Link>();
        links.add( new Link( barLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof BarPlace;
            }
        } ) );
        links.add( new Link( decimationLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof DecimationPlace;
            }
        } ) );
        links.add( new Link( hoverLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof HoverPlace;
            }
        } ) );
        links.add( new Link( imageLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof ImagePlace;
            }
        } ) );
        links.add( new Link( interactiveLegendLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof InteractiveLegendPlace;
            }
        } ) );
        links.add( new Link( markingsLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof MarkingsPlace;
            }
        } ) );
        links.add( new Link( multipleAxesLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof MultipleAxesPlace;
            }
        } ) );
        links.add( new Link( overviewLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof OverviewPlace;
            }
        } ) );
        links.add( new Link( pieLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof PiePlace;
            }
        } ) );
        links.add( new Link( selectionLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof SelectionPlace;
            }
        } ) );
        links.add( new Link( simpleLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof SimplePlace;
            }
        } ) );
        links.add( new Link( slidingLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof SlidingPlace;
            }
        } ) );
        links.add( new Link( stackLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof StackPlace;
            }
        } ) );

        eventBus.addHandler( PlaceChangeEvent.TYPE, this );
    }

    public AcceptsOneWidget getContainer()
    {
        return container;
    }

    @Override
    public void onPlaceChange( PlaceChangeEvent event )
    {
        Place newPlace = event.getNewPlace();

        for ( Link link : links )
        {
            if ( link.isPlaceMatchLink( newPlace ) )
            {
                link.getLink().addStyleName( res.style().menuLinkSelected() );
            }
            else
            {
                link.getLink().removeStyleName( res.style().menuLinkSelected() );
            }
        }
    }

}
