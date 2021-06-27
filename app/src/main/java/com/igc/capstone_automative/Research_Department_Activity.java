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

public class Research_Department_Activity extends AppCompatActivity {

    ListView listresarch;
    String[] REName,RENumber,REEmail,REdatee,REspinner,REpincode,REstate,REDistrict,RECity,REAddress,RELandmark,REeid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.research__department_activity);

        listresarch = findViewById(R.id.listresarch);
        rq = Volley.newRequestQueue(Research_Department_Activity.this);
        pd = new ProgressDialog(Research_Department_Activity.this);
        accessdataresearch();
    }

    public void accessdataresearch()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/researchdeplist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    REName = new String[jsarr1.length()];
                    RENumber = new String[jsarr1.length()];
                    REEmail = new String[jsarr1.length()];
                    REdatee = new String[jsarr1.length()];
                    REspinner = new String[jsarr1.length()];
                    REpincode = new String[jsarr1.length()];
                    REstate = new String[jsarr1.length()];
                    REDistrict = new String[jsarr1.length()];
                    RECity = new String[jsarr1.length()];
                    REAddress = new String[jsarr1.length()];
                    RELandmark = new String[jsarr1.length()];
                    REeid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        REName[i] = jsobj.getString("Name");
                        RENumber[i] = jsobj.getString("Number");
                        REEmail[i] = jsobj.getString("Email");
                        REdatee[i] = jsobj.getString("Datee");
                        REspinner[i] = jsobj.getString("Spinner");
                        REpincode[i] = jsobj.getString("Pincode");
                        REstate[i] = jsobj.getString("State");
                        REDistrict[i] = jsobj.getString("District");
                        RECity[i] = jsobj.getString("City");
                        REAddress[i] = jsobj.getString("Address");
                        RELandmark[i] = jsobj.getString("Landmark");
                        REeid[i] = jsobj.getString("Eid");
                        Toast.makeText(Research_Department_Activity.this, "Name" + REName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listresarch.setAdapter(ma);

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
                Toast.makeText(Research_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return REName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.researchlist,null);

            ImageView imgRes = convertView.findViewById(R.id.imageViewre);

            TextView RE_Name = convertView.findViewById(R.id.txtnamere);
            TextView RE_Number = convertView.findViewById(R.id.txtnumberstore);
            TextView RE_Email = convertView.findViewById(R.id.txtemailstore);
            TextView RE_Date = convertView.findViewById(R.id.txtdatestore);
            TextView RE_Spinner = convertView.findViewById(R.id.txtspinnerstore);
            TextView RE_Pincode = convertView.findViewById(R.id.txtpincodestore);
            TextView RE_State = convertView.findViewById(R.id.txtstatestore);
            TextView RE_District = convertView.findViewById(R.id.txtdistrictstore);
            TextView RE_City= convertView.findViewById(R.id.txtcitystore);
            TextView RE_Address = convertView.findViewById(R.id.txtaddresstore);
            TextView RE_Landmark = convertView.findViewById(R.id.txtlandmarkstore);
            TextView RE_Eid = convertView.findViewById(R.id.txteidstore);

            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+REeid[position]+".jpg";
            Picasso.get().load(urll).into(imgRes);


            RE_Name.setText(REName[position]);
            RE_Number.setText(RENumber[position]);
            RE_Email.setText(REEmail[position]);
            RE_Date.setText(REdatee[position]);
            RE_Spinner.setText(REspinner[position]);
            RE_Pincode.setText(REpincode[position]);
            RE_State.setText(REstate[position]);
            RE_District.setText(REDistrict[position]);
            RE_City.setText(RECity[position]);
            RE_Address.setText(REAddress[position]);
            RE_Landmark.setText(RELandmark[position]);
            RE_Eid.setText(REeid[position]);
            return convertView;


        }
    }
}