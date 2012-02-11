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
    public GlobalSeriesOptions()
    {
    }

    public GlobalSeriesOptions( JSONObject jsonObj )
    {
        super( jsonObj );
    }
}
