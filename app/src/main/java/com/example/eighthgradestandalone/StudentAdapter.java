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
        public TextView textCostFP;
        public TextView textCostSF;
        public TextView textAmtPaid;
        public TextView textAmtDue;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStdFirstName = itemView.findViewById(R.id.listFirstName);
            textStdLastName = itemView.findViewById(R.id.listLastName);
            textStdNum = itemView.findViewById(R.id.listSTDID);
            textCostFP = itemView.findViewById(R.id.listCostFP);
            textCostSF = itemView.findViewById(R.id.listCostSF);
            textAmtPaid = itemView.findViewById(R.id.listAmtPaid);
            textAmtDue = itemView.findViewById(R.id.listAmtDue);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getTextStdFirstName() {return textStdFirstName;}
        public TextView getTextStdLastName() {return textStdLastName;}
        public TextView getTextStdNum() {return textStdNum;}
        public TextView getTextCostFP() {return textCostFP;}
        public TextView getTextCostSF() {return textCostSF;}
        public TextView getTextAmtDue() {return textAmtDue;}
        public TextView getTextAmtPaid() {return textAmtDue;}

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
        svh.getTextStdNum().setText(studentData.get(position).getStdNum());
        svh.getTextCostSF().setText(studentData.get(position).getCostSF());
        svh.getTextCostFP().setText(studentData.get(position).getCostFP());
        svh.getTextAmtDue().setText(studentData.get(position).getAmountDue());
        svh.getTextAmtPaid().setText(studentData.get(position).getAmountPaid());

    }

    @Override
    public int getItemCount() {
        return studentData.size();
    }



}
