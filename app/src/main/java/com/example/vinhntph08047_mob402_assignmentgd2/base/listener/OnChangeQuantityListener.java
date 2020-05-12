package com.example.vinhntph08047_mob402_assignmentgd2.base.listener;

import android.widget.TextView;

import com.example.vinhntph08047_mob402_assignmentgd2.base.model.Cart;

public interface OnChangeQuantityListener {
    void onChangeUp(Cart cart, TextView view);
    void onChangeDown(Cart cart, TextView view);
    void haveChange(Boolean show);
    void notifyItemRemove(int position);

}
