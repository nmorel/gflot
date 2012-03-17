package com.googlecode.gflot.examples.client.examples.stack;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class StackPlace
    extends PlaceWithSources<StackPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( StackExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
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
