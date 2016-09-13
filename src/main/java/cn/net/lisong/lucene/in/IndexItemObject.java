package cn.net.lisong.lucene.in;

import org.apache.lucene.document.Field.Store;

public class IndexItemObject{
	
	private Store store;
	
	private Object data;
	
	private String field;
	
	private IndexType itemType;

	public IndexItemObject(String field,Object data,Store store,IndexType itemType){
		this.store = store;
		this.data = data;
		this.field = field;
		this.itemType = itemType;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public IndexType getItemType() {
		return itemType;
	}

	public void setItemType(IndexType itemType) {
		this.itemType = itemType;
	}
}