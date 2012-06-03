package com.googlecode.gflot.examples.client.examples.tracking;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class TrackingPlace
    extends PlaceWithSources<TrackingPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "TrackingExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( TrackingExample.class.getName() );
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
    public TrackingPlace()
    {
        super();
    }

    public TrackingPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public TrackingPlace createPlace()
    {
        return new TrackingPlace();
    }

}