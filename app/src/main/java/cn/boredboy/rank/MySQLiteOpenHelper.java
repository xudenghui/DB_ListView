package cn.boredboy.rank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by pc on 2016/12/15.
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MySQLiteOpenHelper(Context context) {
        //调用父类方法创建数据库
        super(context, Constant.DATABASE_NAME, null, Constant.DATA_VERSION);
        Log.d(TAG, "MySQLiteOpenHelper: 创建数据库");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+Constant.TABLE_NAME
                +" (" + Constant.NAME +" varchar(20) primary key,"+Constant.SEX +
                " char(2)," + Constant.SCORE + " Integer)";
        db.execSQL(sql);
        Log.d(TAG, "onCreate: 被调用");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "数据库onUpgrade: 被调用");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TAG, "数据库onOpen: 被调用");
    }}
