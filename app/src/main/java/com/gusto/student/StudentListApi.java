package com.gusto.student;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.gusto.student.adapter.StudentImageListAdapter;
import com.gusto.student.obj.StudentObj;
import com.gusto.student.util.Util;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.DeleteBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentListApi extends AppCompatActivity {

    private StudentImageListAdapter studentImageListAdapter;
    private RecyclerView student_list;
    private String LIST_REQUEST = "LIST_REQUEST";
    private MyApplication myApplication;

    private Dao<StudentObj,?> studentObjDao;

    private ProgressDialog progressDialog;

    private ArrayList<StudentObj> studentObjs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        myApplication = (MyApplication) getApplicationContext();

        student_list = findViewById(R.id.student_list);

        studentObjs = new ArrayList<>();

        LoadUI();

        if(Util.isOnline(getApplicationContext())){
            RequestStudentList();
        }else{
            Toast.makeText(getApplicationContext(),"No internet connection!",Toast.LENGTH_SHORT).show();
        }

    }


    public void LoadUI(){

//        try {
//            studentObjDao = myApplication.getDBHelper().getDao(StudentObj.class);
//            studentObjs  = (ArrayList<StudentObj>) studentObjDao.queryBuilder().query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            studentObjDao = myApplication.getDBHelper().getDao(StudentObj.class);
//            studentObjs  = (ArrayList<StudentObj>) studentObjDao.queryBuilder().where().eq("address","Sanchung").query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            studentObjDao = (Dao<StudentObj, ?>) myApplication.getDBHelper().getDao(StudentObj.class);
//            DeleteBuilder<StudentObj,?> objDeleteBuilder = studentObjDao.deleteBuilder();
//            objDeleteBuilder.where().eq("address","Sanchung");
//            studentObjDao.delete(objDeleteBuilder.prepare());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            studentObjDao = myApplication.getDBHelper().getDao(StudentObj.class);
//            studentObjs = (ArrayList<StudentObj>) studentObjDao.queryBuilder().query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            String query = "select * from tbl_student where address='Sanchung'";
//            studentObjDao = myApplication.getDBHelper().getDao(StudentObj.class);
//            GenericRawResults<StudentObj> objGenericRawResults = studentObjDao.queryRaw(query,studentObjDao.getRawRowMapper());
//            studentObjs = (ArrayList<StudentObj>) objGenericRawResults.getResults();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        studentImageListAdapter = new StudentImageListAdapter(StudentListApi.this, studentObjs);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        student_list.setLayoutManager(mLayoutManager);
        student_list.setItemAnimator(new DefaultItemAnimator());
        student_list.setAdapter(studentImageListAdapter);

    }

    public void RequestStudentList() {

        String url = "https://firebasestorage.googleapis.com/v0/b/gustostudent-96ef4.appspot.com/o/student.json?alt=media&token=e48f2e91-2778-4336-a847-1579526cc029";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,
                prepareJson(), new MyResponseListener(), new MyErrorListener());


        request.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 0, 1.0f));
        request.setTag(LIST_REQUEST);
        myApplication.addToRequestQueue(request,
                LIST_REQUEST);

        if(progressDialog == null){
            progressDialog = new ProgressDialog(StudentListApi.this);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

    }

    public void DismissDialog(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
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

            int status = 0;
            String message = "";

            try{
                status = response.getInt("status");
            }catch (Exception e){
                e.printStackTrace();
            }

            try{
                message = response.getString("message");
            }catch (Exception e){
                e.printStackTrace();
            }

            JSONArray jsonArray = null;

            try {
                jsonArray = response.getJSONArray("list");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i = 0 ; jsonArray.length() > i ; i++){
                StudentObj studentObj = new StudentObj();
                try {
                    studentObj.setId(jsonArray.getJSONObject(i).getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObj.setName(jsonArray.getJSONObject(i).getString("name"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObj.setAddress(jsonArray.getJSONObject(i).getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObj.setPhone(jsonArray.getJSONObject(i).getString("phone"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObj.setPhoto(jsonArray.getJSONObject(i).getString("photo"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObj.setActive(jsonArray.getJSONObject(i).getInt("active"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    studentObjDao = myApplication.getDBHelper().getDao(StudentObj.class);
                    studentObjDao.createOrUpdate(studentObj);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //studentObjs.add(studentObj);

                Log.v("StudentObj", ""+studentObj.toString());
            }


            if(status == 1){
                LoadUI();
            }

            DismissDialog();

//            Log.v("Student List", "" + response.toString());

        }

    }

    class MyErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError error) {
            DismissDialog();
            Log.e("StudentObj", "" + error.toString());
        }

    }
}
