package test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ModifyXMLFileInJava {

	public static void main(String[] args) {
		String filePath = "E:\\01.CA_NHAN\\02. HOC TAP\\users.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();

			// parse xml file and load into document
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();

			// update Element value
			// updateElementValue(doc,"parameter", "A1","HAIP");
			//deleteElement(doc);
			deletePerson (doc,"HAI");
			// delete element
			// deleteElement(doc);

			// add new element
			// addElement(doc);

			// write the updated document to file or console
			writeXMLFile(doc);

		} catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
			e1.printStackTrace();
		}
	}

	private static void writeXMLFile(Document doc)
			throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		doc.getDocumentElement().normalize();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("E:\\01.CA_NHAN\\02. HOC TAP\\users_updated.xml"));
		Writer out = new StringWriter();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(doc), new StreamResult(out));
		System.out.println(out.toString());
		// transformer.transform(source, result);
		System.out.println("XML file updated successfully");
	}

	/**
	 * Add a new element salary to user element.
	 * 
	 * @param doc
	 */
	private static void addElement(Document doc) {
		NodeList users = doc.getElementsByTagName("User");
		Element emp = null;

		// loop for each user
		for (int i = 0; i < users.getLength(); i++) {
			emp = (Element) users.item(i);
			Element salaryElement = doc.createElement("salary");
			salaryElement.appendChild(doc.createTextNode("10000"));
			emp.appendChild(salaryElement);
		}
	}

	/**
	 * Delete gender element from User element
	 * 
	 * @param doc
	 */
	private static void deleteElement(Document doc) {
		NodeList users = doc.getElementsByTagName("User");
		Element user = null;
		// loop for each user
		for (int i = 0; i < users.getLength(); i++) {
			user = (Element) users.item(i);
			Node genderNode = user.getElementsByTagName("parameter").item(2);
			user.removeChild(genderNode);
		}
	}

	public static void deletePerson(Document doc, String personName) {
        // <person>
        NodeList nodes = doc.getElementsByTagName("User");

        for (int i = 0; i < nodes.getLength(); i++) {
          Element person = (Element)nodes.item(i);
          // <name>
          Element name = (Element)person.getElementsByTagName("parameter").item(0);
          NamedNodeMap baseElmnt_gold_attr = name.getAttributes();
          for (int j = 0; j < baseElmnt_gold_attr.getLength(); j++)
			{
        	  if (personName.equals("HAI")) {
                  person.getParentNode().removeChild(person);
               }
			}
      }
	}

	/**
	 * Update firstName element value to Upper case.
	 * 
	 * @param doc
	 */
	private static void updateElementValue(Document doc, String tag, String attrs, String values) {
		NodeList baseElmntLst_gold = doc.getElementsByTagName(tag);
		NodeList users = doc.getElementsByTagName("User");
		for (int i = 0; i < baseElmntLst_gold.getLength(); i++) {
			Element baseElmnt_gold = (Element) baseElmntLst_gold.item(i);
			NamedNodeMap baseElmnt_gold_attr = baseElmnt_gold.getAttributes();
			for (int j = 0; j < baseElmnt_gold_attr.getLength(); j++) {
				Node attr = baseElmnt_gold_attr.item(j);
				
				System.out.println(attr.getNodeValue());
				if (attrs.equals(attr.getNodeValue())) {
					baseElmnt_gold.setTextContent(values);
				}
			}

		}
	}

	private static void updateElement(Document doc) throws XPathExpressionException {
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("//User/parameter[@name]");
		NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

		for (int i = 0; i < nl.getLength(); i++) {
			Node currentItem = nl.item(i);
			String key = currentItem.getAttributes().getNamedItem("id").getNodeValue();
			System.out.println(key);
		}
	}
}
