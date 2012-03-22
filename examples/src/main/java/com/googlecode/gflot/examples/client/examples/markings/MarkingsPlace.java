package com.googlecode.gflot.examples.client.examples.markings;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class MarkingsPlace
    extends PlaceWithSources<MarkingsPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "MarkingsExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( MarkingsExample.class.getName() );
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
    public MarkingsPlace()
    {
        super();
    }

    public MarkingsPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public MarkingsPlace createPlace()
    {
        return new MarkingsPlace();
    }

}