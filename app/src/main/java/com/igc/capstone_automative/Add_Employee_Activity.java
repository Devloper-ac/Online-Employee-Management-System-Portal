package com.igc.capstone_automative;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Add_Employee_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView imgPic;
    TextInputEditText ename,enumber,eemail,epincode,estate, edistrict,ecity,eaddress,elandmark,eid;
    Button btndate;
    Bitmap bmp;
    String  imgStr;
    Spinner espinner;
    ProgressDialog pd;
    RequestQueue rq;
    Calendar c;
    String edatee,espinner1;
    int dd,mm,yy;
    String[] type={"Sales and Marketing Department","Store Department","Purchase Department","Maintenance Department","Process And Project Department","Production Department","Quality Assurance Department","Research and Development Department","Account Department","Tools room Department"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add__employee_activity);

        pd = new ProgressDialog(Add_Employee_Activity.this);
        rq = Volley.newRequestQueue(Add_Employee_Activity.this);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH)+1;
        yy = c.get(Calendar.YEAR);


        imgPic = findViewById(R.id.imgPic);
        ename = findViewById(R.id.ename);
        enumber = findViewById(R.id.enumber);
        eemail = findViewById(R.id.eEmail);
        epincode = findViewById(R.id.epincode);
        estate = findViewById(R.id.estate);
        edistrict = findViewById(R.id.edistrict);
        ecity = findViewById(R.id.ecity);
        eaddress = findViewById(R.id.eaddress);
        elandmark = findViewById(R.id.elandmark);
        eid = findViewById(R.id.eid);
        espinner = findViewById(R.id.espinner);
        btndate = findViewById(R.id.btndate);

        ArrayAdapter<String> adp = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,type);
        espinner.setAdapter(adp);
        espinner.setOnItemSelectedListener(this);

        espinner1 = espinner.getSelectedItem().toString();


    }

    public void openCam(View view) {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},111);
        }
        else
        {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,101);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==101 && data!=null && resultCode==RESULT_OK)
        {
            bmp = (Bitmap) data.getExtras().get("data");
            imgPic.setImageBitmap(bmp);
            Toast.makeText(Add_Employee_Activity.this, "Image Caputer", Toast.LENGTH_SHORT).show();

        }
        if(data == null)
        {
            Toast.makeText(Add_Employee_Activity.this,"Image Not Capture",Toast.LENGTH_SHORT).show();
        }



        /*if(requestCode==101 && data != null)
        {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            imgPic.setImageBitmap(bmp);
            Toast.makeText(Add_Employee_Activity.this, "Image Caputer", Toast.LENGTH_SHORT).show();
        }*/

    }

    public void datepicker(View view) {
        DatePickerDialog dpd = new DatePickerDialog(Add_Employee_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                btndate.setText(dayOfMonth+"/"+(month+1)+"/"+year);

            }
        },yy,mm - 1,dd);
        dpd.show();
    }

    public void submitdata(View view) {

        if(ename.getText().toString().isEmpty() || enumber.getText().toString().isEmpty() ||
        eemail.getText().toString().isEmpty() || epincode.getText().toString().isEmpty() ||
        estate.getText().toString().isEmpty() || edistrict.getText().toString().isEmpty() ||
        eaddress.getText().toString().isEmpty() || elandmark.getText().toString().isEmpty() ||
        eid.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
        }else if(enumber.getText().toString().isEmpty())
        {
            enumber.setError("Please Enter Number");enumber.requestFocus();
        }else if(eemail.getText().toString().isEmpty())
        {
            eemail.setError("Please Enter Email");eemail.requestFocus();
        }else if(epincode.getText().toString().isEmpty())
        {
            epincode.setError("Please Enter Pincode");epincode.requestFocus();
        }else if(estate.getText().toString().isEmpty())
        {
            estate.setError("Please Enter State");estate.requestFocus();
        }else if(edistrict.getText().toString().isEmpty())
        {
            edistrict.setError("Please Enter District");edistrict.requestFocus();
        }else if(ecity.getText().toString().isEmpty())
        {
            ecity.setError("Please Enter City");ecity.requestFocus();
        }else if(eaddress.getText().toString().isEmpty())
        {
            eaddress.setError("Please Enter Address");eaddress.requestFocus();
        }else if(elandmark.getText().toString().isEmpty())
        {
            elandmark.setError("Please Enter Landmark");elandmark.requestFocus();
        }else if(eid.getText().toString().isEmpty())
        {
            eid.setError("Please Enter EID");eid.requestFocus();
        }else if(ename.getText().toString().isEmpty())
        {
            ename.setError("Please Enter Name");ename.requestFocus();
        }
        else
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,baos);
            byte[] b = baos.toByteArray();
            imgStr = Base64.encodeToString(b,0);


            pd.setTitle("Sending Data...");
            pd.setMessage("Sending Data to server please wait...");
            pd.show();
            String url ="https://lifeshareofficial.000webhostapp.com/empdatasend.php";
            StringRequest strq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pd.dismiss();
                    Toast.makeText(Add_Employee_Activity.this, "Data Store Sucessfully..", Toast.LENGTH_SHORT).show();
                    ename.setText("");
                    enumber.setText("");
                    eemail.setText("");
                    epincode.setText("");
                    estate.setText("");
                    edistrict.setText("");
                    ecity.setText("");
                    eaddress.setText("");
                    elandmark.setText("");
                    eid.setText("");
                    btndate.setText("");
                    imgPic.setImageBitmap(null);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pd.dismiss();
                    Toast.makeText(Add_Employee_Activity.this, "Error"+error, Toast.LENGTH_SHORT).show();

                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> P = new HashMap<>();

                    P.put("Img",imgStr);
                    P.put("ename",ename.getText().toString().trim());
                    P.put("enumber",enumber.getText().toString().trim());
                    P.put("eemail",eemail.getText().toString().trim());
                    P.put("epincode",epincode.getText().toString().trim());
                    P.put("estate",estate.getText().toString().trim());
                    P.put("edistrict",edistrict.getText().toString().trim());
                    P.put("eaddress",eaddress.getText().toString().trim());
                    P.put("ecity",ecity.getText().toString().trim());
                    P.put("elandmark",elandmark.getText().toString().trim());
                    P.put("espinner1",espinner.getSelectedItem().toString());
                    P.put("datee",btndate.getText().toString());
                    P.put("eid",eid.getText().toString().trim());

                    return P;
                }
            };
            rq.add(strq);
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

/*

ProgressDialog pd;
    RequestQueue rq;
    Spinner spinner3;
    Calendar c;
    String BloodR;
    String Datee;
    int dd,mm,yy,hr,mn,mi;
    Button btncal;
    String[] bloodGrougPatient ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText BloodRname,BloodRnumber,BloodRUnit,BloodRState,BloodRCity,BloodRHosName,BloodRMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__requert_blood_activity);

        rq = Volley.newRequestQueue(Patient_RequertBlood_Activity.this);
        pd = new ProgressDialog(Patient_RequertBlood_Activity.this);

        c = Calendar.getInstance();
        dd = c.get(Calendar.DAY_OF_MONTH);
        mm = c.get(Calendar.MONTH);
        yy = c.get(Calendar.YEAR);
        hr = c.get(Calendar.HOUR_OF_DAY);
        mn = c.get(Calendar.MINUTE);
        btncal = findViewById(R.id.btncal);
        spinner3 = findViewById(R.id.spinner3);

        BloodRname = findViewById(R.id.BloodRname);
        BloodRnumber = findViewById(R.id.BloodRnumber);
        BloodRUnit = findViewById(R.id.BloodRUnit);
        BloodRState = findViewById(R.id.BloodRState);
        BloodRCity = findViewById(R.id.BloodRCity);
        BloodRHosName = findViewById(R.id.BloodRHosName);
        BloodRMessage = findViewById(R.id.BloodRMessage);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGrougPatient);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(aa);
        spinner3.setOnItemSelectedListener(this);

        BloodR = spinner3.getSelectedItem().toString();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void opencal(View view) {

        DatePickerDialog dp = new DatePickerDialog(Patient_RequertBlood_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                btncal.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                Datee = btncal.getText().toString().trim();
            }
        }, yy, mm - 1, dd);
        dp.show();

    }

    public void postRequest(View view) {
        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Patient_BloodRequest.php";
        StringRequest strBR = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response){
                pd.dismiss();
                Toast.makeText(Patient_RequertBlood_Activity.this, "Request Send Succefully..", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_RequertBlood_Activity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();


                P.put("BloodRname",BloodRname.getText().toString().trim());
                P.put(" BloodRnumber", BloodRnumber.getText().toString().trim());
                P.put("BloodR",spinner3.getSelectedItem().toString());
                P.put(" BloodRUnit", BloodRUnit.getText().toString().trim());
                P.put(" BloodRState", BloodRState.getText().toString().trim());
                P.put("BloodRCity",BloodRCity.getText().toString().trim());
                P.put("BloodRHosName",BloodRHosName.getText().toString().trim());
                P.put("Datee",Datee);
                P.put("BloodRMessage",BloodRMessage.getText().toString().trim());

                return P;
            }
        };
        rq.add(strBR);
    }
}

























ProgressDialog pd;
    RequestQueue rq;

    Spinner spinner2;
    String demo;
    String[] bloodGroug ={"O+","O-","A+","A-","B+","B-","AB+","AB-"};
    TextInputEditText patientname,patientnumber,patientemail,ppincode,pstate,pdistrict,pcity,patientaddress,patientlandmark,patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient__information_activity);

        rq = Volley.newRequestQueue(Patient_Information_Activity.this);
        pd = new ProgressDialog(Patient_Information_Activity.this);


        spinner2 = findViewById(R.id.spinner2);

        patientname = findViewById(R.id.patientname);
        patientnumber = findViewById(R.id.patientnumber);
        patientemail = findViewById(R.id.patientemail);
        ppincode = findViewById(R.id.ppincode);
        pstate = findViewById(R.id.pstate);
        pdistrict = findViewById(R.id.pdistrict);
        pcity = findViewById(R.id.pcity);
        patientaddress = findViewById(R.id.patientaddress);
        patientlandmark = findViewById(R.id.patientlandmark);
        patientID = findViewById(R.id.patientID);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodGroug);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa);
        spinner2.setOnItemSelectedListener(this);

        demo = spinner2.getSelectedItem().toString();

    }

    public void submit(View view) {

        pd.setTitle("Sending...");
        pd.setMessage("Sending Data to Server Please Wait...");
        pd.show();
        String url="https://lifeshareofficial.000webhostapp.com/Patient_Information.php";
        StringRequest strq = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Data Store Succefully..", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Patient_Information_Activity.this,Patient_Dashborad_Activity.class));
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Patient_Information_Activity.this, "Error in App", Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> P = new HashMap<>();


                P.put("demo",spinner2.getSelectedItem().toString());
                P.put("patientname",patientname.getText().toString().trim());
                P.put("patientnumber",patientnumber.getText().toString().trim());
                P.put("patientemail", patientemail.getText().toString().trim());
                P.put("ppincode",ppincode.getText().toString().trim());
                P.put("pstate",pstate.getText().toString().trim());
                P.put("pdistrict",pdistrict.getText().toString().trim());
                P.put("pcity",pcity.getText().toString().trim());
                P.put("patientaddress",patientaddress.getText().toString().trim());
                P.put("patientlandmark",patientlandmark.getText().toString().trim());
                P.put("patientID",patientID.getText().toString().trim());
                return P;
            }
        };
        rq.add(strq);

    }



 */