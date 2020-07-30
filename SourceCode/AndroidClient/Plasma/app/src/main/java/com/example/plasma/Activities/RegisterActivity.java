package com.example.plasma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.plasma.R;
import com.example.plasma.Utils.Endpoints;
import com.example.plasma.Utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name_text, city_text, password_text, mobile_text;
    private Spinner bloodGroup_dropdown;

    private Button submitButton;
    String[] country = { "India", "USA", "China", "Japan", "Other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_text = findViewById(R.id.first_name);
        city_text = findViewById(R.id.city);
        bloodGroup_dropdown = (Spinner) findViewById(R.id.blood_group);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.BloodGroup));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup_dropdown.setAdapter(adapter);
        password_text = findViewById(R.id.password);
        mobile_text = findViewById(R.id.number);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, city, blood_group, password, mobile;
                name = name_text.getText().toString();
                city = city_text.getText().toString();
                blood_group = bloodGroup_dropdown.getSelectedItem().toString();
                password = password_text.getText().toString();
                mobile = mobile_text.getText().toString();
                if (isValid(name, city, blood_group, password, mobile)) {
                    register(name, city, blood_group, password, mobile);
                }
            }
        });




    }

    private void register(final String name, final String city, final String blood_group, final String password,
                          final String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("Success")){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
                            .putString("city", city).apply();
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                }else{
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("blood_group", blood_group);
                params.put("password", password);
                params.put("number", mobile);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private boolean isValid(String name, String city, String blood_group, String password,
                            String mobile) {
        List<String> valid_blood_groups = new ArrayList<>();
        valid_blood_groups.add("A+");
        valid_blood_groups.add("A-");
        valid_blood_groups.add("B+");
        valid_blood_groups.add("B-");
        valid_blood_groups.add("AB+");
        valid_blood_groups.add("AB-");
        valid_blood_groups.add("O+");
        valid_blood_groups.add("O-");
        if (name.isEmpty()) {
            showMessage("Name is empty");
            return false;
        } else if (city.isEmpty()) {
            showMessage("City name is required");
            return false;
        } else if (!valid_blood_groups.contains(blood_group)) {
            showMessage("Blood group invalid choose from " + valid_blood_groups);
            return false;
        } else if (mobile.length() != 10) {
            showMessage("Invalid mobile number, number should be of 10 digits");
            return false;
        }
        return true;
    }


    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text =   adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}