package com.example.vinhntph08047_mob402_assignmentgd2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vinhntph08047_mob402_assignmentgd2.R;
import com.example.vinhntph08047_mob402_assignmentgd2.api.CartResponse;
import com.example.vinhntph08047_mob402_assignmentgd2.base.listener.OnChangeQuantityListener;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context context;
    List<CartResponse> cartResponseList;
    OnChangeQuantityListener onChangeQuantityListener;

    public CartAdapter(Context context, List<CartResponse> cartResponseList, OnChangeQuantityListener onChangeQuantityListener) {
        this.context = context;
        this.cartResponseList = cartResponseList;
        this.onChangeQuantityListener = onChangeQuantityListener;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_each, null, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.tvQuantity.setText("Số lượng " + cartResponseList.get(position).getQuantity() + "");
        holder.tvPrice.setText("Giá " + cartResponseList.get(position).getPrice() + "");
        holder.tvProductId.setText(cartResponseList.get(position).getId());
        int thanhTien = Integer.parseInt(cartResponseList.get(position).getPrice()) * Integer.parseInt(cartResponseList.get(position).getQuantity());
        holder.tvThanhTien.setText("Thành tiền " + thanhTien);
        holder.btnRm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeQuantityListener.notifyItemRemove(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartResponseList.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        TextView tvProductId, tvQuantity, tvPrice, tvThanhTien;
        Button btnRm;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductId = itemView.findViewById(R.id.tvProductId);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvThanhTien = itemView.findViewById(R.id.tvThanhTien);
            btnRm = itemView.findViewById(R.id.btnRemove);
        }
    }
}
