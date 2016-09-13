package cn.net.lisong.lucene.in;

import java.util.List;

public class IndexObject {
	List<IndexItemObject> list;

	public IndexObject(List<IndexItemObject> list) {
		this.list = list;
	}

	public List<IndexItemObject> getList() {
		return list;
	}

	public void setList(List<IndexItemObject> list) {
		this.list = list;
	}
}
