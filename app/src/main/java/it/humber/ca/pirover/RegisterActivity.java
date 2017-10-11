package it.humber.ca.pirover;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final Button registerB = (Button)findViewById(R.id.registerButton);

        final EditText editName = (EditText)findViewById(R.id.editName);
        final EditText editUser = (EditText)findViewById(R.id.editUser);
        final EditText editPass = (EditText)findViewById(R.id.editPassword);
        final EditText editConfirmPass = (EditText)findViewById(R.id.editConfirmPassword);

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = editName.getText().toString();
                final String username = editUser.getText().toString();
                final String password = editPass.getText().toString();
                final String content = "";

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, FirstActivity.class);
                                startActivity(intent); //modify if not working

                            } else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        }catch (JSONException e){
                            e.printStackTrace();

                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(name, username, password, content, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);

            }
        });




    }
}
