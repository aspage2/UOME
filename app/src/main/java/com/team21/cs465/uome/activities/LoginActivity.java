package com.team21.cs465.uome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.team21.cs465.uome.NavigationBarActivity;

import com.team21.cs465.uome.Data;
import com.team21.cs465.uome.R;
import com.team21.cs465.uome.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.edit_username);
        password = (EditText)findViewById(R.id.edit_password);
        findViewById(R.id.button_sign_in).setOnClickListener(this);
        findViewById(R.id.button_forgot_password).setOnClickListener(this);
    }

    @Override
    public void onClick (View v)
    {
        switch (v.getId())
        {
            case R.id.button_sign_in:
                if (validateCredentials (username.getText().toString(), password.getText().toString())) {
                    Intent intent = new Intent(this, UserProfile.class);
                    intent.putExtra("USER.TAG", username.getText().toString());
                    intent.putExtra("USER.ISME", true);
                    startActivity(intent);
                }
                else if (username.getText().toString().isEmpty())
                    Toast.makeText(this, "Username is empty.", Toast.LENGTH_SHORT).show();
                else if (password.getText().toString().isEmpty())
                    Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_forgot_password:
                if (username.getText().toString().isEmpty())
                    Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "A recovery email was sent to "+username.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean validateCredentials (String user, String password)
    {
        User u = Data.getUser(user);
        return u!=null && u.getPassword().equals(password);
    }
}
