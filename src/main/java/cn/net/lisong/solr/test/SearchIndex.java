package cn.net.lisong.solr.test;

import java.io.IOException;
import java.text.ParseException;

import cn.net.lisong.lucene.out.SearchService;

public class SearchIndex {

	public static void main(String[] args) {
		try {
			SearchService searchService = new SearchService();
			searchService.performSearch("vs");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
