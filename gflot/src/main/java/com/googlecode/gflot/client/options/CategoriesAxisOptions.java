/*
 * Copyright (c) 2013 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.googlecode.gflot.client.options;

import java.util.Collection;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * @author Nicolas Morel
 */
public class CategoriesAxisOptions extends AbstractAxisOptions<CategoriesAxisOptions>
{

    public static class Categories extends JsonObject
    {

        /**
         * Creates a {@link Categories}
         */
        public static final Categories create()
        {
            return JavaScriptObject.createObject().cast();
        }

        protected Categories()
        {
        }

        public final Categories add( String category, double distance )
        {
            put( category, distance );
            return this;
        }
    }

    private static final String CATEGORIES_KEY = "categories";

    /**
     * Creates a {@link CategoriesAxisOptions}
     */
    public static final CategoriesAxisOptions create()
    {
        CategoriesAxisOptions axis = JavaScriptObject.createObject().cast();
        axis.put( MODE_KEY, CATEGORIES_MODE_KEY );
        return axis;
    }

    protected CategoriesAxisOptions()
    {
    }

    /**
     * @return the categories as an array
     */
    public final JsArrayString getCategoriesAsArray()
    {
        return getStringArray( CATEGORIES_KEY );
    }

    /**
     * @return the categories as an object mapping labels to values
     */
    public final Categories getCategoriesAsMap()
    {
        return getJsObject( CATEGORIES_KEY );
    }

    /**
     * Set the categories as an array.
     */
    public final CategoriesAxisOptions setCategories( String... categories )
    {
        assert null != categories : "categories can't be null";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String category : categories )
        {
            array.push( category );
        }
        return setCategories( array );
    }

    /**
     * Set the categories as an array.
     */
    public final CategoriesAxisOptions setCategories( Collection<String> categories )
    {
        assert null != categories : "categories can't be null";

        JsArrayString array = JavaScriptObject.createArray().cast();
        for ( String category : categories )
        {
            array.push( category );
        }
        return setCategories( array );
    }

    /**
     * Set the categories as an array.
     */
    public final CategoriesAxisOptions setCategories( JsArrayString categories )
    {
        assert null != categories : "categories can't be null";

        put( CATEGORIES_KEY, categories );
        return this;
    }

    /**
     * Set the categories as an object mapping labels to values.
     */
    public final CategoriesAxisOptions setCategories( Categories categories )
    {
        assert null != categories : "categories can't be null";

        put( CATEGORIES_KEY, categories );
        return this;
    }

    /**
     * Clear the categories
     */
    public final CategoriesAxisOptions clearCategories()
    {
        clear( CATEGORIES_KEY );
        return this;
    }

}
