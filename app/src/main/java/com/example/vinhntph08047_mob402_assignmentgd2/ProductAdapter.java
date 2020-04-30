package com.example.vinhntph08047_mob402_assignmentgd2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Callback;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> productList;
    private List<Cart> cartList;
    OnChangeQuantity onChangeQuantity;

    public ProductAdapter(Context context, List<Product> productList, List<Cart> cartList, OnChangeQuantity onChangeQuantity) {
        this.context = context;
        this.productList = productList;
        this.cartList = cartList;
        this.onChangeQuantity = onChangeQuantity;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_each, null, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.tvProductPrice.setText(productList.get(position).getPrice());
        holder.tvProductName.setText(productList.get(position).getName());
        Glide.with(context).load(Constant.BASE_URL + productList.get(position).getImage()).into(holder.productImg);
        holder.tvQuantity.setText(cartList.get(position).getQuantity() + "");
        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeQuantity.onChangeUp(cartList.get(position), holder.tvQuantity);
                onChangeQuantity.haveChange(true);
            }
        });
        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeQuantity.onChangeDown(cartList.get(position), holder.tvQuantity);
                onChangeQuantity.haveChange(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView tvProductName, tvProductPrice;
        Button btnUp, btnDown;
        TextView tvQuantity;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.productImg);
            tvProductName = itemView.findViewById(R.id.productName);
            tvProductPrice = itemView.findViewById(R.id.productPrice);
            btnDown = itemView.findViewById(R.id.btnDown);
            btnUp = itemView.findViewById(R.id.btnUp);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
