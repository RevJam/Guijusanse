package com.mygdx.game.android.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sebastien on 28/12/14.
 */
public class Dao{

        protected final static int VERSION = 1;
        protected final static String NOM = "database.db";

        protected SQLiteDatabase mDb = null;
        protected DataBaseHandler mHandler = null;

        public Dao(Context pContext) {
            this.mHandler = new DataBaseHandler(pContext, NOM, null, VERSION);
        }

        public SQLiteDatabase open() {
            mDb = mHandler.getWritableDatabase();
            return mDb;
        }

        public void close() {
            mDb.close();
        }

        public SQLiteDatabase getDb() {
            return mDb;
        }

}
