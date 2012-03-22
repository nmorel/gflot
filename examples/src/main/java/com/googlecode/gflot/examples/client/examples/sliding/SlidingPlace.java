package com.googlecode.gflot.examples.client.examples.sliding;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class SlidingPlace
    extends PlaceWithSources<SlidingPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "SlidingExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( SlidingExample.class.getName() );
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
    public SlidingPlace()
    {
        super();
    }

    public SlidingPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public SlidingPlace createPlace()
    {
        return new SlidingPlace();
    }

}
