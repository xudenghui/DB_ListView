package cn.boredboy.rank;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by pc on 2016/12/15.
 */

public class ListView extends Activity{
    private android.widget.ListView lv;
    private List<Person> person = null;
    private Person item;
    private SQLiteDatabase db;
    private Cursor cursor;
    private int count;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.list);

        lv = (android.widget.ListView) findViewById(R.id.lv);
        lv.setAdapter(new MyAdapter());

//      获取数据库对象
        db = SQLiteDatabase.openOrCreateDatabase("data/data/cn.boredboy.rank/databases/info.db", null);
        cursor = db.rawQuery("select * from "+Constant.TABLE_NAME, null);
        person = saveCursor(cursor, person);
        Log.d(TAG, "onCreate: "+person.size());
        //获取person中元素个数
        count = person.size();
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Log.d(TAG, "getView坐标: "+i);

            Person p = person.get(i);
//          获取ListView填充器
            LayoutInflater inflater = LayoutInflater.from(ListView.this);
            View v = inflater.inflate(R.layout.list_item, null);
            if(db == null) {
                Log.d(TAG, "db为空");
                return null;
            }
            Log.d(TAG, "cursor的个数: "+cursor.getColumnCount());

            TextView list_no = (TextView) v.findViewById(R.id.list_no);
            TextView list_name = (TextView) v.findViewById(R.id.list_name);
            TextView list_sex = (TextView) v.findViewById(R.id.list_sex);
            TextView list_score = (TextView) v.findViewById(R.id.list_score);
            list_no.setText(i + 1 + "");
            list_name.setText(p.getName());
            list_sex.setText(p.getSex());
            list_score.setText(p.getScore());
            return v;
        }
    }
    //将数据库中的数据存入List<Person>
    public List<Person> saveCursor(Cursor cursor, List<Person> person){
        person = new ArrayList<>();
        int i = 0;
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String score = cursor.getString(cursor.getColumnIndex("score"));
            item = new Person(name, sex, score);
            person.add(item);
            Log.d(TAG, "saveCursor: name"+person.get(i).getName());
            i++;
        }
        return person;
    }
}
