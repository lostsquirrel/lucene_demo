 package cn.net.lisong.solr.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.net.lisong.lucene.in.SolrDemoIndexField;

public class CreateIndexLoc {
	
	public static void main(String[] args) throws IOException {
		String demoWriterPath = "E:/apache-solr/example/solr/demo/data/index/";
		
		Directory dir = FSDirectory.open(new File(demoWriterPath));
		
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_48, new IKAnalyzer());
				
		IndexWriter iw = new IndexWriter(dir, conf);
		
		Document doc = new Document();
		doc.add(new StringField(SolrDemoIndexField.ID,"id1", Field.Store.YES));
		doc.add(new StringField(SolrDemoIndexField.NAME, "name1", Field.Store.YES));
		iw.addDocument(doc);
		iw.close();
		System.out.println("---done---");
	}
	
}
