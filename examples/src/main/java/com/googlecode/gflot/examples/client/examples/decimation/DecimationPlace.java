package com.googlecode.gflot.examples.client.examples.decimation;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class DecimationPlace
    extends PlaceWithSources<DecimationPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "DecimationExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( DecimationExample.class.getName() );
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
    public DecimationPlace()
    {
        super();
    }

    public DecimationPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public DecimationPlace createPlace()
    {
        return new DecimationPlace();
    }

}