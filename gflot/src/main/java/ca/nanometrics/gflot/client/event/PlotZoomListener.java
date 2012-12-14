package ca.nanometrics.gflot.client.event;

import ca.nanometrics.gflot.client.jsni.Plot;

/**
 * @author Nicolas Morel
 */
public interface PlotZoomListener
{
    /**
     * Called when the user zoom the plot
     * 
     * @param axes current axes
     */
    void onPlotZoom( Plot plot );
}
