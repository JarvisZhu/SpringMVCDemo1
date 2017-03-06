package javacommon.base;

import com.rapid.framework.page.Page;
import com.rapid.framework.page.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;

/**
 * Created by Administrator on 2015/9/17.
 */
public class BaseController<T, PK> {
    public static ModelMap toModelMap(Page page, PageRequest pageRequest) {
        return toModelMap("", page, pageRequest);
    }

    public static ModelMap toModelMap(String tableId, Page page, PageRequest pageRequest) {
        ModelMap model = new ModelMap();
        saveIntoModelMap(tableId, page, pageRequest, model);
        return model;
    }

    public static void saveIntoModelMap(String tableId, Page page, PageRequest pageRequest, ModelMap model) {
        Assert.notNull(tableId, "tableId must be not null");
        Assert.notNull(page, "page must be not null");

        model.addAttribute(tableId + "page", page);
        model.addAttribute(tableId + "totalRows", new Integer(page.getTotalCount()));
        model.addAttribute(tableId + "pageRequest", pageRequest);
        model.addAttribute(tableId + "query", pageRequest);
    }
}
