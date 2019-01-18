package com.gusto.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gusto.student.adapter.StudentListAdapter;
import com.gusto.student.obj.StaticStudentList;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentList extends AppCompatActivity {

    private StudentListAdapter studentListAdapter;
    private RecyclerView student_list;
    private MyApplication myApplication;
    private String LIST_REQUEST = "LIST_REQUEST";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        myApplication = (MyApplication) getApplicationContext();

        student_list = findViewById(R.id.student_list);

        studentListAdapter = new StudentListAdapter(StudentList.this, StaticStudentList.getStudentList());

        ////for list view
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        student_list.setLayoutManager(mLayoutManager);
        student_list.setItemAnimator(new DefaultItemAnimator());
        student_list.setAdapter(studentListAdapter);

        RequestStudentList();
    }


    public void RequestStudentList() {

        String url = "https://firebasestorage.googleapis.com/v0/b/gustostudent-96ef4.appspot.com/o/student.json?alt=media&token=e3238c0c-424f-4778-b335-24be34abf71a";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                prepareJson(), new MyResponseListener(), new MyErrorListener());


        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, 1.0f));
        request.setTag(LIST_REQUEST);
        myApplication.addToRequestQueue(request,
                LIST_REQUEST);


    }

    JSONObject prepareJson() {
        JSONObject js = new JSONObject();
        try {
            js.put("version", 1);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return js;
    }

    class MyResponseListener implements Response.Listener<JSONObject> {

        @Override
        public void onResponse(JSONObject response) {

            Log.v("Student List", "" + response.toString());

        }

    }

    class MyErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Student List", "" + error.toString());
        }

    }
}
