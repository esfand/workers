package com.webwarp.junk;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URL;
//import java.util.Iterator;

/**
 * Filters JSON from twitter search REST API
 *
 * @author Jitendra Kotamraju
 */
//@Path("/parser")
public class ParserResource {

    //@GET
    //@Produces("text/plain")
    //public StreamingOutput doGet() {
        //return new StreamingOutput() {
            //@Override
            //public void write(OutputStream os) throws IOException {
                //writeTwitterFeed(os);
            //}
        //};
    //}

    /**
     * Parses JSON from twitter search REST API
     *
     * ... { ... "from_user" : "xxx", ..., "text: "yyy", ... } ...
     *
     * then writes to HTTP output stream as follows:
     *
     * xxx: yyy
     * --------
     */
    private void writeTwitterFeed(OutputStream os) throws IOException {
        URL url = new URL("http://search.twitter.com/search.json?q=%23java");
        try(InputStream is = url.openStream();
            JsonParser parser = Json.createParser(is);
            PrintWriter ps = new PrintWriter(new OutputStreamWriter(os, "UTF-8"))) {

            while(parser.hasNext()) {
                Event e = parser.next();
                if (e == Event.KEY_NAME) {
                    if (parser.getString().equals("from_user")) {
                        parser.next();
                        ps.print(parser.getString());
                        ps.print(": ");
                    } else if (parser.getString().equals("text")) {
                        parser.next();
                        ps.println(parser.getString());
                        ps.println("---------");
                    }
                }
            }
        }
    }
}
