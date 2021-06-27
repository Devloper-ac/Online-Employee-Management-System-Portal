package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Process_Department_Activity extends AppCompatActivity {

    ListView processlist;
    String[] processName,processNumber,processEmail,processdatee,processspinner,processpincode,processstate,processDistrict,processCity,processAddress,processLandmark,processeid;
    RequestQueue rq;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process__department_activity);

        processlist = findViewById(R.id.processlist);

        rq = Volley.newRequestQueue(Process_Department_Activity.this);
        pd = new ProgressDialog(Process_Department_Activity.this);
        accessprocessdata();
    }

    public void accessprocessdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/processdeplist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    processName = new String[jsarr1.length()];
                    processNumber = new String[jsarr1.length()];
                    processEmail = new String[jsarr1.length()];
                    processdatee = new String[jsarr1.length()];
                    processspinner = new String[jsarr1.length()];
                    processpincode = new String[jsarr1.length()];
                    processstate = new String[jsarr1.length()];
                    processDistrict = new String[jsarr1.length()];
                    processCity = new String[jsarr1.length()];
                    processAddress = new String[jsarr1.length()];
                    processLandmark = new String[jsarr1.length()];
                    processeid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        processName[i] = jsobj.getString("Name");
                        processNumber[i] = jsobj.getString("Number");
                        processEmail[i] = jsobj.getString("Email");
                        processdatee[i] = jsobj.getString("Datee");
                        processspinner[i] = jsobj.getString("Spinner");
                        processpincode[i] = jsobj.getString("Pincode");
                        processstate[i] = jsobj.getString("State");
                        processDistrict[i] = jsobj.getString("District");
                        processCity[i] = jsobj.getString("City");
                        processAddress[i] = jsobj.getString("Address");
                        processLandmark[i] = jsobj.getString("Landmark");
                        processeid [i] = jsobj.getString("Eid");
                        Toast.makeText(Process_Department_Activity.this, "Name" + processName[i], Toast.LENGTH_SHORT).show();
                    }
                   MyAdapter ma = new MyAdapter();
                    processlist.setAdapter(ma);

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(Process_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return processName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.processlist,null);

            ImageView imgProces = convertView.findViewById(R.id.imageViewprocess);
            TextView process_Name = convertView.findViewById(R.id.txtnameprocess);
            TextView process_Number = convertView.findViewById(R.id.txtnumberprocess);
            TextView process_Email = convertView.findViewById(R.id.txtemailprocess);
            TextView process_Date = convertView.findViewById(R.id.txtdateprocess);
            TextView process_Spinner = convertView.findViewById(R.id.txtspinnerprocess);
            TextView process_Pincode = convertView.findViewById(R.id.txtpincodeprocess);
            TextView process_State = convertView.findViewById(R.id.txtstateprocess);
            TextView process_District = convertView.findViewById(R.id.txtdistrictprocess);
            TextView process_City= convertView.findViewById(R.id.txtcityprocess);
            TextView process_Address = convertView.findViewById(R.id.txtaddressprocess);
            TextView process_Landmark = convertView.findViewById(R.id.txtlandmarkprocess);
            TextView process_Eid = convertView.findViewById(R.id.txteidprocess);

            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+processeid[position]+".jpg";
            Picasso.get().load(urll).into(imgProces);



            process_Name.setText(processName[position]);
            process_Number.setText(processNumber[position]);
            process_Email.setText(processEmail[position]);
            process_Date.setText(processdatee[position]);
            process_Spinner.setText(processspinner[position]);
            process_Pincode.setText(processpincode[position]);
            process_State.setText(processstate[position]);
            process_District.setText(processDistrict[position]);
            process_City.setText(processCity[position]);
            process_Address.setText(processAddress[position]);
            process_Landmark.setText(processLandmark[position]);
            process_Eid.setText(processeid[position]);
            return convertView;


        }
    }
}