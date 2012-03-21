package com.googlecode.gflot.examples.client.examples.stack;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class StackPlace
    extends PlaceWithSources<StackPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "StackExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( StackExample.class.getName() );
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
    public StackPlace()
    {
        super();
    }

    public StackPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public StackPlace createPlace()
    {
        return new StackPlace();
    }

}
