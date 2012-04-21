package com.googlecode.gflot.examples.client.mvp;

import com.google.gwt.place.shared.Place;
import com.googlecode.gflot.examples.client.examples.bar.BarPlace;
import com.googlecode.gflot.examples.client.examples.decimation.DecimationPlace;
import com.googlecode.gflot.examples.client.examples.export.ExportPlace;
import com.googlecode.gflot.examples.client.examples.hover.HoverPlace;
import com.googlecode.gflot.examples.client.examples.image.ImagePlace;
import com.googlecode.gflot.examples.client.examples.interactivelegend.InteractiveLegendPlace;
import com.googlecode.gflot.examples.client.examples.line.LinePlace;
import com.googlecode.gflot.examples.client.examples.markings.MarkingsPlace;
import com.googlecode.gflot.examples.client.examples.multipleaxes.MultipleAxesPlace;
import com.googlecode.gflot.examples.client.examples.navigate.NavigatePlace;
import com.googlecode.gflot.examples.client.examples.overview.OverviewPlace;
import com.googlecode.gflot.examples.client.examples.pie.PiePlace;
import com.googlecode.gflot.examples.client.examples.selection.SelectionPlace;
import com.googlecode.gflot.examples.client.examples.sliding.SlidingPlace;
import com.googlecode.gflot.examples.client.examples.stack.StackPlace;
import com.googlecode.gflot.examples.client.examples.threshold.ThresholdPlace;

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
        else if ( token.startsWith( NameTokens.LINE ) )
        {
            return new LinePlace();
        }
        else if ( token.startsWith( NameTokens.SLIDING ) )
        {
            return new SlidingPlace();
        }
        else if ( token.startsWith( NameTokens.STACK ) )
        {
            return new StackPlace();
        }
        else if ( token.startsWith( NameTokens.EXPORT ) )
        {
            return new ExportPlace();
        }
        else if ( token.startsWith( NameTokens.THRESHOLD ) )
        {
            return new ThresholdPlace();
        }
        else if ( token.startsWith( NameTokens.NAVIGATE ) )
        {
            return new NavigatePlace();
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
        else if ( place instanceof LinePlace )
        {
            return NameTokens.LINE;
        }
        else if ( place instanceof SlidingPlace )
        {
            return NameTokens.SLIDING;
        }
        else if ( place instanceof StackPlace )
        {
            return NameTokens.STACK;
        }
        else if ( place instanceof ExportPlace )
        {
            return NameTokens.EXPORT;
        }
        else if ( place instanceof ThresholdPlace )
        {
            return NameTokens.THRESHOLD;
        }
        else if ( place instanceof NavigatePlace )
        {
            return NameTokens.NAVIGATE;
        }
        else
        {
            return null;
        }
    }
}
