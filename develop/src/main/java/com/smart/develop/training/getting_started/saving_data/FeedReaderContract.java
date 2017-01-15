package com.smart.develop.training.getting_started.saving_data;

import android.provider.BaseColumns;

/**
 * FileName: FeedReaderContract
 *
 * Des: Training
 *
 *      --Getting Started
 *
 *      --Saving Data
 *
 *      数据库相关常量类
 *
 * Time: 2017/1/7 下午5:25
 */
public final class FeedReaderContract {
    /**
     * Des: 防止意外初始化常量类
     *
     * Time: 2017/1/7 下午5:29
     */
    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

}
