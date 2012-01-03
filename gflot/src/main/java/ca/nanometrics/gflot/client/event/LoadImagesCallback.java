package ca.nanometrics.gflot.client.event;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Nicolas Morel
 */
public interface LoadImagesCallback
{
    void onImagesLoaded( JavaScriptObject data, JavaScriptObject options );
}
