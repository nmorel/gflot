package com.googlecode.gflot.examples.client.examples.pie;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class PiePlace
    extends PlaceWithSources<PiePlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "PieExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( PieExample.class.getName() );
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
    public PiePlace()
    {
        super();
    }

    public PiePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public PiePlace createPlace()
    {
        return new PiePlace();
    }

}
