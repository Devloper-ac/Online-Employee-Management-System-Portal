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

public class Purchase_Department_Activity extends AppCompatActivity {

    ListView purchaselistview;
    String[] purchaseName,purchaseNumber,purchaseEmail,purchasedatee,purchasespinner,purchasepincode,purchasestate,purchaseDistrict,purchaseCity,purchaseAddress,purchaseLandmark,purchaseeid;
    RequestQueue rq;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase__department_activity);

        purchaselistview = findViewById(R.id.purchaselistview);

        rq = Volley.newRequestQueue(Purchase_Department_Activity.this);
        pd = new ProgressDialog(Purchase_Department_Activity.this);
        accesspurchasedata();
    }

    public void accesspurchasedata()
    {
        pd.setTitle("Accessing Data...");
        pd.setMessage("Please Wait... Accessing Data From Server...");
        pd.show();
        String url = "https://lifeshareofficial.000webhostapp.com/purchaselist.php";
        JsonObjectRequest jsrq1 = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    JSONArray jsarr1 = response.getJSONArray("Result");

                    purchaseName = new String[jsarr1.length()];
                    purchaseNumber = new String[jsarr1.length()];
                    purchaseEmail = new String[jsarr1.length()];
                    purchasedatee = new String[jsarr1.length()];
                    purchasespinner = new String[jsarr1.length()];
                    purchasepincode = new String[jsarr1.length()];
                    purchasestate = new String[jsarr1.length()];
                    purchaseDistrict = new String[jsarr1.length()];
                    purchaseCity = new String[jsarr1.length()];
                    purchaseAddress = new String[jsarr1.length()];
                    purchaseLandmark = new String[jsarr1.length()];
                    purchaseeid = new String[jsarr1.length()];
                    JSONObject jsobj;
                    for (int i = 0; i < jsarr1.length(); i++) {
                        jsobj = jsarr1.getJSONObject(i);

                        purchaseName[i] = jsobj.getString("Name");
                        purchaseNumber[i] = jsobj.getString("Number");
                        purchaseEmail[i] = jsobj.getString("Email");
                        purchasedatee[i] = jsobj.getString("Datee");
                        purchasespinner[i] = jsobj.getString("Spinner");
                        purchasepincode[i] = jsobj.getString("Pincode");
                        purchasestate[i] = jsobj.getString("State");
                        purchaseDistrict[i] = jsobj.getString("District");
                        purchaseCity[i] = jsobj.getString("City");
                        purchaseAddress[i] = jsobj.getString("Address");
                        purchaseLandmark[i] = jsobj.getString("Landmark");
                        purchaseeid [i] = jsobj.getString("Eid");
                        Toast.makeText(Purchase_Department_Activity.this, "Name" + purchaseName[i], Toast.LENGTH_SHORT).show();
                    }
                    MyAdapter ma = new MyAdapter();
                    purchaselistview.setAdapter(ma);

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
                Toast.makeText(Purchase_Department_Activity.this, "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsrq1);
    }


    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return purchaseName.length;
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
            convertView = getLayoutInflater().inflate(R.layout.purchaselistview,null);

            ImageView imgpur = convertView.findViewById(R.id.imageViewpurchase);

            TextView purchase_Name = convertView.findViewById(R.id.txtnamepurchase);
            TextView purchase_Number = convertView.findViewById(R.id.txtnumberpurchase);
            TextView purchase_Email = convertView.findViewById(R.id.txtemailpurchase);
            TextView purchase_Date = convertView.findViewById(R.id.txtdatepurchase);
            TextView purchase_Spinner = convertView.findViewById(R.id.txtspinnerpurchase);
            TextView purchase_Pincode = convertView.findViewById(R.id.txtpincodepurchase);
            TextView purchase_State = convertView.findViewById(R.id.txtstatepurchase);
            TextView purchase_District = convertView.findViewById(R.id.txtdistrictpurchase);
            TextView purchase_City= convertView.findViewById(R.id.txtcitypurchase);
            TextView purchase_Address = convertView.findViewById(R.id.txtaddresspurchase);
            TextView purchase_Landmark = convertView.findViewById(R.id.txtlandmarkpurchase);
            TextView purchase_Eid = convertView.findViewById(R.id.txteidpurchase);


            String urll ="https://lifeshareofficial.000webhostapp.com/Ownerimage/"+purchaseeid[position]+".jpg";
            Picasso.get().load(urll).into(imgpur);

            purchase_Name.setText(purchaseName[position]);
            purchase_Number.setText(purchaseNumber[position]);
            purchase_Email.setText(purchaseEmail[position]);
            purchase_Date.setText(purchasedatee[position]);
            purchase_Spinner.setText(purchasespinner[position]);
            purchase_Pincode.setText(purchasepincode[position]);
            purchase_State.setText(purchasestate[position]);
            purchase_District.setText(purchaseDistrict[position]);
            purchase_City.setText(purchaseCity[position]);
            purchase_Address.setText(purchaseAddress[position]);
            purchase_Landmark.setText(purchaseLandmark[position]);
            purchase_Eid.setText(purchaseeid[position]);
            return convertView;


        }
    }
}