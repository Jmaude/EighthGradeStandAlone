package com.example.eighthgradestandalone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter {


    private ArrayList<Student> studentData;
    private View.OnClickListener mOnItemClickListener;
    private Context parentContext;


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView textStdFirstName;
        public TextView textStdLastName;
        public TextView textStdId;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStdFirstName = itemView.findViewById(R.id.textStdNameFirst);
            textStdLastName = itemView.findViewById(R.id.textStdNameLast);
            textStdId = itemView.findViewById(R.id.textStdID);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);

        }

        public TextView getTextStdFirstName() {
            return textStdFirstName;
        }

        public TextView getTextStdLastName() {
            return textStdLastName;
        }

        public TextView getTextStdId() {
            return textStdId;
        }
    }

    public StudentAdapter(ArrayList<Student> arrayList, Context context) {
        studentData = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_std_list_item, parent, false);
        return new StudentViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentViewHolder svh = (StudentViewHolder) holder;
        svh.getTextStdFirstName().setText(studentData.get(position).getStdFirstName());
        svh.getTextStdLastName().setText(studentData.get(position).getStdLastName());
        svh.getTextStdId().setText(studentData.get(position).getStudentID());

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }
}
