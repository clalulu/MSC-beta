package it.msc.model;

import java.util.List;

public class EventJson {
   
    private int total; // jqGrid에 표시할 전체 페이지 수
    private int page; // 현재 페이지
    private int records; // 전체 레코드(row)수
    private List<Event> rows; // 목록
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
    public List<Event> getRows() {
        return rows;
    }
    public void setRows(List<Event> rows) {
        this.rows = rows;
    }  
}
