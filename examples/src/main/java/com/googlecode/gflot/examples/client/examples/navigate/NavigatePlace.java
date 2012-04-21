package com.googlecode.gflot.examples.client.examples.navigate;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class NavigatePlace
    extends PlaceWithSources<NavigatePlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "NavigateExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( NavigateExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    @Override
    public String[] getRawSourceFilenames()
    {
        return RAW_SOURCE_FILENAMES;
    }

    /**
     * Default constructor. It will show the example
     */
    public NavigatePlace()
    {
        super();
    }

    public NavigatePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public NavigatePlace createPlace()
    {
        return new NavigatePlace();
    }

}
