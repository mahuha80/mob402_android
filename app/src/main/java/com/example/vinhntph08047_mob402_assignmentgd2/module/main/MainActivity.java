package com.example.vinhntph08047_mob402_assignmentgd2.module.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinhntph08047_mob402_assignmentgd2.R;
import com.example.vinhntph08047_mob402_assignmentgd2.api.LoginResponse;
import com.example.vinhntph08047_mob402_assignmentgd2.base.api.APIService;
import com.example.vinhntph08047_mob402_assignmentgd2.base.api.ApiModule;
import com.example.vinhntph08047_mob402_assignmentgd2.base.constants.APIConstant;
import com.example.vinhntph08047_mob402_assignmentgd2.module.register.RegisterActivity;
import com.example.vinhntph08047_mob402_assignmentgd2.module.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister, btnLogin;
    EditText edUsername, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void signIn() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        APIService APIService = ApiModule.getInstance().create(APIService.class);
        APIService.loginUser(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    APIConstant.userId = loginResponse.getUserId();
                    APIConstant.TOKEN = loginResponse.getToken();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, call.toString()+"\n"+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                signIn();
                break;
            case R.id.btnRegister:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
