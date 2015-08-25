package it.msc.model;

import java.util.List;

public class ElectricJson {
	
	 private int total; 
	    private int page; 
	    private int records; 
	    private List<Electric> rows; 
	    public int getTotal() {
	        return total;
	    }
	    public void setTotal(int total) {
	        this.total = total;
	    }
	    public int getPage() {
	        return page;
	    }
	    public void setPage(int page) {
	        this.page = page;
	    }
	    public int getRecords() {
	        return records;
	    }
	    public void setRecords(int records) {
	        this.records = records;
	    }
	    public List<Electric> getRows() {
	        return rows;
	    }
	    public void setRows(List<Electric> rows) {
	        this.rows = rows;
	    }  

}
