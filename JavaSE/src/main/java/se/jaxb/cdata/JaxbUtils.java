package se.jaxb.cdata;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import net.sf.cglib.proxy.Enhancer;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbUtils {

    public static String obj2XmlWithCData(Object obj) throws XMLStreamException, JAXBException {

        StringWriter writer = new StringWriter();
        XMLStreamWriter streamWriter = XMLOutputFactory.newInstance()
                .createXMLStreamWriter(writer);

        XMLStreamWriter cdataStreamWriter = (XMLStreamWriter) Enhancer.create(XMLStreamWriter.class, new CDataHandler(streamWriter));

        JAXBContext jc = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jc.createMarshaller();

        marshaller.setListener(new MarshallerDealNullListener());
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        marshaller.marshal(obj, cdataStreamWriter);
        return writer.toString();
    }

    public static <T> T xml2bean(String entity, Class<T> entityClass) throws JAXBException {
        JAXBContext act = JAXBContext.newInstance(entityClass);
        Unmarshaller unMarshaller = act.createUnmarshaller();
        return (T) unMarshaller.unmarshal(new StringReader(entity));
    }

    public static String bean2xml(Object entity) throws JAXBException {
        StringWriter writer = new StringWriter();
        JAXBContext act = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = act.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(entity, writer);
        return writer.toString();
    }


}
