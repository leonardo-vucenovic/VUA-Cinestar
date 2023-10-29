/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.factory;

import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author Leonardo
 */
public class ParserFactory {

    public static XMLEventReader createStaxParser(InputStream is) throws XMLStreamException {
        return XMLInputFactory.
                newFactory().
                createXMLEventReader(is);
    }

    private ParserFactory() {
        
    }
    
}
