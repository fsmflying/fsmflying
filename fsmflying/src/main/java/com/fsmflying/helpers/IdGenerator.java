package com.fsmflying.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

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

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class IdGenerator {

	private static IdGenerator mIdGenerator = null;

	public static IdGenerator newInstance() {
		if (mIdGenerator == null)
			mIdGenerator = new XmlIdGenerator();
		return mIdGenerator;
	}

	public abstract int nextInt();

	public abstract int nextInt(String key);

	public abstract int[] nextInts(int count);

	public abstract int[] nextInts(String key, int count);
}

class XmlIdGenerator extends IdGenerator {

	private File mFile = null;
	private HashMap<String, Integer> mIdMap = null;
	private Document mDocument = null;

	public XmlIdGenerator() {
		mIdMap = new HashMap<String, Integer>();
		mFile = new File("keys.xml");

		try {
			Element root = null;
			if (!mFile.exists()) {
				this.mDocument = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().newDocument();
				Comment comment = this.mDocument
						.createComment("This file is created by 'com.fsmflying.helpers.IdGenerator#XmlIdGenerator'");
				this.mDocument.appendChild(comment);
				root = this.mDocument.createElement("keys");
				this.mDocument.appendChild(root);
				Element defaultNode = this.mDocument.createElement("default");
				defaultNode.setTextContent("0");
				root.appendChild(defaultNode);
				writeDocument();
			} else {
				this.mDocument = DocumentBuilderFactory.newInstance()
						.newDocumentBuilder().parse(this.mFile);

			}
			root = this.mDocument.getDocumentElement();
			NodeList list = root.getChildNodes();
			Element node = null;
			Integer value = null;
			for (int i = 0; i < list.getLength(); i++) {
				node = (Element) list.item(i);
				if (!mIdMap.containsKey(node.getTagName())) {
					value = Integer.parseInt(node.getTextContent());
					mIdMap.put(node.getTagName(), value);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private boolean writeDocument() {
		Element root = this.mDocument.getDocumentElement();
		try {
			Transformer trans = TransformerFactory.newInstance()
					.newTransformer();
			trans.setOutputProperty(OutputKeys.ENCODING,"GB2312");
			
			trans.transform(new DOMSource(root), new StreamResult(
					new FileOutputStream("keys.xml")));
			return true;
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int nextInt(String key) {
		key = key.toLowerCase().trim();
		Element root = this.mDocument.getDocumentElement();
		if (mIdMap.containsKey(key)) {
			NodeList list = root.getChildNodes();
			Element node = null;
			for (int i = 0; i < list.getLength(); i++) {
				node = (Element) list.item(i);
				if (node.getTagName().equals(key)) {
					node.setTextContent("" + (mIdMap.get(key) + 1));
				}
			}
			mIdMap.put(key, mIdMap.get(key) + 1);
		} else {
			Node newE = this.mDocument.createElement(key);
			newE.setTextContent("1");
			root.appendChild(newE);
			mIdMap.put(key, 1);
		}
		writeDocument();

		return mIdMap.get(key);
	}

	@Override
	public int[] nextInts(String key, int count) {
		int[] resultInts = new int[count];
		int startId = -1;
		key = key.toLowerCase().trim();
		Element root = this.mDocument.getDocumentElement();
		if (mIdMap.containsKey(key)) {
			startId = mIdMap.get(key) + 1;
			NodeList list = root.getChildNodes();
			Element node = null;
			for (int i = 0; i < list.getLength(); i++) {
				node = (Element) list.item(i);
				if (node.getTagName().equals(key)) {
					node.setTextContent("" + (mIdMap.get(key) + count));
				}
			}
			mIdMap.put(key, mIdMap.get(key) + count);
		} else {
			startId = 1;
			Node newE = this.mDocument.createElement(key);
			newE.setTextContent(count + "");
			root.appendChild(newE);
			mIdMap.put(key, count);

		}
		writeDocument();
		for (int i = 0; i < count; i++) {
			resultInts[i] = startId + i;
		}
		return resultInts;
	}

	@Override
	public int nextInt() {
		return nextInt("default");
	}

	@Override
	public int[] nextInts(int count) {
		// TODO Auto-generated method stub
		return nextInts("default", count);
	}

}