package cn.net.lisong.lucene.in;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class IndexService {

	private IndexWriter demoWriter;

	public IndexWriter getDemoWriter() {
		return demoWriter;
	}

	private String demoWriterPath = "E:/apache-solr/example/solr/demo/data/index/";

	public void createSolrDemoIndex(SolrDemo demo) {

		if (demoWriter == null) {
			initDemoWriter();
		}

		List<IndexItemObject> indexObjectList = new ArrayList<IndexItemObject>();

		indexObjectList.add(new IndexItemObject(SolrDemoIndexField.ID, demo
				.getId(), Store.YES, IndexType.LONG));

		String name = demo.getName() != null ? demo.getName() : "";
		indexObjectList.add(new IndexItemObject(SolrDemoIndexField.NAME, name,
				Store.YES, IndexType.TEXT_CN));

		String content = demo.getContent() != null ? demo.getContent() : "";
		indexObjectList.add(new IndexItemObject(SolrDemoIndexField.CONTENT, content,
				Store.NO, IndexType.TEXT_CN));
		
		IndexObject indexObject = new IndexObject(indexObjectList);

		try {
			indexDocs(demoWriter, indexObject);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void indexDocs(IndexWriter writer, IndexObject index)
			throws IOException {

		List<IndexItemObject> list = index.getList();
		Document doc = new Document();
		for (IndexItemObject iio : list) {

			try {
				if (iio.getItemType() == IndexType.STRING) {
					doc.add(new StringField(iio.getField(), (String) iio
							.getData(), iio.getStore()));
				} else if (iio.getItemType() == IndexType.FLOAT) {
					doc.add(new FloatField(iio.getField(), (Float) iio
							.getData(), iio.getStore()));
				} else if (iio.getItemType() == IndexType.INT) {
					doc.add(new IntField(iio.getField(), (Integer) iio
							.getData(), iio.getStore()));
				} else if (iio.getItemType() == IndexType.LONG) {
					doc.add(new LongField(iio.getField(), (Long) iio.getData(),
							iio.getStore()));
				} else if (iio.getItemType() == IndexType.TEXT_CN) {
					doc.add(new TextField(iio.getField(), (String) iio
							.getData(), iio.getStore()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		writer.addDocument(doc);
	
	}

	private void initDemoWriter() {
		
		Analyzer analyzer = new IKAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_48,
				analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		
		try {
			Directory outletDir = FSDirectory.open(new File(demoWriterPath));
			demoWriter = new IndexWriter(outletDir, iwc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
