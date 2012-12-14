package ca.nanometrics.gflot.client.event;

import ca.nanometrics.gflot.client.Series;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.JsArray;

/**
 * @author Nicolas Morel
 */
public interface LoadImagesCallback
{
    void onImagesLoaded( JsArray<Series> data, PlotOptions options );
}
