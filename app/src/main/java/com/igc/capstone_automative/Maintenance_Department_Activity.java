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

public class Maintenance_Department_Activity extends AppCompatActivity {

    ListView listmaintenance;
    ImageView ImageMain;
    String[] MName,MNumber,MEmail,Mdatee,Mspinner,Mpincode,Mstate,MDistrict,MCity,MAddress,MLandmark,Meid;
    RequestQueue rq;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance__department_);

        listmaintenance = findViewById(R.id.listmaintenance);

        rq = Volley.newRequestQueue(Maintenance_Department_Activity.this);
        pd = new ProgressDialog(Maintenance_Department_Activity.this);
        accessmaindata();
    }

    public void accessmaindata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/maintenancelist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    MName = new String[jsarr1.length()];
                    MNumber = new String[jsarr1.length()];
                    MEmail = new String[jsarr1.length()];
                    Mdatee = new String[jsarr1.length()];
                    Mspinner = new String[jsarr1.length()];
                    Mpincode = new String[jsarr1.length()];
                    Mstate = new String[jsarr1.length()];
                    MDistrict = new String[jsarr1.length()];
                    MCity = new String[jsarr1.length()];
                    MAddress = new String[jsarr1.length()];
                    MLandmark = new String[jsarr1.length()];
                    Meid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        MName[i] = jsobj.getString("Name");
                        MNumber[i] = jsobj.getString("Number");
                        MEmail[i] = jsobj.getString("Email");
                        Mdatee[i] = jsobj.getString("Datee");
                        Mspinner[i] = jsobj.getString("Spinner");
                        Mpincode[i] = jsobj.getString("Pincode");
                        Mstate[i] = jsobj.getString("State");
                        MDistrict[i] = jsobj.getString("District");
                        MCity[i] = jsobj.getString("City");
                        MAddress[i] = jsobj.getString("Address");
                        MLandmark[i] = jsobj.getString("Landmark");
                        Meid[i] = jsobj.getString("Eid");
                        Toast.makeText(Maintenance_Department_Activity.this, "Name" + MName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listmaintenance.setAdapter(ma);

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
                Toast.makeText(Maintenance_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return MName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.maintenancelist,null);

            ImageView im = convertView.findViewById(R.id.imageViewmain);
            TextView M_Name = convertView.findViewById(R.id.txtnamemain);
            TextView M_Number = convertView.findViewById(R.id.txtnumbermain);
            TextView M_Email = convertView.findViewById(R.id.txtemailmain);
            TextView M_Date = convertView.findViewById(R.id.txtdatemain);
            TextView M_Spinner = convertView.findViewById(R.id.txtspinnermain);
            TextView M_Pincode = convertView.findViewById(R.id.txtpincodemain);
            TextView M_State = convertView.findViewById(R.id.txtstatemain);
            TextView M_District = convertView.findViewById(R.id.txtdistrictmain);
            TextView M_City= convertView.findViewById(R.id.txtcitymain);
            TextView M_Address = convertView.findViewById(R.id.txtaddressmain);
            TextView M_Landmark = convertView.findViewById(R.id.txtlandmarkmain);
            TextView M_Eid = convertView.findViewById(R.id.txteidmain);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+Meid[position]+".jpg";
            Picasso.get().load(urll).into(im);

            M_Name.setText(MName[position]);
            M_Number.setText(MNumber[position]);
            M_Email.setText(MEmail[position]);
            M_Date.setText(Mdatee[position]);
            M_Spinner.setText(Mspinner[position]);
            M_Pincode.setText(Mpincode[position]);
            M_State.setText(Mstate[position]);
            M_District.setText(MDistrict[position]);
            M_City.setText(MCity[position]);
            M_Address.setText(MAddress[position]);
            M_Landmark.setText(MLandmark[position]);
            M_Eid.setText(Meid[position]);
            return convertView;


        }
    }
}