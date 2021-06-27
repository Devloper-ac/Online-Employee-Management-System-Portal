package com.igc.capstone_automative;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Update_Delete_Employees extends AppCompatActivity {

    RequestQueue rq;
    ProgressDialog pd;
    Calendar c;
    int dd,mm,yy;
    ImageView accessEimg;
    TextInputEditText txtsearchupdate;
    LinearLayout lytupdate,lytlist;
    Button btnupdatedate;
    String Datee,uspinner2;
    Spinner updatespinnerdata;

    TextInputEditText txtupdatename,txtupdatenumber,txtupdateemail,txtupdatepincode,txtupdatestate,txtupdatedistrict,txtupdatecity,txtupdateaddress,txtupdatelandmark,txtupdateeid;

    //String[] updateName,updateNumber,updateEmail,updatedatee,updatespinner,updatepincode,updatestate,updateDistrict,updateCity,updateAddress,updateLandmark,updateeid;
    String[] type1={"Sales and Marketing Department","Store Department","Purchase Department","Maintenance Department","Process And Project Department","Production Department","Quality Assurance Department","Research and Development Department","Account Department","Tools room Department"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update__delete__employees_activity);


        rq = Volley.newRequestQueue(Update_Delete_Employees.this);
        pd = new ProgressDialog(Update_Delete_Employees.this);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);

        txtupdatename = findViewById(R.id.txtupdatename);
        txtupdatenumber = findViewById(R.id.txtupdatenumber);
        txtupdateemail = findViewById(R.id.txtupdateemail);
        txtupdatepincode = findViewById(R.id.txtupdatepincode);
        txtupdatestate = findViewById(R.id.txtupdatestate);
        txtupdatedistrict = findViewById(R.id.txtupdatedistrict);
        txtupdatecity = findViewById(R.id.txtupdatecity);
        txtupdateaddress = findViewById(R.id.txtupdateaddress);
        txtupdatelandmark = findViewById(R.id.txtupdatelandmark);
        btnupdatedate = findViewById(R.id.btnupdatedate);
        updatespinnerdata = findViewById(R.id.updatespinnerdata);
        txtupdateeid = findViewById(R.id.txtupdateeid);
        lytupdate = findViewById(R.id.lytupdate);
        lytlist = findViewById(R.id.lytdatalist);
        txtsearchupdate = findViewById(R.id.txtsearchupdate);

        lytlist.setVisibility(View.GONE);

        ArrayAdapter<String> adp = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,type1);
        updatespinnerdata.setAdapter(adp);

        uspinner2 = updatespinnerdata.getSelectedItem().toString();

        //updatedata();
        
    }


    public void opencal(View view) {

        DatePickerDialog dp = new DatePickerDialog(Update_Delete_Employees.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                btnupdatedate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                Datee = btnupdatedate.getText().toString().trim();
            }
        }, yy, mm - 1, dd);
        dp.show();

    }

    public void updatedata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/accessempdata.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Update_Delete_Employees.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    public void searchupdate(View view) {


        if(txtsearchupdate.getText().toString().isEmpty())
        {
            txtsearchupdate.setError("Please Enter ID");txtsearchupdate.requestFocus();
        }
        else
        {
            //lytlist.setVisibility(View.VISIBLE);
            //lytupdate.setVisibility(View.GONE);
            pd.setTitle("Searching Data...");
            pd.setMessage("Searching Data Please Be waited..");
            pd.show();

            String url1 ="https://lifeshareofficial.000webhostapp.com/accessempdata.php";
            String url ="https://lifeshareofficial.000webhostapp.com/accessempdata.php?txtkey="+txtsearchupdate.getText().toString();
            JsonObjectRequest jsrq = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response)
                {


                    pd.dismiss();
                    try {
                        JSONArray jsarr1 = response.getJSONArray("Result");
                        //Toast.makeText(Update_Delete_Employees.this, ""+jsarr1.length(), Toast.LENGTH_SHORT).show();

                        if(jsarr1.length() != 0)
                        {
                            Toast.makeText(Update_Delete_Employees.this, "Data Found", Toast.LENGTH_SHORT).show();
                            lytupdate.setVisibility(View.GONE);
                             lytlist.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            Toast.makeText(Update_Delete_Employees.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            lytlist.setVisibility(View.GONE);
                            lytupdate.setVisibility(View.VISIBLE);
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


                            txtupdatename.setText(n);
                            txtupdatenumber.setText(num);
                            txtupdateemail.setText(email);
                            btnupdatedate.setText(datee);
                            txtupdatepincode.setText(pin);
                            txtupdatestate.setText(state);
                            txtupdatedistrict.setText(dist);
                            txtupdatecity.setText(cityy);
                            txtupdateaddress.setText(addre);
                            txtupdatelandmark.setText(land);
                            txtupdateeid.setText(eeid);


                            Toast.makeText(Update_Delete_Employees.this, "Name"+n, Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(Update_Delete_Employees.this, "Error:- "+error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
            rq.add(jsrq);
        }


    }

    public void updateemployeedata(View view) {

        pd.setTitle("Searching Data...");
        pd.setMessage("Searching Data Please Be waited..");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/updateEmpdata.php";
        StringRequest str2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Update_Delete_Employees.this, "Data Updated Successfully..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Update_Delete_Employees.this,DashBoard_Activity.class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Update_Delete_Employees.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> M = new HashMap<>();
                M.put("uname",txtupdatename.getText().toString());
                M.put("unumber",txtupdatenumber.getText().toString());
                M.put("uemail",txtupdateemail.getText().toString());
                M.put("udate",btnupdatedate.getText().toString());
                M.put("espinner1",updatespinnerdata.getSelectedItem().toString());
                M.put("upincode",txtupdatepincode.getText().toString());
                M.put("ustate",txtupdatestate.getText().toString());
                M.put("udistrict",txtupdatedistrict.getText().toString());
                M.put("ucity",txtupdatecity.getText().toString());
                M.put("uaddress",txtupdateaddress.getText().toString());
                M.put("ulandmark",txtupdatelandmark.getText().toString());
                M.put("ueid",txtupdateeid.getText().toString());


                return M;
            }
        };
        rq.add(str2);
    }



}


