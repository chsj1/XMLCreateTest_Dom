package com.example.xmlcreatetest_dom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.content.Context;
import android.os.Environment;

public class XMLCreateByDOMUtils {
	public static void createXmlFile() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			// 创建xml根元素
			Element rootEle = doc.createElement("classes");
			doc.appendChild(rootEle);
			// 创建xml二级元素
			Element groupEle = doc.createElement("group");
			groupEle.setAttribute("name", "yinianji");
			groupEle.setAttribute("num", "10");
			// 创建xml person元素
			Element personEle = doc.createElement("person");
			// personEle 的属性和属性值
			personEle.setAttribute("name", "xiaoming");
			personEle.setAttribute("age", "7");
			// 创建personELe的子元素
			Element chinese = doc.createElement("chinese");
			// 创建personELe的子元素的值
			chinese.appendChild(doc.createTextNode("chinese 90"));
			personEle.appendChild(chinese);
			Element english = doc.createElement("english");
			english.appendChild(doc.createTextNode("english 80"));
			personEle.appendChild(english);
			groupEle.appendChild(personEle);
			rootEle.appendChild(groupEle);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			// 创建文件存放在 /data/data/com.xxx.xxx(当前包)/files
			// FileOutputStream fos = openFileOutput("Dom.xml",
			// Context.MODE_PRIVATE);
			File newxmlfile = new File(
					Environment.getExternalStorageDirectory()
							+ "/xmlparser_person_by_dom.xml");
			FileOutputStream fos = new FileOutputStream(newxmlfile);
			// 创建文件存放在 /data/data/com.xxx.xxx(当前包)/cache
			// FileOutputStream fos = Op
			PrintWriter pw = new PrintWriter(fos);
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("生成XML文件成功!");
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
