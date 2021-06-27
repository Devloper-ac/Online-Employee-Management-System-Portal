package com.igc.capstone_automative;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Delete_Employee_Activity extends AppCompatActivity {

    RequestQueue rq;
    ProgressDialog pd;
    Calendar c;
    int dd,mm,yy;
    TextInputEditText txtdeleteEmp;
    LinearLayout lytsearchdel,lytdelEmp;
    Button btndeldate;
    String Datee;
    TextInputEditText txtdeleteename,txtdeletenumber,txtdeleteemail,txtdeletepincode,txtdeletestate,
            txtdeletedistrict,txtdeletecity,txtdeleteaddress,txtdeletelandmark,txtdeleteeid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete__employee_activity);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);

        rq = Volley.newRequestQueue(Delete_Employee_Activity.this);
        pd = new ProgressDialog(Delete_Employee_Activity.this);



        btndeldate = findViewById(R.id.btndeldate);
        lytsearchdel = findViewById(R.id.lytsearchdel);
        lytdelEmp = findViewById(R.id.lytdelEmp);

        txtdeleteEmp = findViewById(R.id.txtdeleteEmp);
        txtdeleteename = findViewById(R.id.txtdeleteename);
        txtdeletenumber = findViewById(R.id.txtdeletenumber);
        txtdeleteemail = findViewById(R.id.txtdeleteemail);
        txtdeletepincode = findViewById(R.id.txtdeletepincode);
        txtdeletestate = findViewById(R.id.txtdeletestate);
        txtdeletedistrict = findViewById(R.id.txtdeletedistrict);
        txtdeletecity = findViewById(R.id.txtdeletecity);
        txtdeleteaddress = findViewById(R.id.txtdeleteaddress);
        txtdeletelandmark = findViewById(R.id.txtdeletelandmark);
        txtdeleteeid = findViewById(R.id.txtdeleteeid);

        lytdelEmp.setVisibility(View.GONE);
    }

    public void deleteemployeedata(View view) {
        pd.setTitle("Searching Data...");
        pd.setMessage("Searching Data Please Be waited..");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/deleteEmployee.php?deleteemp="+txtdeleteeid.getText().toString();
        StringRequest strq2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Delete_Employee_Activity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Delete_Employee_Activity.this,DashBoard_Activity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Delete_Employee_Activity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();

                P.put("txtdeleteeid",txtdeleteeid.getText().toString());

                return P;
            }
        };
        rq.add(strq2);

    }

    public void searchdeleteemp(View view) {

        if(txtdeleteEmp.getText().toString().isEmpty())
        {
            txtdeleteEmp.setError("Please Enter ID");txtdeleteEmp.requestFocus();
        }
        else
        {

            pd.setTitle("Searching Data...");
            pd.setMessage("Searching Data Please Be waited..");
            pd.show();

            String url1 ="https://lifeshareofficial.000webhostapp.com/accessempdata.php";
            String url ="https://lifeshareofficial.000webhostapp.com/accessempdata.php?txtkey="+txtdeleteEmp.getText().toString();
            JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {


                    pd.dismiss();
                    try {
                        JSONArray jsarr1 = response.getJSONArray("Result");
                        //Toast.makeText(Delete_Employee_Activity.this, ""+jsarr1.length(), Toast.LENGTH_SHORT).show();

                        if(jsarr1.length() != 0)
                        {
                            Toast.makeText(Delete_Employee_Activity.this, "Data Found", Toast.LENGTH_SHORT).show();
                            lytsearchdel.setVisibility(View.GONE);
                            lytdelEmp.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Toast.makeText(Delete_Employee_Activity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            lytdelEmp.setVisibility(View.GONE);
                            lytsearchdel.setVisibility(View.VISIBLE);
                        }


                        for (int i = 0; i < jsarr1.length(); i++)
                        {

                            JSONObject jsobj = jsarr1.getJSONObject(i);

                            String n = (jsobj.getString("Name"));
                            String num = (jsobj.getString("Number"));
                            String email = (jsobj.getString("Email"));
                            String datee = (jsobj.getString("Datee"));
                            String spin = (jsobj.getString("Spinner"));
                            String pin = (jsobj.getString("Pincode"));
                            String state = (jsobj.getString("State"));
                            String dist = (jsobj.getString("District"));
                            String cityy = (jsobj.getString("City"));
                            String addre = (jsobj.getString("Address"));
                            String land = (jsobj.getString("Landmark"));
                            String eeid = (jsobj.getString("Eid"));

                            txtdeleteename.setText(n);
                            txtdeletenumber.setText(num);
                            txtdeleteemail.setText(email);
                            btndeldate.setText(datee);
                            txtdeletepincode.setText(pin);
                            txtdeletestate.setText(state);
                            txtdeletedistrict.setText(dist);
                            txtdeletecity.setText(cityy);
                            txtdeleteaddress.setText(addre);
                            txtdeletelandmark.setText(land);
                            txtdeleteeid.setText(eeid);

                            Toast.makeText(Delete_Employee_Activity.this, "Name"+n, Toast.LENGTH_SHORT).show();

                        }

                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    pd.dismiss();
                    Toast.makeText(Delete_Employee_Activity.this, "Error:- "+error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jsrq);
        }
    }

    public void opencalender(View view) {

        /*DatePickerDialog dp = new DatePickerDialog(Delete_Employee_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                btndeldate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

            }
        }, yy, mm - 1, dd);
        dp.show();*/

    }
}