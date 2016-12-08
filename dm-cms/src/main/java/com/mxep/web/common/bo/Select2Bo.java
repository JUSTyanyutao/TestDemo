package com.mxep.web.common.bo;

import java.util.List;

/**
 * Created by ranfi on 4/20/16.
 */
public class Select2Bo {

    private Long totalCount;

    private List<SelectOption> items;


    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<SelectOption> getItems() {
        return items;
    }

    public void setItems(List<SelectOption> items) {
        this.items = items;
    }
}
