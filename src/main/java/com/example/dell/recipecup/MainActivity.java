package com.example.dell.recipecup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText name_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
    public void login(View v)
    {
        String UserName = name_et.getText().toString();
        String Password = password_et.getText().toString();
        if (UserName.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your Emplyee ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Password.equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your Password", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject js = new JSONObject();
        try {
            js.put("name", UserName);
            js.put("pas", Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jobjreq = new JsonObjectRequest("http://192.168.0.70/EmployeeLogin.php", js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jobjreq.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));

        com.example.dell.employee_details.AppController ap = new com.example.dell.employee_details.AppController(MainActivity.this);
        ap.addToRequestQueue(jobjreq);

    }

