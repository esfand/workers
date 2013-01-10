package org.glassfish.jsondemos.jaxrs;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.StreamingOutput;
import java.io.OutputStream;

/**
 * Writes wiki's JSON example in a streaming fashion
 *
 * @author Jitendra Kotamraju
 */
@Path("/generator")
public class GeneratorResource {

    @GET
    @Produces("application/json")
    public StreamingOutput doGet() {
        return new StreamingOutput() {
            public void write(OutputStream os) {
                writeWikiExample(os);
            }
        };
    }

    // Writes wiki example JSON in a streaming fashion
    private void writeWikiExample(OutputStream os) {
        try(JsonGenerator gene = Json.createGenerator(os)) {
            gene.writeStartObject()
                .write("firstName", "John")
                .write("lastName", "Smith")
                .write("age", 25)
                .writeStartObject("address")
                    .write("streetAddress", "21 2nd Street")
                    .write("city", "New York")
                    .write("state", "NY")
                    .write("postalCode", "10021")
                .writeEnd()
                .writeStartArray("phoneNumber")
                    .writeStartObject()
                        .write("type", "home")
                        .write("number", "212 555-1234")
                    .writeEnd()
                    .writeStartObject()
                        .write("type", "fax")
                        .write("number", "646 555-4567")
                    .writeEnd()
                .writeEnd()
            .writeEnd();
        }
    }

}
