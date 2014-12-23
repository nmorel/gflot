package com.googlecode.gflot.examples.client.examples.background;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Igor Baldachini
 */
public class BackgroundPlace
    extends PlaceWithSources<BackgroundPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "BackgroundExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( BackgroundExample.class.getName() );
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
    public BackgroundPlace()
    {
        super();
    }

    public BackgroundPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public BackgroundPlace createPlace()
    {
        return new BackgroundPlace();
    }

}