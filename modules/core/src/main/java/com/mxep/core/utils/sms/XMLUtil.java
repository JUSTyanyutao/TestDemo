package com.mxep.core.utils.sms;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * XML工具
 */
public final class XMLUtil {
	
	/**
	 * @param text
	 * @param nodeName
	 * @return
	 * @throws DocumentException
	 * 解析字符串格式的xml，获取指定node的值
	 */
	public static String parseXML(String text, String nodeName) throws DocumentException {
		Document document = DocumentHelper.parseText(text);
		Element root = document.getRootElement();
		Element node = root.element(nodeName);
		if (node == null) {
			return null;
		}
		String nodeText = node.getTextTrim();
		return nodeText;
	}

}
