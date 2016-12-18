package cn.boredboy.rank;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pc on 2016/12/18.
 */

public interface SQL {
    public MySQLiteOpenHelper getHelper();
    public SQLiteDatabase getDB();
}
