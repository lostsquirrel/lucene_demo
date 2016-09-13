package cn.net.lisong.solr.http;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

public class SolrjTest {

	public static void main(String[] args) {
//		String url = "http://localhost:8983/solr/demo";
//		SolrServer server = new HttpSolrServer(url);

		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id222", 1.0f);
		doc1.addField("name", "doc222", 1.0f);
		doc1.addField("content", "content222");
		
	}
	
	public static void deprecatedMethod() {
//		String url = "http://localhost:8983/solr/demo";
//		SolrServer server = new HttpSolrServer(url);
		
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id222", 1.0f);
		doc1.addField("name", "doc222", 1.0f);
		doc1.addField("content", "content222");
		
		//
//		UpdateRequest req = new UpdateRequest();
//		req.setAction(UpdateRequest.ACTION.COMMIT, false, false);
//		req.add(doc1);
//		try {
//			UpdateResponse rsp = req.process(server);
//		} catch (SolrServerException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public static void createDoc() {
		String url = "http://localhost:8983/solr/demo";
		SolrServer server = new HttpSolrServer(url);

		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", "id111", 1.0f);
		doc1.addField("name", "doc111", 1.0f);
		doc1.addField("content", "content111");

		try {
			server.add(doc1);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
