package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class View_Employee_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view__employee_activity);
    }

    public void salesmarketing(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Sales_Marketing_Activity.class));
    }

    public void storedepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Store_Department_Activity.class));
    }

    public void purchasedepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Purchase_Department_Activity.class));
    }

    public void maintenancedepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Maintenance_Department_Activity.class));
    }

    public void processdepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Process_Department_Activity.class));
    }

    public void productiondepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Production_Department_Activity.class));
    }

    public void qualitydepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Quality_Department_Activity.class));
    }

    public void researchdepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Research_Department_Activity.class));
    }

    public void accountdepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Account_Department_Activity.class));
    }

    public void toolsdepartment(View view) {
        startActivity(new Intent(View_Employee_Activity.this,Tools_Department_Activity.class));
    }
}