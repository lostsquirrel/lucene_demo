package cn.net.lisong.lucene.out;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SearchService {

	private IndexSearcher searcher = null;

	private QueryParser parser = null;

	private String demoIndexPath = "E:/apache-solr/example/solr/demo/data/index/";

	private IndexReader indexReader;

	/** Creates a new instance of SearchEngine */
	public SearchService() throws IOException {

		Analyzer analyzer = new IKAnalyzer();
		parser = new QueryParser(Version.LUCENE_48, "content", analyzer);

		indexReader = DirectoryReader.open(FSDirectory.open(new File(
				demoIndexPath)));
		searcher = new IndexSearcher(indexReader);

	}

	public void performSearch(String queryString) throws IOException,
			ParseException {
		int hitsPerPage = 10;

		Query query;
		TopScoreDocCollector collector = TopScoreDocCollector.create(
				hitsPerPage, true);
		try {
			query = parser.parse(queryString);
			searcher.search(query, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			System.out.println("Found " + hits.length + " hits.");
			for (int i = 0; i < hits.length; ++i) {
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				System.out.println((i + 1) + ". " + d.get("id") + "\t"
						+ d.get("name"));
			}

		} catch (org.apache.lucene.queryparser.classic.ParseException e) {
			e.printStackTrace();
		}
		// reader can only be closed when there
		// is no need to access the documents any more.
		indexReader.close();
	}
}
