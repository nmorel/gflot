package com.googlecode.gflot.examples.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceChangeEvent.Handler;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gflot.examples.client.examples.bar.BarPlace;
import com.googlecode.gflot.examples.client.examples.decimation.DecimationPlace;
import com.googlecode.gflot.examples.client.examples.export.ExportPlace;
import com.googlecode.gflot.examples.client.examples.hover.HoverPlace;
import com.googlecode.gflot.examples.client.examples.image.ImagePlace;
import com.googlecode.gflot.examples.client.examples.interactivelegend.InteractiveLegendPlace;
import com.googlecode.gflot.examples.client.examples.line.LinePlace;
import com.googlecode.gflot.examples.client.examples.markings.MarkingsPlace;
import com.googlecode.gflot.examples.client.examples.multipleaxes.MultipleAxesPlace;
import com.googlecode.gflot.examples.client.examples.overview.OverviewPlace;
import com.googlecode.gflot.examples.client.examples.pie.PiePlace;
import com.googlecode.gflot.examples.client.examples.selection.SelectionPlace;
import com.googlecode.gflot.examples.client.examples.sliding.SlidingPlace;
import com.googlecode.gflot.examples.client.examples.stack.StackPlace;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.PlaceWithSources;

public class MainView
    extends ResizeComposite
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

    private final PlaceController placeController;

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
    Hyperlink exportLink;

    @UiField
    Anchor exampleLink;

    @UiField
    Anchor sourceLink;

    @UiField
    ListBox sourceList;

    @UiField
    ScrollPanel container;

    public MainView( EventBus eventBus, PlaceController placeController, Resources res )
    {
        this.placeController = placeController;
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
                return place instanceof LinePlace;
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
        links.add( new Link( exportLink, new Predicate()
        {
            @Override
            public boolean apply( Place place )
            {
                return place instanceof ExportPlace;
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

        // select the link corresponding to the place
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

        // clear the source list because we are going to update it with the raw source file corresponding to the new
        // place
        sourceList.clear();

        if ( newPlace instanceof PlaceWithSources )
        {
            exampleLink.setVisible( true );
            sourceLink.setVisible( true );

            PlaceWithSources<?> place = (PlaceWithSources<?>) newPlace;

            sourceList.addItem( "Example", place.getSourceFilename() );

            String[] rawFilenames = place.getRawSourceFilenames();

            if ( null != rawFilenames && rawFilenames.length > 0 )
            {
                // add the raw source files to the list and show the list
                String text = sourceLink.getText();
                if ( !text.endsWith( ":" ) )
                {
                    sourceLink.setText( text + ":" );
                }
                sourceList.setVisible( true );
                int indexRawSource = 0;
                // starting at 1 because the first item is the example
                int i = 1;
                for ( String filename : rawFilenames )
                {
                    sourceList.addItem( filename, filename );
                    if ( place.isRawSource() && filename.equals( place.getFilename() ) )
                    {
                        indexRawSource = i;
                    }
                    i++;
                }
                sourceList.setSelectedIndex( indexRawSource );
            }
            else
            {
                // no raw source file, we hide the list
                String text = sourceLink.getText();
                if ( text.endsWith( ":" ) )
                {
                    sourceLink.setText( text.substring( 0, text.length() - 1 ) );
                }
                sourceList.setVisible( false );
            }

            if ( null == place.getFilename() )
            {
                sourceLink.removeStyleName( res.style().sourceLinkSelected() );
                exampleLink.addStyleName( res.style().sourceLinkSelected() );
                container.getElement().getStyle().clearBackgroundColor();
                container.getElement().getStyle().clearProperty( "border" );
            }
            else
            {
                exampleLink.removeStyleName( res.style().sourceLinkSelected() );
                sourceLink.addStyleName( res.style().sourceLinkSelected() );
                container.getElement().getStyle().setBackgroundColor( "#eee" );
                container.getElement().getStyle().setProperty( "border", "1px solid #c3c3c3" );
            }
        }
        else
        {
            // should not happen
            exampleLink.setVisible( false );
            sourceLink.setVisible( false );
            sourceList.setVisible( false );
        }
    }

    @UiHandler( "exampleLink" )
    void onClickExampleLink( ClickEvent e )
    {
        Place currentPlace = placeController.getWhere();
        if ( currentPlace instanceof PlaceWithSources )
        {
            PlaceWithSources<?> place = (PlaceWithSources<?>) currentPlace;
            if ( null != place.getFilename() )
            {
                // we were on the source page, we create a new place from the previous one and go to this new place
                placeController.goTo( place.createPlace() );
            }
        }
    }

    @UiHandler( "sourceLink" )
    void onClickSourceLink( ClickEvent e )
    {
        Place currentPlace = placeController.getWhere();
        if ( currentPlace instanceof PlaceWithSources )
        {
            PlaceWithSources<?> place = (PlaceWithSources<?>) currentPlace;
            // we were on the example page, we create a new place from the previous one and go to this new place
            placeController.goTo( place.createPlace( place.getSourceFilename(), sourceList.isVisible() && sourceList.getSelectedIndex() > 0 ) );
        }
    }

    @UiHandler( "sourceList" )
    void onChangeSourceList( ChangeEvent e )
    {
        Place currentPlace = placeController.getWhere();
        if ( currentPlace instanceof PlaceWithSources )
        {
            PlaceWithSources<?> place = (PlaceWithSources<?>) currentPlace;
            int selectedIndex = sourceList.getSelectedIndex();
            // we were on the example page, we create a new place from the previous one and go to this new place
            placeController.goTo( place.createPlace( sourceList.getValue( selectedIndex ), selectedIndex > 0 ) );
        }
    }
}
