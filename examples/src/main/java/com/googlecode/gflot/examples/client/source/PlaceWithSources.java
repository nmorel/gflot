package com.googlecode.gflot.examples.client.source;

import java.util.HashMap;
import java.util.Map;

import com.googlecode.gflot.examples.client.mvp.PlaceWithParameters;

/**
 * Abstract place to handle the source and raw source file
 *
 * @author Nicolas Morel
 * @param <P> type of the place
 */
public abstract class PlaceWithSources<P extends PlaceWithSources<?>>
    extends PlaceWithParameters
{
    /**
     * Key of the filename's parameter
     */
    private static final String PARAM_FILENAME = "filename";

    /**
     * Key of the rawSource's parameter
     */
    private static final String PARAM_RAW_SOURCE = "rawSource";

    /**
     * Extract the source's filename from class name
     *
     * @param className Name of the class
     * @return the source's filename
     */
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

    void setFilename( String filename )
    {
        this.filename = filename;
    }

    public boolean isRawSource()
    {
        return rawSource;
    }

    void setRawSource( boolean rawSource )
    {
        this.rawSource = rawSource;
    }

    /**
     * @return the source filename
     */
    public abstract String getSourceFilename();

    /**
     * @return the raw source filenames. can be null
     */
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

    /**
     * Create a new place from this one with the filename and rawSource specified. This method is used to switch between
     * place showing example and place showing source. Implementations can copy their specific attributes.
     *
     * @return the created place
     */
    public P createPlace( String filename, boolean rawSource )
    {
        P place = createPlace();
        place.setFilename( filename );
        place.setRawSource( rawSource );
        return place;
    }

    /**
     * Create a new place from this one without the filename and rawSource attributes. This method is used to switch
     * between place showing example and place showing source. Implementations can copy their specific attrbutes.
     *
     * @return the created place
     */
    public abstract P createPlace();

}
