package com.igc.capstone_automative;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Demo extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    int count = 0;
    TextInputEditText name1,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.demo);
        imageView = findViewById(R.id.imageView);

        name1 = findViewById(R.id.name1);
        password = findViewById(R.id.password);
        /*imageView.setOnTouchListener(new com.shashank.platform.loginui.OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeLeft() {
                if (count == 0) {
                    imageView.setImageResource(R.drawable.good_night_img);
                    textView.setText("Night");
                    count = 1;
                } else {
                    imageView.setImageResource(R.drawable.good_morning_img);
                    textView.setText("Morning");
                    count = 0;
                }
            }

            public void onSwipeBottom() {
            }

        });*/
    }

    /*public void forgotpassword1(View view) {
        startActivity(new Intent(Demo.this,ForgotPassword.class));
    }*/

    public void submitlogin(View view) {
        if(name1.getText().toString().isEmpty() && password.getText().toString().isEmpty())
        {
            name1.setError("Please Enter Email");name1.requestFocus();
            password.setError("Please Enter Password");password.requestFocus();
        }else if(name1.getText().toString().isEmpty())
        {
            name1.setError("Please Enter Email");name1.requestFocus();
        }else if(password.getText().toString().isEmpty())
        {
            password.setError("Please Enter Password");password.requestFocus();
        }
        else
        {
            if(name1.getText().toString().equals("Admin") && password.getText().toString().equals("Admin"))
            {
                startActivity(new Intent(Demo.this,DashBoard_Activity.class));
                SharedPreferences sp = getSharedPreferences("File",MODE_PRIVATE);
                SharedPreferences.Editor se = sp.edit();
                se.putString("Admin","Admin");
                se.putString("Password","Admin");
                se.commit();
                finish();
            }
            else
            {
                name1.setError("Wrong Email");name1.requestFocus();
                password.setError("Wrong Password");password.requestFocus();

            }
        }

    }
}
