package com.gusto.student.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gusto.student.R;
import com.gusto.student.obj.StudentObj;

import java.util.ArrayList;

public class StudentGridAdapter extends RecyclerView.Adapter<StudentGridAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<StudentObj> studentObjs;


    public StudentGridAdapter(Activity activity, ArrayList<StudentObj> studentObjs) {
        this.mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.studentObjs = studentObjs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.student_item_grid_layout, viewGroup, false);
        return new StudentGridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.name_tv.setText(studentObjs.get(position).getName());
        viewHolder.address_tv.setText(studentObjs.get(position).getAddress());
        viewHolder.phone_tv.setText(studentObjs.get(position).getPhone());

    }

    @Override
    public int getItemCount() {
        return studentObjs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name_tv;
        public TextView address_tv;
        public TextView phone_tv;

        public ViewHolder(View view) {
            super(view);
            this.name_tv = view.findViewById(R.id.tv_name);
            this.address_tv = view.findViewById(R.id.tv_address);
            this.phone_tv = view.findViewById(R.id.tv_phone);
        }
    }

}
