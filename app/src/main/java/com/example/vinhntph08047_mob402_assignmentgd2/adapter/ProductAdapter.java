package com.example.vinhntph08047_mob402_assignmentgd2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vinhntph08047_mob402_assignmentgd2.R;
import com.example.vinhntph08047_mob402_assignmentgd2.api.Product;
import com.example.vinhntph08047_mob402_assignmentgd2.base.listener.OnChangeQuantityListener;
import com.example.vinhntph08047_mob402_assignmentgd2.base.model.Cart;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private Context context;
    private List<Product> productList;
    private List<Cart> cartList;
    OnChangeQuantityListener onChangeQuantityListener;

    public ProductAdapter(Context context, List<Product> productList, List<Cart> cartList, OnChangeQuantityListener onChangeQuantityListener) {
        this.context = context;
        this.productList = productList;
        this.cartList = cartList;
        this.onChangeQuantityListener = onChangeQuantityListener;
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
        Glide.with(context).load( productList.get(position).getImage()).into(holder.productImg);
        holder.tvQuantity.setText(cartList.get(position).getQuantity() + "");
        holder.btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeQuantityListener.onChangeUp(cartList.get(position), holder.tvQuantity);
                onChangeQuantityListener.haveChange(true);
            }
        });
        holder.btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeQuantityListener.onChangeDown(cartList.get(position), holder.tvQuantity);
                onChangeQuantityListener.haveChange(true);
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
