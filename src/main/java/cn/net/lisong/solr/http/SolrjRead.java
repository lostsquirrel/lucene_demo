package cn.net.lisong.solr.http;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import cn.net.lisong.lucene.in.SolrDemo;

public class SolrjRead {

	public static void main(String[] args) {
		String url = "http://localhost:8983/solr/demo";
		SolrServer server = new HttpSolrServer(url);

		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.addSortField("id", SolrQuery.ORDER.asc);

		try {
			QueryResponse rsp = server.query(query);

			SolrDocumentList docs = rsp.getResults();

			List<SolrDemo> beans = rsp.getBeans(SolrDemo.class);

			System.out.println(beans.get(0).getContent());
			System.out.println(docs);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
	}
}
