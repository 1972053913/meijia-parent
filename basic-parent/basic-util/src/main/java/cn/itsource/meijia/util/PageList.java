package cn.itsource.meijia.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装数据
 * @param <T>
 */
public class PageList<T> {

    private Long total;/*总条数*/
    private List<T> rows=new ArrayList<>();/*封装当前页的数据*/

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
