package com.likhit.noty.custom;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(T item, int position, View view);
}
