package br.com.brinkaedekora.util;

import java.io.IOException;
import java.io.StringWriter;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import br.com.brinkaedekora.models.Produto;
import br.com.brinkaedekora.domain.ListaProdutos;

public class JAXBUtil {

    private static JAXBUtil instance;

    public static JAXBUtil getInstance() {
        return instance;
    }

    private static JAXBContext context;

    static {
        try{
            context = JAXBContext.newInstance(ListaProdutos.class, Produto.class);
        }catch (JAXBException e){
            throw new RuntimeException(e);
        }
    }

    public static String toXml(Object object) throws IOException {
        try {
            StringWriter stringWriter = new StringWriter();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, stringWriter);
            String xml = stringWriter.toString();
            return xml;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
