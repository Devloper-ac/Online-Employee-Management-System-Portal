package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class DashBoard_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dash_board_activity);
        Toast.makeText(this, "Welcome Owner", Toast.LENGTH_SHORT).show();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ac, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout1:
                startActivity(new Intent(DashBoard_Activity.this,Demo.class));
                File deletePrefFile = new File("/data/data/com.igc.capstone_automative/shared_prefs/File.xml");
                deletePrefFile.delete();
                finish();
                return true;
     

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void addemployees(View view) {
        startActivity(new Intent(DashBoard_Activity.this,Add_Employee_Activity.class));
    }

    public void viewemployees(View view) {
        startActivity(new Intent(DashBoard_Activity.this,View_Employee_Activity.class));
    }

    public void updatedeleteemployee(View view) {
        startActivity(new Intent(DashBoard_Activity.this,Update_Delete_Employees.class));
    }

    public void deleteemp(View view) {
        startActivity(new Intent(DashBoard_Activity.this,Delete_Employee_Activity.class));
    }
}