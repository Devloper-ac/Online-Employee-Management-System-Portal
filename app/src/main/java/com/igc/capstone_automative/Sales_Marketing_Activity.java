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

public class Sales_Marketing_Activity extends AppCompatActivity {

    ListView listsales;
    String[] SaleName,SaleNumber,SaleEmail,Saledatee,Salespinner,Salepincode,Salestate,SaleDistrict,SaleCity,SaleAddress,SaleLandmark,Saleeid;
    RequestQueue rq;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales__marketing_activity);

        listsales = findViewById(R.id.listsales);
        rq = Volley.newRequestQueue(Sales_Marketing_Activity.this);
        pd = new ProgressDialog(Sales_Marketing_Activity.this);
        accesssaledata();
    }

    public void accesssaledata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/sales.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    SaleName = new String[jsarr1.length()];
                    SaleNumber = new String[jsarr1.length()];
                    SaleEmail = new String[jsarr1.length()];
                    Saledatee = new String[jsarr1.length()];
                    Salespinner = new String[jsarr1.length()];
                    Salepincode = new String[jsarr1.length()];
                    Salestate = new String[jsarr1.length()];
                    SaleDistrict = new String[jsarr1.length()];
                    SaleCity = new String[jsarr1.length()];
                    SaleAddress = new String[jsarr1.length()];
                    SaleLandmark = new String[jsarr1.length()];
                    Saleeid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        SaleName[i] = jsobj.getString("Name");
                        SaleNumber[i] = jsobj.getString("Number");
                        SaleEmail[i] = jsobj.getString("Email");
                        Saledatee[i] = jsobj.getString("Datee");
                        Salespinner[i] = jsobj.getString("Spinner");
                        Salepincode[i] = jsobj.getString("Pincode");
                        Salestate[i] = jsobj.getString("State");
                        SaleDistrict[i] = jsobj.getString("District");
                        SaleCity[i] = jsobj.getString("City");
                        SaleAddress[i] = jsobj.getString("Address");
                        SaleLandmark[i] = jsobj.getString("Landmark");
                        Saleeid[i] = jsobj.getString("Eid");
                        Toast.makeText(Sales_Marketing_Activity.this, "Name" + SaleName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    listsales.setAdapter(ma);

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
                Toast.makeText(Sales_Marketing_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return SaleName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.saleslist,null);

            ImageView imgsale = convertView.findViewById(R.id.imageViewsale);
            TextView SALE_Name = convertView.findViewById(R.id.txtnamesale);
            TextView SALE_Number = convertView.findViewById(R.id.txtnumbersale);
            TextView SALE_Email = convertView.findViewById(R.id.txtemailsale);
            TextView SALE_Date = convertView.findViewById(R.id.txtdatesale);
            TextView SALE_Spinner = convertView.findViewById(R.id.txtspinnersale);
            TextView SALE_Pincode = convertView.findViewById(R.id.txtpincodesale);
            TextView SALE_State = convertView.findViewById(R.id.txtstatesale);
            TextView SALE_District = convertView.findViewById(R.id.txtdistrictsale);
            TextView SALE_City= convertView.findViewById(R.id.txtcitysale);
            TextView SALE_Address = convertView.findViewById(R.id.txtaddresssale);
            TextView SALE_Landmark = convertView.findViewById(R.id.txtlandmarksale);
            TextView SALE_Eid = convertView.findViewById(R.id.txteidsale);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+Saleeid[position]+".jpg";
            Picasso.get().load(urll).into(imgsale);

            SALE_Name.setText(SaleName[position]);
            SALE_Number.setText(SaleNumber[position]);
            SALE_Email.setText(SaleEmail[position]);
            SALE_Date.setText(Saledatee[position]);
            SALE_Spinner.setText(Salespinner[position]);
            SALE_Pincode.setText(Salepincode[position]);
            SALE_State.setText(Salestate[position]);
            SALE_District.setText(SaleDistrict[position]);
            SALE_City.setText(SaleCity[position]);
            SALE_Address.setText(SaleAddress[position]);
            SALE_Landmark.setText(SaleLandmark[position]);
            SALE_Eid.setText(Saleeid[position]);
            return convertView;


        }
    }
}