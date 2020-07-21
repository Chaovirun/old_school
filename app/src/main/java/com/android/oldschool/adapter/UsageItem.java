package com.android.oldschool.adapter;

/**
 *  Created by Huo Chhunleng on 16/Feb/2017.
 */
public class UsageItem {
    private String ITEM_CODE;
    private String ITEM_NAME;

    public UsageItem() {}

    public UsageItem(String ITEM_CODE, String ITEM_NAME) {
        this.ITEM_CODE = ITEM_CODE;
        this.ITEM_NAME = ITEM_NAME;
    }
    public String getITEM_CODE() {
        return ITEM_CODE;
    }

    public void setITEM_CODE(String ITEM_CODE) {
        this.ITEM_CODE = ITEM_CODE;
    }

    public String getITEM_NAME() {
        return ITEM_NAME;
    }

    public void setITEM_NAME(String ITEM_NAME) {
        this.ITEM_NAME = ITEM_NAME;
    }
}
