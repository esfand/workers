package org.glassfish.jsondemos.jaxrs;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Writes wiki example JSON using JsonObject
 *
 * @author Jitendra Kotamraju
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonBodyWriter implements MessageBodyWriter<JsonStructure> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations,
        MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(JsonStructure jsonStructure, Class<?> aClass,
        Type type, Annotation[] annotations, MediaType mediaType) {

        return -1;
    }

    @Override
    public void writeTo(JsonStructure jsonStructure, Class<?> aClass, Type type,
        Annotation[] annotations, MediaType mediaType,
        MultivaluedMap<String, Object> stringObjectMultivaluedMap,
        OutputStream outputStream) throws IOException, WebApplicationException {

        try(JsonWriter writer = new JsonWriter(outputStream)) {
            writer.write(jsonStructure);
        }
    }
}
