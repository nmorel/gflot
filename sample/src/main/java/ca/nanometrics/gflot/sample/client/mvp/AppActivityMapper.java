package ca.nanometrics.gflot.sample.client.mvp;

import ca.nanometrics.gflot.sample.client.resources.Resources;
import ca.nanometrics.gflot.sample.client.samples.bar.BarExample;
import ca.nanometrics.gflot.sample.client.samples.bar.BarPlace;
import ca.nanometrics.gflot.sample.client.samples.decimation.DecimationExample;
import ca.nanometrics.gflot.sample.client.samples.decimation.DecimationPlace;
import ca.nanometrics.gflot.sample.client.samples.hover.HoverExample;
import ca.nanometrics.gflot.sample.client.samples.hover.HoverPlace;
import ca.nanometrics.gflot.sample.client.samples.image.ImageExample;
import ca.nanometrics.gflot.sample.client.samples.image.ImagePlace;
import ca.nanometrics.gflot.sample.client.samples.interactivelegend.InteractiveLegendExample;
import ca.nanometrics.gflot.sample.client.samples.interactivelegend.InteractiveLegendPlace;
import ca.nanometrics.gflot.sample.client.samples.markings.MarkingsExample;
import ca.nanometrics.gflot.sample.client.samples.markings.MarkingsPlace;
import ca.nanometrics.gflot.sample.client.samples.multipleaxes.MultipleAxesExample;
import ca.nanometrics.gflot.sample.client.samples.multipleaxes.MultipleAxesPlace;
import ca.nanometrics.gflot.sample.client.samples.overview.OverviewExample;
import ca.nanometrics.gflot.sample.client.samples.overview.OverviewPlace;
import ca.nanometrics.gflot.sample.client.samples.pie.PieExample;
import ca.nanometrics.gflot.sample.client.samples.pie.PiePlace;
import ca.nanometrics.gflot.sample.client.samples.selection.SelectionExample;
import ca.nanometrics.gflot.sample.client.samples.selection.SelectionPlace;
import ca.nanometrics.gflot.sample.client.samples.simple.SimpleExample;
import ca.nanometrics.gflot.sample.client.samples.simple.SimplePlace;
import ca.nanometrics.gflot.sample.client.samples.sliding.SlidingExample;
import ca.nanometrics.gflot.sample.client.samples.sliding.SlidingPlace;
import ca.nanometrics.gflot.sample.client.samples.stack.StackExample;
import ca.nanometrics.gflot.sample.client.samples.stack.StackPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

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

    public AppActivityMapper( Resources resources )
    {
        this.resources = resources;
    }

    @Override
    public Activity getActivity( Place place )
    {
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
        return null;
    }
}
