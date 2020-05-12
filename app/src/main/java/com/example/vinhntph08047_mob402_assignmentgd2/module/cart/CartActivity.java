package com.example.vinhntph08047_mob402_assignmentgd2.module.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.vinhntph08047_mob402_assignmentgd2.R;
import com.example.vinhntph08047_mob402_assignmentgd2.adapter.CartAdapter;
import com.example.vinhntph08047_mob402_assignmentgd2.api.BaseGetProductCart;
import com.example.vinhntph08047_mob402_assignmentgd2.api.CartResponse;
import com.example.vinhntph08047_mob402_assignmentgd2.api.Root;
import com.example.vinhntph08047_mob402_assignmentgd2.base.api.APIService;
import com.example.vinhntph08047_mob402_assignmentgd2.base.api.ApiModule;
import com.example.vinhntph08047_mob402_assignmentgd2.base.constants.APIConstant;
import com.example.vinhntph08047_mob402_assignmentgd2.base.listener.OnChangeQuantityListener;
import com.example.vinhntph08047_mob402_assignmentgd2.base.model.Cart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnChangeQuantityListener {
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
        APIService APIService = ApiModule.getInstance().create(APIService.class);
        APIService.getAllProductCartById(APIConstant.userId).enqueue(new Callback<Root>() {
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
