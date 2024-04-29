package com.example.huongdan2404;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDB extends SQLiteOpenHelper {

    public static final String TableName = "Student";
    public static final String SBD = "Sbd";
    public static final String Name = "Name";
    public static final String Toan = "Toan";
    public static final String Ly = "Ly";
    public static final String Hoa = "Hoa";
    public MyDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists " + TableName + "("
                + SBD + " Text, "
                + Name + " Text, "
                + Toan + " Double, "
                + Ly + " Double, "
                + Hoa + " Double)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + TableName);
    }

    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SBD,student.getSbd());
        value.put(Name,student.getName());
        value.put(Toan,student.getToan());
        value.put(Ly,student.getLy());
        value.put(Hoa,student.getHoa());
        db.insert(TableName,null,value);
        db.close();
    }
    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> list = new ArrayList<>();
        String sql = "Select * from " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                Student contact = new Student(cursor.getString(0),
                        cursor.getString(1),cursor.getDouble(2),
                        cursor.getDouble(3),cursor.getDouble(4));
                list.add(contact);
            }
        }
        return list;
    }

    public void updateStudent(String sbd,Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SBD,student.getSbd());
        value.put(Name,student.getName());
        value.put(Toan,student.getToan());
        value.put(Ly,student.getLy());
        value.put(Hoa,student.getHoa());
        db.update(TableName,value,SBD + "=?",new String[]{sbd});
        db.close();
    }

    public void deleteStudent(String sbd){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM " + TableName + " WHERE Sbd = ?";

        // Use parameterized query to avoid SQL injection
        db.execSQL(sql, new String[]{sbd});
        db.close();
    }

}
