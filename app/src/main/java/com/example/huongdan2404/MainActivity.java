package com.example.huongdan2404;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    public ListView lvStudent;
    private int SelectedItemId;
    public Adapter listStudentAdapter;
    public EditText etSearch;
    public FloatingActionButton flbtnAdd;
    public ArrayList<Student> listStudent;
    public MyDB db;
    ContentProvider cp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.lvStudent);
        etSearch = findViewById(R.id.etSearch);
        flbtnAdd = findViewById(R.id.flbtnAdd);
        listStudent = new ArrayList<>();
        db = new MyDB(this,"StudentDB2",null,1);
//        db.addStudent(new Student("fsdf","fsdf",5.2,6.3,4.5));
//        db.addStudent(new Student("qweqw","qweq",5.7,9.3,2.5));
//        db.addStudent(new Student("cxvb","yuty",6.4,7.9,1.0));
        listStudent = db.getAllStudent();
        listStudentAdapter = new Adapter(this,listStudent);
        lvStudent.setAdapter(listStudentAdapter);
        flbtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class);
                startActivityForResult(intent,100);
            }
        });
        registerForContextMenu(lvStudent);
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SelectedItemId = position;
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        String name = b.getString("Name");
        String sbd = b.getString("SBD");
        double toan = b.getDouble("Toan");
        double ly = b.getDouble("Ly");
        double hoa = b.getDouble("Hoa");
        Student newstudent = new Student(sbd,name,toan,ly,hoa);
        if(requestCode == 100 && resultCode == 150) {
            //listStudent.add(newstudent);
            db.addStudent(newstudent);
            listStudentAdapter = new Adapter(this,listStudent);
            listStudentAdapter.notifyDataSetChanged();
        } else if (requestCode == 200 && resultCode == 150){
            listStudent.set(SelectedItemId,newstudent);
            db.updateStudent(newstudent.getSbd(),newstudent);
            Toast.makeText(this, newstudent.getName(), Toast.LENGTH_SHORT).show();
            lvStudent.setAdapter(listStudentAdapter);
            listStudentAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnuSortByTD){
            Toast.makeText(this,"Sort by TD",Toast.LENGTH_SHORT).show();
            listStudent = db.getAllStudent();
            // sort the list by getSum()
            // giam dan
            Collections.sort(listStudent, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    // Compare the sums of two students in reverse order
                    return Double.compare(s2.getSum(), s1.getSum());
                }
            });
            listStudentAdapter = new Adapter(this,listStudent);
            lvStudent.setAdapter(listStudentAdapter);
        } else if(item.getItemId() == R.id.mnuSortBySBD){
            Toast.makeText(this,"Sort by SBD",Toast.LENGTH_SHORT).show();
            listStudent = db.getAllStudent();
            // sort the list by getSBD() return string
            Collections.sort(listStudent, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    // Compare the strings returned by getSbd() of two students
                    return s1.getSbd().compareTo(s2.getSbd());
                }
            });
            listStudentAdapter = new Adapter(this,listStudent);
            lvStudent.setAdapter(listStudentAdapter);
        } else if(item.getItemId() == R.id.mnuSortByDTB){
            Toast.makeText(this,"Sort by DTB",Toast.LENGTH_SHORT).show();
            listStudent = db.getAllStudent();
            // sort the list by getAvg()
            Collections.sort(listStudent, new Comparator<Student>() {
                @Override
                public int compare(Student s1, Student s2) {
                    // Compare the avg of two students
                    return Double.compare(s1.getAvg(), s2.getAvg());
                }
            });
            listStudentAdapter = new Adapter(this,listStudent);
            lvStudent.setAdapter(listStudentAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.actionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.contextmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Student s = listStudent.get(SelectedItemId);
        if(item.getItemId() == R.id.mnuEdit){
            Toast.makeText(this,"Edit",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,SubActivity.class);
            Bundle b = new Bundle();
            b.putString("Name",s.getName());
            b.putString("SBD",s.getSbd());
            b.putDouble("Toan",s.getToan());
            Toast.makeText(this,"Toan" + s.getToan(),Toast.LENGTH_SHORT).show();
            b.putDouble("Ly",s.getLy());
            b.putDouble("Hoa",s.getHoa());
            intent.putExtras(b);
            startActivityForResult(intent,200);
        } else if (item.getItemId() == R.id.mnuDelete) {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            db.deleteStudent(s.getSbd());
            listStudent = db.getAllStudent();
            listStudentAdapter = new Adapter(this,listStudent);
            lvStudent.setAdapter(listStudentAdapter);
        } else if(item.getItemId() == R.id.mnuSearch){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
            }
            else {
                cp = new ContentProvider(this);
                String phone = cp.phoneNumberOfStudent(s);
                Toast.makeText(this, "Phone: " + phone, Toast.LENGTH_SHORT).show();
            }
        }
        return super.onContextItemSelected(item);
    }
}