package com.example.vinhntph08047_mob402_assignmentgd2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnChangeQuantity {
    List<CartResponse> list;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    TextView tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rv);
        tvTongTien=findViewById(R.id.tvTongTien);
        RetrofitService retrofitService = APIClient.getInstance().create(RetrofitService.class);
        retrofitService.getAllProductCartById(Constant.userId).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.code() == 200) {
                    Root root = response.body();
                    BaseGetProductCart[] baseGetProductCart = root.getData();
                    for (int i = 0; i < baseGetProductCart.length; i++) {
                        CartResponse[] cart = baseGetProductCart[i].getItems();
                        for (CartResponse cartResponse : cart) {
                            list.add(cartResponse);
                        }
                    }
                    cartAdapter = new CartAdapter(CartActivity.this, list, CartActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this);
                    recyclerView.setAdapter(cartAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    getTongTien();


                    //                list = Arrays.asList(baseGetProductCart.getItems());
//                Toast.makeText(CartActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {

            }
        });
    }

    public void getTongTien() {
        int tongTien = 0;
        for (int i = 0; i < list.size(); i++) {
            int thanhTien = Integer.parseInt(list.get(i).getPrice()) * Integer.parseInt(list.get(i).getQuantity());
            tongTien += thanhTien;
        }
        tvTongTien.setText("Thành tiền " +tongTien+"");
    }

    @Override
    public void onChangeUp(Cart cart, TextView view) {

    }

    @Override
    public void onChangeDown(Cart cart, TextView view) {

    }

    @Override
    public void haveChange(Boolean show) {

    }

    @Override
    public void notifyItemRemove(int position) {
        list.remove(position);
        cartAdapter.notifyItemRemoved(position);
        getTongTien();
    }
}
