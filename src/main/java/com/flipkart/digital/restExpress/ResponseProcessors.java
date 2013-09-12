package com.flipkart.digital.restExpress;

import com.strategicgains.restexpress.response.DefaultResponseWrapper;
import com.strategicgains.restexpress.response.RawResponseWrapper;
import com.strategicgains.restexpress.response.ResponseProcessor;
import com.strategicgains.restexpress.response.ResponseWrapper;
import com.strategicgains.restexpress.serialization.SerializationProcessor;
import com.strategicgains.restexpress.serialization.json.DefaultJsonProcessor;
import com.strategicgains.restexpress.serialization.xml.DefaultXmlProcessor;

/**
 * A factory to create ResponseProcessors for serialization and wrapping of responses.
 * 
 * @author toddf
 * @since May 15, 2012
 */
public class ResponseProcessors
{
    // SECTION: CONSTANTS

    private static final SerializationProcessor JSON_SERIALIZER = new DefaultJsonProcessor();//new JsonSerializationProcessor();
    private static final SerializationProcessor XML_SERIALIZER = new DefaultXmlProcessor();//new XmlSerializationProcessor();
    private static final ResponseWrapper RAW_WRAPPER = new RawResponseWrapper();
    private static final ResponseWrapper WRAPPING_WRAPPER = new DefaultResponseWrapper();

    // SECTION: FACTORY

    public static ResponseProcessor json()
    {
        return new ResponseProcessor(JSON_SERIALIZER, RAW_WRAPPER);
    }

    public static ResponseProcessor wrappedJson()
    {
        return new ResponseProcessor(JSON_SERIALIZER, WRAPPING_WRAPPER);
    }

    public static ResponseProcessor xml()
    {
        return new ResponseProcessor(XML_SERIALIZER, RAW_WRAPPER);
    }

    public static ResponseProcessor wrappedXml()
    {
        return new ResponseProcessor(XML_SERIALIZER, WRAPPING_WRAPPER);
    }
}
