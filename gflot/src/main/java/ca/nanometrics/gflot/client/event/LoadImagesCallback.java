package ca.nanometrics.gflot.client.event;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Nicolas Morel
 */
public interface LoadImagesCallback
{
    // FIXME overlay : put the real object once they are converted to JSO
    void onImagesLoaded( JavaScriptObject data, JavaScriptObject options );
}
