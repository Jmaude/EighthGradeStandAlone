package com.example.eighthgradestandalone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter {

    private ArrayList<Student> studentData;
    private View.OnClickListener mOnItemClickListener;
    private Context parentContext;

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView textStdFirstName;
        public TextView textStdLastName;
        public TextView textStdNum;
        public TextView textAmtPaid;
        public TextView textAmtDue;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStdFirstName = itemView.findViewById(R.id.listFirstName);
            textStdLastName = itemView.findViewById(R.id.listLastName);
            textStdNum = itemView.findViewById(R.id.listStdNum);
            textAmtPaid = itemView.findViewById(R.id.listAmountPaid);
            textAmtDue = itemView.findViewById(R.id.listAmountDue);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getTextStdFirstName() {return textStdFirstName;}
        public TextView getTextStdLastName() {return textStdLastName;}
        public TextView getTextStdNum() { return textStdNum;}
        public TextView getTextAmtPaid() {return textAmtPaid;}
        public TextView getTextAmtDue() {return textAmtDue;}
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        StudentViewHolder svh = (StudentViewHolder) holder;
        if (position % 2 == 0){
            ((StudentViewHolder) holder).textStdFirstName.setTextColor(Color.BLUE);
        } else {
            ((StudentViewHolder) holder).textStdFirstName.setTextColor(Color.RED);
        }
        svh.getTextStdFirstName().setText(studentData.get(position).getStdFirstName());
        svh.getTextStdLastName().setText(studentData.get(position).getStdLastName());
        svh.getTextStdNum().setText(studentData.get(position).getStdNumText());
        svh.getTextAmtPaid().setText(studentData.get(position).getAmountPaidText());
        svh.getTextAmtDue().setText(studentData.get(position).getAmountDueString());

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }



}
