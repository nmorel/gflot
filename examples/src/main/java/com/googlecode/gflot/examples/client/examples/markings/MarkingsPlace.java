package com.googlecode.gflot.examples.client.examples.markings;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class MarkingsPlace
    extends PlaceWithSources<MarkingsPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( MarkingsExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
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