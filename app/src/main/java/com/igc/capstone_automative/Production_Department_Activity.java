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

public class Production_Department_Activity extends AppCompatActivity {

    ListView productionlistview;
    String[] productionName,productionNumber,productionEmail,productiondatee,productionspinner,productionpincode,productionstate,productionDistrict,productionCity,productionAddress,productionLandmark,productioneid;
    RequestQueue rq;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.production__department_activity);

        productionlistview = findViewById(R.id.productionlistview);
        rq = Volley.newRequestQueue(Production_Department_Activity.this);
        pd = new ProgressDialog(Production_Department_Activity.this);
        accessproductiondata();
    }

    public void accessproductiondata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/productionlist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    productionName = new String[jsarr1.length()];
                    productionNumber = new String[jsarr1.length()];
                    productionEmail = new String[jsarr1.length()];
                    productiondatee = new String[jsarr1.length()];
                    productionspinner = new String[jsarr1.length()];
                    productionpincode = new String[jsarr1.length()];
                    productionstate = new String[jsarr1.length()];
                    productionDistrict = new String[jsarr1.length()];
                    productionCity = new String[jsarr1.length()];
                    productionAddress = new String[jsarr1.length()];
                    productionLandmark = new String[jsarr1.length()];
                    productioneid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        productionName[i] = jsobj.getString("Name");
                        productionNumber[i] = jsobj.getString("Number");
                        productionEmail[i] = jsobj.getString("Email");
                        productiondatee[i] = jsobj.getString("Datee");
                        productionspinner[i] = jsobj.getString("Spinner");
                        productionpincode[i] = jsobj.getString("Pincode");
                        productionstate[i] = jsobj.getString("State");
                        productionDistrict[i] = jsobj.getString("District");
                        productionCity[i] = jsobj.getString("City");
                        productionAddress[i] = jsobj.getString("Address");
                        productionLandmark[i] = jsobj.getString("Landmark");
                        productioneid [i] = jsobj.getString("Eid");
                        Toast.makeText(Production_Department_Activity.this, "Name" + productionName[i], Toast.LENGTH_SHORT).show();
                    }
                   MyAdapter ma = new MyAdapter();
                    productionlistview.setAdapter(ma);

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
                Toast.makeText(Production_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return productionName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.productionlist,null);

            ImageView imgprodu = convertView.findViewById(R.id.imageViewproduction);
            TextView production_Name = convertView.findViewById(R.id.txtnameproduction);
            TextView production_Number = convertView.findViewById(R.id.txtnumberproduction);
            TextView production_Email = convertView.findViewById(R.id.txtemailproduction);
            TextView production_Date = convertView.findViewById(R.id.txtdateproduction);
            TextView production_Spinner = convertView.findViewById(R.id.txtspinnerproduction);
            TextView production_Pincode = convertView.findViewById(R.id.txtpincodeproduction);
            TextView production_State = convertView.findViewById(R.id.txtstateproduction);
            TextView production_District = convertView.findViewById(R.id.txtdistrictproduction);
            TextView production_City= convertView.findViewById(R.id.txtcityproduction);
            TextView production_Address = convertView.findViewById(R.id.txtaddressproduction);
            TextView production_Landmark = convertView.findViewById(R.id.txtlandmarkproduction);
            TextView production_Eid = convertView.findViewById(R.id.txteidproduction);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+productioneid[position]+".jpg";
            Picasso.get().load(urll).into(imgprodu);


            production_Name.setText(productionName[position]);
            production_Number.setText(productionNumber[position]);
            production_Email.setText(productionEmail[position]);
            production_Date.setText(productiondatee[position]);
            production_Spinner.setText(productionspinner[position]);
            production_Pincode.setText(productionpincode[position]);
            production_State.setText(productionstate[position]);
            production_District.setText(productionDistrict[position]);
            production_City.setText(productionCity[position]);
            production_Address.setText(productionAddress[position]);
            production_Landmark.setText(productionLandmark[position]);
            production_Eid.setText(productioneid[position]);
            return convertView;


        }
    }
}