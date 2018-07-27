package pers.dcg.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

public class XmlDemo {
    public static void main(String[] args) {
        try {
            //创建sax的实例解析xml文件
            SAXReader reader = new SAXReader();
            //读取xml文件
            Document doc = reader.read(SAXReader.class.getResourceAsStream("/stus.xml"));
            //获得根元素
            Element rootElement = doc.getRootElement();


            /**
            //获得根元素 stus的所有直接子元素
            List<Element> elements = rootElement.elements();
            //遍历输出
            for(Element ele: elements){
                //输出ele节点下的name节点的text内容
                System.out.println(ele.element("name").getText());

            }
             */
            //使用Xpath语法
            List<Node> eles = rootElement.selectNodes("//name");
            for(Node ele : eles){
                System.out.println(ele.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
