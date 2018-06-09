package br.com.brinkaedekora.rest;

import javax.ws.rs.core.Application;
import java.util.Map;
import java.util.HashMap;

public class MyApplication extends Application {

    /* Jettison will not be used to convert to JSON
    Instead Jettison, GSON from google will be used
    The class GsonMessageBodyHandler will be responsible
    to convert the json with Google GSon

    @Override
    public Set<Object> getSingletons() {

        Set<Object> singletons = new HashSet<>();

        // Driver do Jettison to generate JSON
        singletons.add(new JettisonFeature());

        return singletons;
    }
    */

    // The class Geson MessageBodyHandler is responsible to convert json using Google Gson
    @Override
    public Map<String, Object> getProperties() {

        Map<String, Object> properties = new HashMap<>();

        // Set the packages which have the REST annotation
       properties.put("jersey.config.server.provider.packages", "br.com.brinkaedekora");
        return properties;
    }
}
