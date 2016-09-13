package cn.net.lisong.solr.test;

import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.net.lisong.lucene.in.IndexService;
import cn.net.lisong.lucene.in.SolrDemo;

public class CreateIndex {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		IndexService indexService = new IndexService();
		
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(CreateIndex.class.getClassLoader().getResourceAsStream("solrdemo.xml"));
			Element root=document.getRootElement();
			
			List<Element> records = root.elements();
			
			for (Element e : records) {
				String id = e.elementText("id");
				String name = e.elementText("name");
				String content = e.elementText("brief");
				if (id != null && !"".equals(id)) {
					SolrDemo s = new SolrDemo();
					s.setId(Long.parseLong(id));
					s.setName(name);
					s.setContent(content);
					indexService.createSolrDemoIndex(s);
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			indexService.getDemoWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
