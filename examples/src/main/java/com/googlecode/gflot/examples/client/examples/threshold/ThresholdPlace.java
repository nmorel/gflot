package com.googlecode.gflot.examples.client.examples.threshold;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class ThresholdPlace
    extends PlaceWithSources<ThresholdPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "ThresholdExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( ThresholdExample.class.getName() );
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
    public ThresholdPlace()
    {
        super();
    }

    public ThresholdPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public ThresholdPlace createPlace()
    {
        return new ThresholdPlace();
    }

}
