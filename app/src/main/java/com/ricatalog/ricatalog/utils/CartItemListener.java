package com.ricatalog.ricatalog.utils;

import com.ricatalog.ricatalog.database.CartEntity;

import java.util.Map;

public interface CartItemListener {
    public void increaseQty(CartEntity entity);
    public void decreaseQty(CartEntity entity);
    public void deleteItem(CartEntity entity);
    public void emptyCart(Map<String,String> map);
}
