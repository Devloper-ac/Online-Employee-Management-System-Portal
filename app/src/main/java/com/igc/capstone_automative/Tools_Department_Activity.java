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

public class Tools_Department_Activity extends AppCompatActivity {

    ListView listtools;
    String[] TName,TNumber,TEmail,Tdatee,Tspinner,Tpincode,Tstate,TDistrict,TCity,TAddress,TLandmark,Teid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tools__department_activity);

        listtools = findViewById(R.id.listtools);

        rq = Volley.newRequestQueue(Tools_Department_Activity.this);
        pd = new ProgressDialog(Tools_Department_Activity.this);
        accesstooldata();
    }

    public void accesstooldata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/toolsdeplist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    TName = new String[jsarr1.length()];
                    TNumber = new String[jsarr1.length()];
                    TEmail = new String[jsarr1.length()];
                    Tdatee = new String[jsarr1.length()];
                    Tspinner = new String[jsarr1.length()];
                    Tpincode = new String[jsarr1.length()];
                    Tstate = new String[jsarr1.length()];
                    TDistrict = new String[jsarr1.length()];
                    TCity = new String[jsarr1.length()];
                    TAddress = new String[jsarr1.length()];
                    TLandmark = new String[jsarr1.length()];
                    Teid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        TName[i] = jsobj.getString("Name");
                        TNumber[i] = jsobj.getString("Number");
                        TEmail[i] = jsobj.getString("Email");
                        Tdatee[i] = jsobj.getString("Datee");
                        Tspinner[i] = jsobj.getString("Spinner");
                        Tpincode[i] = jsobj.getString("Pincode");
                        Tstate[i] = jsobj.getString("State");
                        TDistrict[i] = jsobj.getString("District");
                        TCity[i] = jsobj.getString("City");
                        TAddress[i] = jsobj.getString("Address");
                        TLandmark[i] = jsobj.getString("Landmark");
                        Teid[i] = jsobj.getString("Eid");
                        Toast.makeText(Tools_Department_Activity.this, "Name" + TName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listtools.setAdapter(ma);

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
                Toast.makeText(Tools_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return TName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.toolsdepartmentlist,null);

            ImageView imgtool = convertView.findViewById(R.id.imageViewtools);
            TextView T_Name = convertView.findViewById(R.id.txtnametool);
            TextView T_Number = convertView.findViewById(R.id.txtnumbertool);
            TextView T_Email = convertView.findViewById(R.id.txtemailtool);
            TextView T_Date = convertView.findViewById(R.id.txtdatetool);
            TextView T_Spinner = convertView.findViewById(R.id.txtspinnertool);
            TextView T_Pincode = convertView.findViewById(R.id.txtpincodetool);
            TextView T_State = convertView.findViewById(R.id.txtstatetool);
            TextView T_District = convertView.findViewById(R.id.txtdistricttool);
            TextView T_City= convertView.findViewById(R.id.txtcitytool);
            TextView T_Address = convertView.findViewById(R.id.txtaddresstool);
            TextView T_Landmark = convertView.findViewById(R.id.txtlandmarktool);
            TextView T_Eid = convertView.findViewById(R.id.txteidtool);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+Teid[position]+".jpg";
            Picasso.get().load(urll).into(imgtool);

            T_Name.setText(TName[position]);
            T_Number.setText(TNumber[position]);
            T_Email.setText(TEmail[position]);
            T_Date.setText(Tdatee[position]);
            T_Spinner.setText(Tspinner[position]);
            T_Pincode.setText(Tpincode[position]);
            T_State.setText(Tstate[position]);
            T_District.setText(TDistrict[position]);
            T_City.setText(TCity[position]);
            T_Address.setText(TAddress[position]);
            T_Landmark.setText(TLandmark[position]);
            T_Eid.setText(Teid[position]);
            return convertView;


        }
    }
}