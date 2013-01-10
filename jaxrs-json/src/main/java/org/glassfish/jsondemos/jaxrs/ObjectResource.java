package org.glassfish.jsondemos.jaxrs;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Writes person's JSON using JsonObject
 *
 * @author Jitendra Kotamraju
 */
@Path("/object")
public class ObjectResource {

    @GET
    @Produces("application/json")
    public JsonObject doGet() {
        return new JsonObjectBuilder()
            .add("firstName", "John")
            .add("lastName", "Smith")
            .add("age", 25)
            .add("address", new JsonObjectBuilder()
                .add("streetAddress", "21 2nd Street")
                .add("city", "New York")
                .add("state", "NY")
                .add("postalCode", "10021"))
            .add("phoneNumber", new JsonArrayBuilder()
                .add(new JsonObjectBuilder()
                    .add("type", "home")
                    .add("number", "212 555-1234"))
                .add(new JsonObjectBuilder()
                    .add("type", "fax")
                    .add("number", "646 555-4567")))
            .build();
    }
}
