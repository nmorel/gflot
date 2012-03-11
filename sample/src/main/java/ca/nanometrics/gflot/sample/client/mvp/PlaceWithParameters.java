package ca.nanometrics.gflot.sample.client.mvp;

import java.util.Map;

import com.google.gwt.place.shared.Place;

/**
 * Places with parameters should extend this class
 *
 * @author Nicolas Morel
 */
public abstract class PlaceWithParameters
    extends Place
{
    /**
     * Return the place's parameters in their string representation
     *
     * @return the place's parameters in their string representation
     */
    public abstract Map<String, String> getParameters();

    /**
     * Give to the place their parameters in their string representation
     *
     * @param parameters parameters in their string representation
     */
    public abstract void setParameters( Map<String, String> parameters );
}
