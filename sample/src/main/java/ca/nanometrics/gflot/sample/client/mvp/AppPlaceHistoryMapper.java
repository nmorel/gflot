package ca.nanometrics.gflot.sample.client.mvp;

import ca.nanometrics.gflot.sample.client.samples.bar.BarPlace;
import ca.nanometrics.gflot.sample.client.samples.decimation.DecimationPlace;
import ca.nanometrics.gflot.sample.client.samples.hover.HoverPlace;
import ca.nanometrics.gflot.sample.client.samples.image.ImagePlace;
import ca.nanometrics.gflot.sample.client.samples.interactivelegend.InteractiveLegendPlace;
import ca.nanometrics.gflot.sample.client.samples.markings.MarkingsPlace;
import ca.nanometrics.gflot.sample.client.samples.multipleaxes.MultipleAxesPlace;
import ca.nanometrics.gflot.sample.client.samples.overview.OverviewPlace;
import ca.nanometrics.gflot.sample.client.samples.pie.PiePlace;
import ca.nanometrics.gflot.sample.client.samples.selection.SelectionPlace;
import ca.nanometrics.gflot.sample.client.samples.simple.SimplePlace;
import ca.nanometrics.gflot.sample.client.samples.sliding.SlidingPlace;
import ca.nanometrics.gflot.sample.client.samples.stack.StackPlace;

import com.google.gwt.place.shared.Place;

public class AppPlaceHistoryMapper
    extends AbstractPlaceHistoryMapper
{

    protected Place getPlaceFromToken( String token )
    {
        // Add any new place here
        if ( token.startsWith( NameTokens.BAR ) )
        {
            return new BarPlace();
        }
        else if ( token.startsWith( NameTokens.DECIMATION ) )
        {
            return new DecimationPlace();
        }
        else if ( token.startsWith( NameTokens.HOVER ) )
        {
            return new HoverPlace();
        }
        else if ( token.startsWith( NameTokens.IMAGE ) )
        {
            return new ImagePlace();
        }
        else if ( token.startsWith( NameTokens.INTERACTIVE_LEGEND ) )
        {
            return new InteractiveLegendPlace();
        }
        else if ( token.startsWith( NameTokens.MARKINGS ) )
        {
            return new MarkingsPlace();
        }
        else if ( token.startsWith( NameTokens.MULTIPLES_AXES ) )
        {
            return new MultipleAxesPlace();
        }
        else if ( token.startsWith( NameTokens.OVERVIEW ) )
        {
            return new OverviewPlace();
        }
        else if ( token.startsWith( NameTokens.PIE ) )
        {
            return new PiePlace();
        }
        else if ( token.startsWith( NameTokens.SELECTION ) )
        {
            return new SelectionPlace();
        }
        else if ( token.startsWith( NameTokens.SIMPLE ) )
        {
            return new SimplePlace();
        }
        else if ( token.startsWith( NameTokens.SLIDING ) )
        {
            return new SlidingPlace();
        }
        else if ( token.startsWith( NameTokens.STACK ) )
        {
            return new StackPlace();
        }
        else
        {
            return null;
        }
    }

    protected String getTokenFromPlace( Place place )
    {
        // Add any new place here
        if ( place instanceof BarPlace )
        {
            return NameTokens.BAR;
        }
        else if ( place instanceof DecimationPlace )
        {
            return NameTokens.DECIMATION;
        }
        else if ( place instanceof HoverPlace )
        {
            return NameTokens.HOVER;
        }
        else if ( place instanceof ImagePlace )
        {
            return NameTokens.IMAGE;
        }
        else if ( place instanceof InteractiveLegendPlace )
        {
            return NameTokens.INTERACTIVE_LEGEND;
        }
        else if ( place instanceof MarkingsPlace )
        {
            return NameTokens.MARKINGS;
        }
        else if ( place instanceof MultipleAxesPlace )
        {
            return NameTokens.MULTIPLES_AXES;
        }
        else if ( place instanceof OverviewPlace )
        {
            return NameTokens.OVERVIEW;
        }
        else if ( place instanceof PiePlace )
        {
            return NameTokens.PIE;
        }
        else if ( place instanceof SelectionPlace )
        {
            return NameTokens.SELECTION;
        }
        else if ( place instanceof SimplePlace )
        {
            return NameTokens.SIMPLE;
        }
        else if ( place instanceof SlidingPlace )
        {
            return NameTokens.SLIDING;
        }
        else if ( place instanceof StackPlace )
        {
            return NameTokens.STACK;
        }
        else
        {
            return null;
        }
    }
}
