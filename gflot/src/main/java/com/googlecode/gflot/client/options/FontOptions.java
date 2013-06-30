/*
 * Copyright (c) 2012 Nicolas Morel
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


import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.gflot.client.jsni.JsonObject;

/**
 * Options to control font on axis and probably legend later.
 *
 * @author Nicolas Morel
 */
public class FontOptions extends JsonObject
{

    private static final String SIZE_KEY = "size";
    private static final String LINE_HEIGHT_KEY = "lineHeight";
    private static final String STYLE_KEY = "style";
    private static final String WEIGHT_KEY = "weight";
    private static final String FAMILY_KEY = "family";
    private static final String VARIANT_KEY = "variant";
    private static final String COLOR_KEY = "color";

    /** Creates a {@link com.googlecode.gflot.client.options.FontOptions} */
    public static final FontOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    protected FontOptions()
    {
    }

    /** Set the size of the font. Must be expressed in pixels. */
    public final FontOptions setSize( Double size )
    {
        put( SIZE_KEY, size );
        return this;
    }

    /** @return the size of the font */
    public final Double getSize()
    {
        return getDouble( SIZE_KEY );
    }

    /** Clear the size of the font */
    public final FontOptions clearSize()
    {
        clear( SIZE_KEY );
        return this;
    }

    /** Set the line height. Must be expressed in pixels. */
    public final FontOptions setLineHeight( Double lineHeight )
    {
        put( LINE_HEIGHT_KEY, lineHeight );
        return this;
    }

    /** @return the line height */
    public final Double getLineHeight()
    {
        return getDouble( LINE_HEIGHT_KEY );
    }

    /** Clear the line height */
    public final FontOptions clearLineHeight()
    {
        clear( LINE_HEIGHT_KEY );
        return this;
    }

    /** Set the style of the font. */
    public final FontOptions setStyle( String style )
    {
        put( STYLE_KEY, style );
        return this;
    }

    /** @return the style of the font */
    public final String getStyle()
    {
        return getString( STYLE_KEY );
    }

    /** Clear the style of the font */
    public final FontOptions clearStyle()
    {
        clear( STYLE_KEY );
        return this;
    }

    /** Set the weight of the font. */
    public final FontOptions setWeight( String weight )
    {
        put( WEIGHT_KEY, weight );
        return this;
    }

    /** @return the weight of the font */
    public final String getWeight()
    {
        return getString( WEIGHT_KEY );
    }

    /** Clear the weight of the font */
    public final FontOptions clearWeight()
    {
        clear( WEIGHT_KEY );
        return this;
    }

    /** Set the family of the font. */
    public final FontOptions setFamily( String family )
    {
        put( FAMILY_KEY, family );
        return this;
    }

    /** @return the family of the font */
    public final String getFamily()
    {
        return getString( FAMILY_KEY );
    }

    /** Clear the family of the font */
    public final FontOptions clearFamily()
    {
        clear( FAMILY_KEY );
        return this;
    }

    /** Set the variant of the font. */
    public final FontOptions setVariant( String variant )
    {
        put( VARIANT_KEY, variant );
        return this;
    }

    /** @return the variant of the font */
    public final String getVariant()
    {
        return getString( VARIANT_KEY );
    }

    /** Clear the variant of the font */
    public final FontOptions clearVariant()
    {
        clear( VARIANT_KEY );
        return this;
    }

    /** Set the color */
    public final FontOptions setColor( String color )
    {
        put( COLOR_KEY, color );
        return this;
    }

    /** @return the color */
    public final String getColor()
    {
        return getString( COLOR_KEY );
    }

    /** Clear the color of the font */
    public final FontOptions clearColor()
    {
        clear( COLOR_KEY );
        return this;
    }

}
