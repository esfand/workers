package org.glassfish.jsondemos.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * A JAX-RS Demo Application using JSON API
 *
 * @author Jitendra Kotamraju
 */
@ApplicationPath("/")
public class DemoApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(ParserResource.class);
        set.add(GeneratorResource.class);
        set.add(ObjectResource.class);

        set.add(JsonBodyWriter.class);
        return set;
    }
}
