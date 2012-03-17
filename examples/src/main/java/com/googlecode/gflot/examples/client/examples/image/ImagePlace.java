package com.googlecode.gflot.examples.client.examples.image;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class ImagePlace
    extends PlaceWithSources<ImagePlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( ImageExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public ImagePlace()
    {
        super();
    }

    public ImagePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public ImagePlace createPlace()
    {
        return new ImagePlace();
    }

}