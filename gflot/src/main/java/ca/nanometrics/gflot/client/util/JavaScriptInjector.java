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
package ca.nanometrics.gflot.client.util;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadElement;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.dom.client.Element;

/**
 * Wrapper around functionality used to inject external Javascript code into your application.
 *
 * @author Simon Pamies
 */
public class JavaScriptInjector
{

    private static HeadElement head;

    public static void inject( String javascript )
    {
        HeadElement head = getHead();
        ScriptElement element = createScriptElement();
        element.setText( javascript );
        head.appendChild( element );
    }

    private static ScriptElement createScriptElement()
    {
        ScriptElement script = Document.get().createScriptElement();
        script.setAttribute( "language", "javascript" );
        return script;
    }

    private static HeadElement getHead()
    {
        if ( head == null )
        {
            Element element = Document.get().getElementsByTagName( "head" ).getItem( 0 );
            assert element != null : "HTML Head element required";
            HeadElement head = HeadElement.as( element );
            JavaScriptInjector.head = head;
        }
        return JavaScriptInjector.head;
    }

}