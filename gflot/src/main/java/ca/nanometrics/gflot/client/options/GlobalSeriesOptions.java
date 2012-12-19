package ca.nanometrics.gflot.client.options;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options for all series. If you need to define specific option for your series, use the options on Series
 * 
 * @author Nicolas Morel
 */
public class GlobalSeriesOptions
    extends CommonSeriesOptions<GlobalSeriesOptions>
{
    /**
     * Creates a {@link GlobalSeriesOptions}
     */
    public static final GlobalSeriesOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String MULTIPLE_BARS_KEY = "multipleBars";

    protected GlobalSeriesOptions()
    {
    }

    /**
     * Enable the multiple bars patch to flot.
     */
    public final GlobalSeriesOptions setMultipleBars( boolean multiplebars )
    {
        put( MULTIPLE_BARS_KEY, multiplebars );
        return this;
    }

    /**
     * @return true if the multiple bars patch to flot is enabled
     */
    public final Boolean getMultipleBars()
    {
        return getBoolean( MULTIPLE_BARS_KEY );
    }

    /**
     * Clear if the multiple bars patch to flot is enabled
     */
    public final GlobalSeriesOptions clearMultipleBars()
    {
        clear( MULTIPLE_BARS_KEY );
        return this;
    }

}
