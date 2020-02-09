package com.nowcoder.community.entity;


//封装分页相关的组件
public class Page {

    //前端传来当前显示的页码
    private int currentPageNumber = 1;
    // 每页显示数据上限
    private int maxRowsPerPage = 10;
    // 数据总数
    private int rowsMaxCount;
    // 查询路径
    private String path;

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        if (currentPageNumber >= 1) {
            this.currentPageNumber = currentPageNumber;
        }
    }

    public int getmaxRowsPerPage() {
        return maxRowsPerPage;
    }

    public void setmaxRowsPerPage(int maxRowsPerPage) {
        if (maxRowsPerPage >= 1 && maxRowsPerPage <= 100) {
            this.maxRowsPerPage = maxRowsPerPage;
        }

    }

    public int getRowsMaxCount() {
        return rowsMaxCount;
    }

    public void setRowsMaxCount(int rowsMaxCount) {
        if (rowsMaxCount >= 0) {
            this.rowsMaxCount = rowsMaxCount;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPageNumber=" + currentPageNumber +
                ", maxRowsPerPage=" + maxRowsPerPage +
                ", rowsMaxCount=" + rowsMaxCount +
                ", path='" + path + '\'' +
                '}';
    }

    // 数据库在查询数据时，需要的是offset-起始行，而不是页码，所以需要使用页码计算出起始行
    public int getOffSet() {
        return currentPageNumber * maxRowsPerPage - maxRowsPerPage;
    }

    //获取总页数
    public int getTotalPageNumber() {
        //
        if (rowsMaxCount % maxRowsPerPage == 0) {
            return rowsMaxCount / maxRowsPerPage;
        } else {
            return rowsMaxCount / maxRowsPerPage + 1;
        }
    }


}
