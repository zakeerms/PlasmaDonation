package com.example.plasma.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.plasma.R;
import com.example.plasma.Utils.Endpoints;
import com.example.plasma.Utils.VolleySingleton;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

  EditText mobileNumber, password;
  Button login_button;
  TextView register_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    mobileNumber = findViewById(R.id.number);
    password = findViewById(R.id.password);
    login_button = findViewById(R.id.submit_button);
    register_button = findViewById(R.id.sign_up_text);
    register_button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
      }
    });

    login_button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        mobileNumber.setError(null);
        password.setError(null);
        String number = mobileNumber.getText().toString();
        String password = LoginActivity.this.password.getText().toString();
        if (isValid(number, password)) {
          login(number, password);
        }
      }
    });
  }


  private void login(final String number, final String password) {
    StringRequest stringRequest = new StringRequest(
        Method.POST, Endpoints.login_url, new Listener<String>() {
      @Override
      public void onResponse(String response) {
        if (!response.equals("Invalid Credentials")) {
          Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
          startActivity(new Intent(LoginActivity.this, MainActivity.class));
          PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
              .putString("number", number).apply();
          PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit()
              .putString("city", response).apply();
          LoginActivity.this.finish();
        } else {
          Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
        }
      }
    }, new ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(LoginActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
        Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));
      }
    }) {
      @Override
      protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<>();
        params.put("password", password);
        params.put("number", number);
        return params;
      }
    };
    VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
  }


  private boolean isValid(String number, String password) {
    if (number.isEmpty()) {
      showMessage("Empty Mobile Number");
      mobileNumber.setError("Empty Mobile Number");
      return false;
    } else if (password.isEmpty()) {
      showMessage("Empty Password");
      this.password.setError("Empty Password");
      return false;
    }
    return true;
  }


  private void showMessage(String msg) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


}
