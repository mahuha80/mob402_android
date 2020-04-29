package com.example.vinhntph08047_mob402_assignmentgd2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    EditText edUsername, edPassword, edRetypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    private void init() {
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        edRetypePassword = findViewById(R.id.edRetypePassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                executeRegister();
                break;
        }
    }

    private void executeRegister() {
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String retypepassword = edRetypePassword.getText().toString();
        if (!password.equalsIgnoreCase(retypepassword)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu trùng nhau", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
            RetrofitService retrofitService = APIClient.getInstance().create(RetrofitService.class);
            retrofitService.registerUser(username, password).enqueue(new Callback<RegisterResponse>() {
                @Override
                public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                    RegisterResponse registerResponse = response.body();
                    if (response.code() == 200) {
                        Toast.makeText(RegisterActivity.this, "Đăng kí tài khoản thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Đăng kí tài khoản không thành công", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegisterResponse> call, Throwable t) {

                }
            });
        }
    }
}
