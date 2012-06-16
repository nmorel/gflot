package ca.nanometrics.gflot.client.options;

import com.google.gwt.json.client.JSONObject;

/**
 * Options for all series. If you need to define specific option for your series, use the options on Series
 *
 * @author Nicolas Morel
 */
public class GlobalSeriesOptions
    extends CommonSeriesOptions<GlobalSeriesOptions>
{
    private static final String MULTIPLE_BARS_KEY = "multipleBars";

    public GlobalSeriesOptions()
    {
    }

    public GlobalSeriesOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }

    /**
     * Enable the multiple bars patch to flot.
     */
    public GlobalSeriesOptions setMultipleBars( boolean multiplebars )
    {
        put( MULTIPLE_BARS_KEY, multiplebars );
        return this;
    }

    /**
     * @return true if the multiple bars patch to flot is enabled
     */
    public Boolean getMultipleBars()
    {
        return getBoolean( MULTIPLE_BARS_KEY );
    }
}
