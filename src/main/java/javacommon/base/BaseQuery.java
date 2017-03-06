package javacommon.base;

import com.rapid.framework.page.PageRequest;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/10.
 */
public class BaseQuery extends PageRequest implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;

    public BaseQuery() {
        setPageSize(DEFAULT_PAGE_SIZE);
    }
}
