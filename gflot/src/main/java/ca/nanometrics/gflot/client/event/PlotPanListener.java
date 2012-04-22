package ca.nanometrics.gflot.client.event;

import ca.nanometrics.gflot.client.Axes;

/**
 * @author Nicolas Morel
 */
public interface PlotPanListener
{
    /**
     * Called when the user pan the plot
     *
     * @param axes current axes
     */
    void onPlotPan( Axes axes );
}
