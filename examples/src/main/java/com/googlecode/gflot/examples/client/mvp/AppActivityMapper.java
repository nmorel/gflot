package com.googlecode.gflot.examples.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.googlecode.gflot.examples.client.examples.bar.BarExample;
import com.googlecode.gflot.examples.client.examples.bar.BarPlace;
import com.googlecode.gflot.examples.client.examples.decimation.DecimationExample;
import com.googlecode.gflot.examples.client.examples.decimation.DecimationPlace;
import com.googlecode.gflot.examples.client.examples.export.ExportExample;
import com.googlecode.gflot.examples.client.examples.export.ExportPlace;
import com.googlecode.gflot.examples.client.examples.hover.HoverExample;
import com.googlecode.gflot.examples.client.examples.hover.HoverPlace;
import com.googlecode.gflot.examples.client.examples.image.ImageExample;
import com.googlecode.gflot.examples.client.examples.image.ImagePlace;
import com.googlecode.gflot.examples.client.examples.interactivelegend.InteractiveLegendExample;
import com.googlecode.gflot.examples.client.examples.interactivelegend.InteractiveLegendPlace;
import com.googlecode.gflot.examples.client.examples.markings.MarkingsExample;
import com.googlecode.gflot.examples.client.examples.markings.MarkingsPlace;
import com.googlecode.gflot.examples.client.examples.multipleaxes.MultipleAxesExample;
import com.googlecode.gflot.examples.client.examples.multipleaxes.MultipleAxesPlace;
import com.googlecode.gflot.examples.client.examples.overview.OverviewExample;
import com.googlecode.gflot.examples.client.examples.overview.OverviewPlace;
import com.googlecode.gflot.examples.client.examples.pie.PieExample;
import com.googlecode.gflot.examples.client.examples.pie.PiePlace;
import com.googlecode.gflot.examples.client.examples.selection.SelectionExample;
import com.googlecode.gflot.examples.client.examples.selection.SelectionPlace;
import com.googlecode.gflot.examples.client.examples.simple.SimpleExample;
import com.googlecode.gflot.examples.client.examples.simple.SimplePlace;
import com.googlecode.gflot.examples.client.examples.sliding.SlidingExample;
import com.googlecode.gflot.examples.client.examples.sliding.SlidingPlace;
import com.googlecode.gflot.examples.client.examples.stack.StackExample;
import com.googlecode.gflot.examples.client.examples.stack.StackPlace;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.PlaceWithSources;
import com.googlecode.gflot.examples.client.source.SourceActivity;

public class AppActivityMapper
    implements ActivityMapper
{
    private Resources resources;

    private Activity barActivity;

    private Activity decimationActivity;

    private Activity hoverActivity;

    private Activity imageActivity;

    private Activity interactiveLegendActivity;

    private Activity markingsActivity;

    private Activity multipleAxesActivity;

    private Activity overviewActivity;

    private Activity pieActivity;

    private Activity selectionActivity;

    private Activity simpleActivity;

    private Activity slidingActivity;

    private Activity stackActivity;

    private Activity exportActivity;

    public AppActivityMapper( Resources resources )
    {
        this.resources = resources;
    }

    @Override
    public Activity getActivity( Place place )
    {
        if ( place instanceof PlaceWithSources && ( (PlaceWithSources<?>) place ).getFilename() != null )
        {
            return new SourceActivity().withPlace( (PlaceWithSources<?>) place );
        }

        if ( place instanceof BarPlace )
        {
            if ( null == barActivity )
            {
                barActivity = new BarExample( resources );
            }
            return barActivity;
        }
        if ( place instanceof DecimationPlace )
        {
            if ( null == decimationActivity )
            {
                decimationActivity = new DecimationExample( resources );
            }
            return decimationActivity;
        }
        if ( place instanceof HoverPlace )
        {
            if ( null == hoverActivity )
            {
                hoverActivity = new HoverExample( resources );
            }
            return hoverActivity;
        }
        if ( place instanceof ImagePlace )
        {
            if ( null == imageActivity )
            {
                imageActivity = new ImageExample( resources );
            }
            return imageActivity;
        }
        if ( place instanceof InteractiveLegendPlace )
        {
            if ( null == interactiveLegendActivity )
            {
                interactiveLegendActivity = new InteractiveLegendExample( resources );
            }
            return interactiveLegendActivity;
        }
        if ( place instanceof MarkingsPlace )
        {
            if ( null == markingsActivity )
            {
                markingsActivity = new MarkingsExample( resources );
            }
            return markingsActivity;
        }
        if ( place instanceof MultipleAxesPlace )
        {
            if ( null == multipleAxesActivity )
            {
                multipleAxesActivity = new MultipleAxesExample( resources );
            }
            return multipleAxesActivity;
        }
        if ( place instanceof OverviewPlace )
        {
            if ( null == overviewActivity )
            {
                overviewActivity = new OverviewExample( resources );
            }
            return overviewActivity;
        }
        if ( place instanceof PiePlace )
        {
            if ( null == pieActivity )
            {
                pieActivity = new PieExample( resources );
            }
            return pieActivity;
        }
        if ( place instanceof SelectionPlace )
        {
            if ( null == selectionActivity )
            {
                selectionActivity = new SelectionExample( resources );
            }
            return selectionActivity;
        }
        if ( place instanceof SimplePlace )
        {
            if ( null == simpleActivity )
            {
                simpleActivity = new SimpleExample( resources );
            }
            return simpleActivity;
        }
        if ( place instanceof SlidingPlace )
        {
            if ( null == slidingActivity )
            {
                slidingActivity = new SlidingExample( resources );
            }
            return slidingActivity;
        }
        if ( place instanceof StackPlace )
        {
            if ( null == stackActivity )
            {
                stackActivity = new StackExample( resources );
            }
            return stackActivity;
        }
        if ( place instanceof ExportPlace )
        {
            if ( null == exportActivity )
            {
                exportActivity = new ExportExample( resources );
            }
            return exportActivity;
        }
        return null;
    }
}
