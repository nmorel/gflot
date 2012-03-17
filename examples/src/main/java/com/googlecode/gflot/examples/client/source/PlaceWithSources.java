package com.googlecode.gflot.examples.client.source;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.gflot.examples.client.mvp.PlaceWithParameters;

public abstract class PlaceWithSources<P extends PlaceWithSources<?>>
    extends PlaceWithParameters
{
    private static final String PARAM_FILENAME = "filename";
    private static final String PARAM_RAW_SOURCE = "rawSource";

    protected static String extractSourceFilenameFromClassName( String className )
    {
        return className.substring( className.lastIndexOf( "." ) + 1 );
    }

    private String filename;

    private boolean rawSource;

    /**
     * Default constructor to show an example
     */
    public PlaceWithSources()
    {
        this( null, false );
    }

    /**
     * Constructor used to show the source of an example
     *
     * @param filename filename of the source
     * @param rawSource true if the file is a raw source, false otherwise
     */
    public PlaceWithSources( String filename, boolean rawSource )
    {
        this.filename = filename;
        this.rawSource = rawSource;
    }

    public String getFilename()
    {
        return filename;
    }

    private void setFilename( String filename )
    {
        this.filename = filename;
    }

    public boolean isRawSource()
    {
        return rawSource;
    }

    private void setRawSource( boolean rawSource )
    {
        this.rawSource = rawSource;
    }

    public abstract String getSourceFilename();

    public String[] getRawSourceFilenames()
    {
        return null;
    }

    @Override
    public Map<String, String> getParameters()
    {
        if ( null == filename )
        {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put( PARAM_FILENAME, filename );
        map.put( PARAM_RAW_SOURCE, Boolean.toString( rawSource ) );
        return map;
    }

    @Override
    public void setParameters( Map<String, String> parameters )
    {
        if ( null != parameters && !parameters.isEmpty() )
        {
            filename = parameters.get( PARAM_FILENAME );
            rawSource = Boolean.parseBoolean( parameters.get( PARAM_RAW_SOURCE ) );
        }
    }

    public P createPlace( String filename, boolean rawSource )
    {
        P place = createPlace();
        place.setFilename( filename );
        place.setRawSource( rawSource );
        return place;
    }

    public abstract P createPlace();

}
