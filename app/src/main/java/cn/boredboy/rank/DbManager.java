package cn.boredboy.rank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by pc on 2016/12/15.
 */

public class DbManager {
    public static MySQLiteOpenHelper helper;
    public static MySQLiteOpenHelper getInstance(Context context){
        if(helper == null){
            helper = new MySQLiteOpenHelper(context);
        }
        return helper;
    }
    public static void execSql(SQLiteDatabase db, String sql){
        if(db != null){
            if(sql != null && !sql.equals("")){
                db.execSQL(sql);
            }
            else{
                Log.d(TAG, "SQLexec: sql语句为空");
            }
        }
    }
}
