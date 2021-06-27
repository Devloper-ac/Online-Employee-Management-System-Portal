package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
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

public class Quality_Department_Activity extends AppCompatActivity {

    ListView listQuality;
    ImageView imgquality;
    Bitmap bmp1;
    String[] QName,QNumber,QEmail,Qdatee,Qspinner,Qpincode,Qstate,QDistrict,QCity,QAddress,QLandmark,Qeid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quality__department_activity);

        listQuality = findViewById(R.id.listQuality);

        imgquality  = findViewById(R.id.imageView2);

        rq = Volley.newRequestQueue(Quality_Department_Activity.this);
        pd = new ProgressDialog(Quality_Department_Activity.this);

        accessquality();
    }

    public void accessquality()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/accessquality.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    QName = new String[jsarr1.length()];
                    QNumber = new String[jsarr1.length()];
                    QEmail = new String[jsarr1.length()];
                    Qdatee = new String[jsarr1.length()];
                    Qspinner = new String[jsarr1.length()];
                    Qpincode = new String[jsarr1.length()];
                    Qstate = new String[jsarr1.length()];
                    QDistrict = new String[jsarr1.length()];
                    QCity = new String[jsarr1.length()];
                    QAddress = new String[jsarr1.length()];
                    QLandmark = new String[jsarr1.length()];
                    Qeid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        QName[i] = jsobj.getString("Name");
                        QNumber[i] = jsobj.getString("Number");
                        QEmail[i] = jsobj.getString("Email");
                        Qdatee[i] = jsobj.getString("Datee");
                        Qspinner[i] = jsobj.getString("Spinner");
                        Qpincode[i] = jsobj.getString("Pincode");
                        Qstate[i] = jsobj.getString("State");
                        QDistrict[i] = jsobj.getString("District");
                        QCity[i] = jsobj.getString("City");
                        QAddress[i] = jsobj.getString("Address");
                        QLandmark[i] = jsobj.getString("Landmark");
                        Qeid[i] = jsobj.getString("Eid");
                        Toast.makeText(Quality_Department_Activity.this, "Name" + QName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listQuality.setAdapter(ma);

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
                Toast.makeText(Quality_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return QName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.qualitylist,null);

            ImageView img = convertView.findViewById(R.id.imageView2);
            TextView Q_Name = convertView.findViewById(R.id.txtname);
            TextView Q_Number = convertView.findViewById(R.id.txtnumber);
            TextView Q_Email = convertView.findViewById(R.id.txtemail);
            TextView Q_Date = convertView.findViewById(R.id.txtdate);
            TextView Q_Spinner = convertView.findViewById(R.id.txtspinner);
            TextView Q_Pincode = convertView.findViewById(R.id.txtpincode);
            TextView Q_State = convertView.findViewById(R.id.txtstate);
            TextView Q_District = convertView.findViewById(R.id.txtdistrict);
            TextView Q_City= convertView.findViewById(R.id.txtcity);
            TextView Q_Address = convertView.findViewById(R.id.txtaddress);
            TextView Q_Landmark = convertView.findViewById(R.id.txtlandmark);
            TextView Q_Eid = convertView.findViewById(R.id.txteid);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+Qeid[position]+".jpg";
            Picasso.get().load(urll).into(img);


            Q_Name.setText(QName[position]);
            Q_Number.setText(QNumber[position]);
            Q_Email.setText(QEmail[position]);
            Q_Date.setText(Qdatee[position]);
            Q_Spinner.setText(Qspinner[position]);
            Q_Pincode.setText(Qpincode[position]);
            Q_State.setText(Qstate[position]);
            Q_District.setText(QDistrict[position]);
            Q_City.setText(QCity[position]);
            Q_Address.setText(QAddress[position]);
            Q_Landmark.setText(QLandmark[position]);
            Q_Eid.setText(Qeid[position]);
            return convertView;


        }
    }
}

/*

ListView OANListview;
    String[] OANName,OANBloodGroup,OANMobNo,OANemail,OANPincode,OANState,OANDistrict,OANCity,OANAddress,OANLandmark;
    RequestQueue rq;
    ProgressDialog pd;
    SwipeRefreshLayout refAN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner__a_n_activity);

        OANListview = findViewById(R.id.OANListview);
        rq = Volley.newRequestQueue(Owner_AN_Activity.this);
        pd = new ProgressDialog(Owner_AN_Activity.this);

        refAN = findViewById(R.id.refAN);
        refAN.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                AccessDataOAN();
                refAN.setRefreshing(false);
            }
        });

        AccessDataOAN();
    }

    public void AccessDataOAN()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/AN_OwnerAccess.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    OANName = new String[jsarr1.length()];
                    OANBloodGroup = new String[jsarr1.length()];
                    OANMobNo = new String[jsarr1.length()];
                    OANemail = new String[jsarr1.length()];
                    OANPincode = new String[jsarr1.length()];
                    OANState = new String[jsarr1.length()];
                    OANDistrict = new String[jsarr1.length()];
                    OANCity = new String[jsarr1.length()];
                    OANAddress = new String[jsarr1.length()];
                    OANLandmark = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        OANName[i] = jsobj.getString("Name");
                        OANBloodGroup[i] = jsobj.getString("BloodGroup");
                        OANMobNo[i] = jsobj.getString("MobileNo");
                        OANemail[i] = jsobj.getString("Email");
                        OANPincode[i] = jsobj.getString("Pincode");
                        OANState[i] = jsobj.getString("State");
                        OANDistrict[i] = jsobj.getString("District");
                        OANCity[i] = jsobj.getString("City");
                        OANAddress[i] = jsobj.getString("Address");
                        OANLandmark[i] = jsobj.getString("Landmark");
                        Toast.makeText(Owner_AN_Activity.this, "RollNo" + OANName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    OANListview.setAdapter(ma);

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
                Toast.makeText(Owner_AN_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return OANName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.oanlist_activity,null);

            TextView OAN_Name = convertView.findViewById(R.id.OANName);
            TextView OAN_Blood = convertView.findViewById(R.id.OANBloodGroup);
            TextView OAN_Mobno = convertView.findViewById(R.id.OANMobNo);
            TextView OAN_email = convertView.findViewById(R.id.OANemail);
            TextView OAN_Pincode = convertView.findViewById(R.id.OANPincode);
            TextView OAN_State = convertView.findViewById(R.id.OANState);
            TextView OAN_District = convertView.findViewById(R.id.OANDistrict);
            TextView OAN_City = convertView.findViewById(R.id.OANCity);
            TextView OAN_Address = convertView.findViewById(R.id.OANAddress);
            TextView OAN_Landmark = convertView.findViewById(R.id.OANLandmark);




            OAN_Name.setText(OANName[position]);
            OAN_Blood.setText(OANBloodGroup[position]);
            OAN_Mobno.setText(OANMobNo[position]);
            OAN_email.setText(OANemail[position]);
            OAN_Pincode.setText(OANPincode[position]);
            OAN_State.setText(OANState[position]);
            OAN_District.setText(OANDistrict[position]);
            OAN_City.setText(OANCity[position]);
            OAN_Address.setText(OANAddress[position]);
            OAN_Landmark.setText(OANLandmark[position]);
            return convertView;
        }
    }
}
 */