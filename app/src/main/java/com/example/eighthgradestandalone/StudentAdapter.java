package com.example.eighthgradestandalone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter {

    private ArrayList<Student> studentData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;


    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView textStdFirstName;
        public TextView textStdLastName;
        public TextView textStdNum;
        public TextView textAmtPaid;
        public TextView textAmtDue;
        public Button deleteButton;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStdFirstName = itemView.findViewById(R.id.listFirstName);
            textStdLastName = itemView.findViewById(R.id.listLastName);
            textStdNum = itemView.findViewById(R.id.listStdNum);
            textAmtPaid = itemView.findViewById(R.id.listAmountPaid);
            textAmtDue = itemView.findViewById(R.id.listAmountDue);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getTextStdFirstName() {return textStdFirstName;}
        public TextView getTextStdLastName() {return textStdLastName;}
        public TextView getTextStdNum() { return textStdNum;}
        public TextView getTextAmtPaid() {return textAmtPaid;}
        public TextView getTextAmtDue() {return textAmtDue;}
        public Button getDeleteButton() {
            return deleteButton;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        StudentViewHolder svh = (StudentViewHolder) holder;

        svh.getTextStdFirstName().setText(studentData.get(position).getStdFirstName());
        svh.getTextStdLastName().setText(studentData.get(position).getStdLastName());
        svh.getTextStdNum().setText(studentData.get(position).getStdNumText());
        svh.getTextAmtPaid().setText(studentData.get(position).getAmountPaidText());
        svh.getTextAmtDue().setText(studentData.get(position).getAmountDueString());

        if (isDeleting) {
            svh.getDeleteButton().setVisibility(View.VISIBLE);
            svh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position); // replace with holder.getHolderPosition
                }
            });
        }
        else {
            svh.getDeleteButton().setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return studentData.size();
    }

    public void setDelete(boolean b){
        isDeleting = b;
    }

    private void deleteItem(int position){
        Student student = studentData.get(position);
        StudentActivityDataSource ds = new StudentActivityDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteContact(student.getStdSystemID());
            ds.close();

            if (didDelete) {
                studentData.remove(position);
                notifyDataSetChanged(); // refresh display
                Toast.makeText(parentContext, "Delete Success!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
        }
    }



}
