package com.gusto.student.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gusto.student.R;
import com.gusto.student.obj.StudentObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentImageListAdapter extends RecyclerView.Adapter<StudentImageListAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    private Activity activity;
    private ArrayList<StudentObj> studentObjs;


    public StudentImageListAdapter(Activity activity, ArrayList<StudentObj> studentObjs) {
        this.mInflater = LayoutInflater.from(activity);
        this.activity = activity;
        this.studentObjs = studentObjs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.student_image_item_layout, viewGroup, false);
        return new StudentImageListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.name_tv.setText(studentObjs.get(position).getName());
        viewHolder.address_tv.setText(studentObjs.get(position).getAddress());
        viewHolder.phone_tv.setText(studentObjs.get(position).getPhone());

        try{
            Picasso.get().load(studentObjs.get(position).getPhoto()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.thumb_iv);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return studentObjs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name_tv;
        public TextView address_tv;
        public TextView phone_tv;
        public ImageView thumb_iv;

        public ViewHolder(View view) {
            super(view);
            this.name_tv = view.findViewById(R.id.tv_name);
            this.address_tv = view.findViewById(R.id.tv_address);
            this.phone_tv = view.findViewById(R.id.tv_phone);
            this.thumb_iv = view.findViewById(R.id.thumb_iv);
        }
    }

}
