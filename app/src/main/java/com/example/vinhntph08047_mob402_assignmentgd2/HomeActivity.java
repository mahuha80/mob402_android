package com.example.vinhntph08047_mob402_assignmentgd2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements OnChangeQuantity {
    List<Product> productList = new ArrayList<>();
    RecyclerView rv;
    ProductAdapter productAdapter;
    List<Cart> cartList = new ArrayList<>();
    Button btnThanhToan;
    RetrofitService retrofitService;
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rv = findViewById(R.id.rv);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setVisibility(View.INVISIBLE);
        retrofitService = APIClient.getInstance().create(RetrofitService.class);
        retrofitService.getAllProduct().enqueue(new Callback<BaseProductResponse>() {
            @Override
            public void onResponse(Call<BaseProductResponse> call, Response<BaseProductResponse> response) {
                BaseProductResponse baseProductResponse = response.body();
                productList = baseProductResponse.getData();
                for (int i = 0; i < productList.size(); i++) {
                    Cart cart = new Cart(productList.get(i).getId(), 0, productList.get(i).getPrice());
                    cartList.add(cart);
                }
                productAdapter = new ProductAdapter(HomeActivity.this, productList, cartList, HomeActivity.this);
                rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                rv.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<BaseProductResponse> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Can't get data from server :D", Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onChangeUp(Cart cart, TextView view) {
        cart.setQuantity(cart.getQuantity() + 1);
        view.setText(cart.getQuantity() + "");
    }

    @Override
    public void onChangeDown(Cart cart, TextView view) {
        int quantityAfter = cart.getQuantity() - 1;
        if (quantityAfter >= 0) {
            cart.setQuantity(cart.getQuantity() - 1);
            view.setText(cart.getQuantity() + "");
        } else {
            Toast.makeText(this, "Số lượng không thể âm", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void haveChange(Boolean show) {
        if (show == true) {
            btnThanhToan.setVisibility(View.VISIBLE);
            btnThanhToan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retrofitService.postProductToCart(Constant.userId,cartList).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code()==200){
                                Toast.makeText(HomeActivity.this, "Upload lên server thành công", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(HomeActivity.this,CartActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

    @Override
    public void notifyItemRemove(int position) {

    }

}
