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

public class Account_Department_Activity extends AppCompatActivity {

    ListView accountlistview;
    String[] accoutName,accoutNumber,accoutEmail,accoutdatee,accoutspinner,accoutpincode,accoutstate,accoutDistrict,accoutCity,accoutAddress,accoutLandmark,accouteid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account__department_activity);

        accountlistview = findViewById(R.id.accountlistview);

        rq = Volley.newRequestQueue(Account_Department_Activity.this);
        pd = new ProgressDialog(Account_Department_Activity.this);
        accessaccountdata();
    }

    public void accessaccountdata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/accoutdeplist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    accoutName = new String[jsarr1.length()];
                    accoutNumber = new String[jsarr1.length()];
                    accoutEmail = new String[jsarr1.length()];
                    accoutdatee = new String[jsarr1.length()];
                    accoutspinner = new String[jsarr1.length()];
                    accoutpincode = new String[jsarr1.length()];
                    accoutstate = new String[jsarr1.length()];
                    accoutDistrict = new String[jsarr1.length()];
                    accoutCity = new String[jsarr1.length()];
                    accoutAddress = new String[jsarr1.length()];
                    accoutLandmark = new String[jsarr1.length()];
                    accouteid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        accoutName[i] = jsobj.getString("Name");
                        accoutNumber[i] = jsobj.getString("Number");
                        accoutEmail[i] = jsobj.getString("Email");
                        accoutdatee[i] = jsobj.getString("Datee");
                        accoutspinner[i] = jsobj.getString("Spinner");
                        accoutpincode[i] = jsobj.getString("Pincode");
                        accoutstate[i] = jsobj.getString("State");
                        accoutDistrict[i] = jsobj.getString("District");
                        accoutCity[i] = jsobj.getString("City");
                        accoutAddress[i] = jsobj.getString("Address");
                        accoutLandmark[i] = jsobj.getString("Landmark");
                        accouteid[i] = jsobj.getString("Eid");
                        Toast.makeText(Account_Department_Activity.this, "Name" + accoutName[i], Toast.LENGTH_SHORT).show();
                    }
                   MyAdapter ma = new MyAdapter();
                    accountlistview.setAdapter(ma);

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
                Toast.makeText(Account_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return accoutName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.accoutlist,null);

            ImageView imgacc = convertView.findViewById(R.id.imageViewaccout);
            TextView accout_Name = convertView.findViewById(R.id.txtnameaccout);
            TextView accout_Number = convertView.findViewById(R.id.txtnumberaccout);
            TextView accout_Email = convertView.findViewById(R.id.txtemailaccout);
            TextView accout_Date = convertView.findViewById(R.id.txtdateaccout);
            TextView accout_Spinner = convertView.findViewById(R.id.txtspinneraccout);
            TextView accout_Pincode = convertView.findViewById(R.id.txtpincodeaccout);
            TextView accout_State = convertView.findViewById(R.id.txtstateaccout);
            TextView accout_District = convertView.findViewById(R.id.txtdistrictaccout);
            TextView accout_City= convertView.findViewById(R.id.txtcityaccout);
            TextView accout_Address = convertView.findViewById(R.id.txtaddressaccout);
            TextView accout_Landmark = convertView.findViewById(R.id.txtlandmarkaccout);
            TextView accout_Eid = convertView.findViewById(R.id.txteidaccout);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+accoutName[position]+accouteid[position]+".jpg";
            Picasso.get().load(urll).into(imgacc);

            accout_Name.setText(accoutName[position]);
            accout_Number.setText(accoutNumber[position]);
            accout_Email.setText(accoutEmail[position]);
            accout_Date.setText(accoutdatee[position]);
            accout_Spinner.setText(accoutspinner[position]);
            accout_Pincode.setText(accoutpincode[position]);
            accout_State.setText(accoutstate[position]);
            accout_District.setText(accoutDistrict[position]);
            accout_City.setText(accoutCity[position]);
            accout_Address.setText(accoutAddress[position]);
            accout_Landmark.setText(accoutLandmark[position]);
            accout_Eid.setText(accouteid[position]);
            return convertView;


        }
    }
}