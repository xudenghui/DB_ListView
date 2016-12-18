package cn.boredboy.rank;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.onClick;
import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements SQL{
//获取控制组件
    private EditText et_name;
    private EditText et_sex;
    private EditText et_score;
    private Button bt_insert;
    private Button bt_update;
    private Button bt_delete;
    private Button bt_rank;
    private Button bt_create;
    private MySQLiteOpenHelper helper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_name);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_score = (EditText) findViewById(R.id.et_score);
        bt_insert = (Button) findViewById(R.id.bt_insert);
        bt_update = (Button) findViewById(R.id.bt_update);
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_rank = (Button) findViewById(R.id.bt_rank);
        bt_create = (Button) findViewById(R.id.bt_create);

        helper = DbManager.getInstance(this);

    }
// 设置按钮监听方法
    public void click(View view){
        db = helper.getWritableDatabase();
        Bundle bundle = new Bundle();
        switch (view.getId()){
            case R.id.bt_insert:
                bundle.putString("name", et_name.getText().toString());
                bundle.putString("sex", et_sex.getText().toString());
                bundle.putString("score", et_score.getText().toString());
                Log.d(TAG, "click: insert调用");
                Toast.makeText(MainActivity.this, "insert被调用", Toast.LENGTH_SHORT).show();
                insert(db, bundle);
                break;
            case R.id.bt_update:
                break;
            case R.id.bt_delete:
                break;
            case R.id.bt_rank:
//                跳转至排名界面
                Intent intent = new Intent(MainActivity.this, ListView.class);
//                intent.putExtras(rank(db));
                startActivity(intent);
                break;
            case R.id.bt_create:
                File database = new File("data/data/cn.boredboy.rank/databases/info.db");
                if(database.exists()){
                    Toast.makeText(MainActivity.this, "数据库已存在", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    //insert
    public void insert(SQLiteDatabase db, Bundle bundle){
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, (String) bundle.get("name"));
        values.put(Constant.SEX, (String) bundle.get("sex"));
        values.put(Constant.SCORE, (String) bundle.get("score") );
        Log.d(TAG, "insert: "+values.get("name")+values.get("sex")+values.get("score"));
        db.insert(Constant.TABLE_NAME, null, values);
    }
    //rank
//    public Cursor rank(SQLiteDatabase db){
//        String sql_select = "select * from ";
//        Cursor cursor = db.rawQuery(sql_select + Constant.TABLE_NAME, null);
//        return cursor;
//    }
    @Override
    public MySQLiteOpenHelper getHelper() {
        return helper;
    }

    @Override
    public SQLiteDatabase getDB() {
        if(db == null)
            Log.d(TAG, "getDB: db为空-MainActivity");
        return db;
    }
}
