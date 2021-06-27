package com.igc.capstone_automative;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class Store_Department_Activity extends AppCompatActivity {

    ListView liststore;
    String[] SName,SNumber,SEmail,Sdatee,Sspinner,Spincode,Sstate,SDistrict,SCity,SAddress,SLandmark,Seid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store__department_activity);

        liststore = findViewById(R.id.liststore);
        rq = Volley.newRequestQueue(Store_Department_Activity.this);
        pd = new ProgressDialog(Store_Department_Activity.this);
        accessstoredata();
    }

    public void accessstoredata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/storelist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    SName = new String[jsarr1.length()];
                    SNumber = new String[jsarr1.length()];
                    SEmail = new String[jsarr1.length()];
                    Sdatee = new String[jsarr1.length()];
                    Sspinner = new String[jsarr1.length()];
                    Spincode = new String[jsarr1.length()];
                    Sstate = new String[jsarr1.length()];
                    SDistrict = new String[jsarr1.length()];
                    SCity = new String[jsarr1.length()];
                    SAddress = new String[jsarr1.length()];
                    SLandmark = new String[jsarr1.length()];
                    Seid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        SName[i] = jsobj.getString("Name");
                        SNumber[i] = jsobj.getString("Number");
                        SEmail[i] = jsobj.getString("Email");
                        Sdatee[i] = jsobj.getString("Datee");
                        Sspinner[i] = jsobj.getString("Spinner");
                        Spincode[i] = jsobj.getString("Pincode");
                        Sstate[i] = jsobj.getString("State");
                        SDistrict[i] = jsobj.getString("District");
                        SCity[i] = jsobj.getString("City");
                        SAddress[i] = jsobj.getString("Address");
                        SLandmark[i] = jsobj.getString("Landmark");
                        Seid[i] = jsobj.getString("Eid");
                        Toast.makeText(Store_Department_Activity.this, "Name" + SName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    liststore.setAdapter(ma);

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
                Toast.makeText(Store_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return SName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.storedepartmentlist,null);

            ImageView imgstore = convertView.findViewById(R.id.imageViewstore);

            TextView S_Name = convertView.findViewById(R.id.txtnamestore);
            TextView S_Number = convertView.findViewById(R.id.txtnumberstore);
            TextView S_Email = convertView.findViewById(R.id.txtemailstore);
            TextView S_Date = convertView.findViewById(R.id.txtdatestore);
            TextView S_Spinner = convertView.findViewById(R.id.txtspinnerstore);
            TextView S_Pincode = convertView.findViewById(R.id.txtpincodestore);
            TextView S_State = convertView.findViewById(R.id.txtstatestore);
            TextView S_District = convertView.findViewById(R.id.txtdistrictstore);
            TextView S_City= convertView.findViewById(R.id.txtcitystore);
            TextView S_Address = convertView.findViewById(R.id.txtaddresstore);
            TextView S_Landmark = convertView.findViewById(R.id.txtlandmarkstore);
            TextView S_Eid = convertView.findViewById(R.id.txteidstore);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+Seid[position]+".jpg";
            Picasso.get().load(urll).into(imgstore);

            S_Name.setText(SName[position]);
            S_Number.setText(SNumber[position]);
            S_Email.setText(SEmail[position]);
            S_Date.setText(Sdatee[position]);
            S_Spinner.setText(Sspinner[position]);
            S_Pincode.setText(Spincode[position]);
            S_State.setText(Sstate[position]);
            S_District.setText(SDistrict[position]);
            S_City.setText(SCity[position]);
            S_Address.setText(SAddress[position]);
            S_Landmark.setText(SLandmark[position]);
            S_Eid.setText(Seid[position]);
            return convertView;


        }
    }
}