package com.example.vinhntph08047_mob402_assignmentgd2;

import android.view.View;
import android.widget.TextView;

public interface OnChangeQuantity {
    void onChangeUp(Cart cart, TextView view);
    void onChangeDown(Cart cart, TextView view);
    void haveChange(Boolean show);

}
