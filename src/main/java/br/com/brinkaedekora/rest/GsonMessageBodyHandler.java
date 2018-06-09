package br.com.brinkaedekora.rest;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


@Provider
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charsert=utf-8")
public final class GsonMessageBodyHandler implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

    private static final String UTF_8 = "UTF-8";
    private Gson gson;

    public Gson getGson() {

        if (gson == null) {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
        return gson;
    }

    // Methods from Message Body Reader

    @Override
    public boolean isReadable(Class<?> aClass,
                              Type type,
                              Annotation[] annotations,
                              MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> aClass,
                           Type type,
                           Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> multivaluedMap,
                           InputStream inputStream) throws IOException, WebApplicationException {
        InputStreamReader streamReader = new InputStreamReader(inputStream, UTF_8);

        try {
            Type jsonType;

            if (aClass.equals(type)) {
                jsonType = aClass;
            } else {
                jsonType = type;
            }
            return getGson().fromJson(streamReader, jsonType);
        } finally {
            streamReader.close();
        }
    }


    // Methods from Message Body Writer

    @Override
    public boolean isWriteable(Class<?> aClass,
                               Type type,
                               Annotation[] annotations,
                               MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object o,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Object o,
                        Class<?> aClass,
                        Type type,
                        Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap,
                        OutputStream outputStream) throws IOException, WebApplicationException {

        OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, UTF_8);

        try {
            Type jsonType;
            if (aClass.equals(type)) {
                jsonType = aClass;
            } else {
                jsonType = type;
            }

            getGson().toJson(o, jsonType, streamWriter);

        } finally {
            streamWriter.close();
        }
    }
}
