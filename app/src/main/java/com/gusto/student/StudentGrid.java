package com.gusto.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gusto.student.adapter.StudentGridAdapter;
import com.gusto.student.adapter.StudentListAdapter;
import com.gusto.student.obj.StaticStudentList;

public class StudentGrid extends AppCompatActivity {

    private StudentGridAdapter studentGridAdapter;
    private RecyclerView student_list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        student_list = findViewById(R.id.student_list);

        studentGridAdapter = new StudentGridAdapter(StudentGrid.this, StaticStudentList.getStudentList());

        //for grid view
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);

        student_list.setLayoutManager(mLayoutManager);
        student_list.setItemAnimator(new DefaultItemAnimator());
        student_list.setAdapter(studentGridAdapter);
    }
}
