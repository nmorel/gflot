package com.googlecode.gflot.examples.client.examples.bar;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class BarPlace
    extends PlaceWithSources<BarPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "BarExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( BarExample.class.getName() );
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
    public BarPlace()
    {
        super();
    }

    public BarPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public BarPlace createPlace()
    {
        return new BarPlace();
    }

}