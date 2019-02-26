package com.unisa.ium.revidaliam.revidaliam;

import com.unisa.ium.revidaliam.revidaliam.db.RigaOrdineBean;

import java.util.List;
import java.util.Vector;

public class Cart {

    private static List<RigaOrdineBean> cart;

    public static List<RigaOrdineBean> getCart() {
        if (cart == null) {
            cart = new Vector<RigaOrdineBean>();
        }
        return cart;
    }

    public static void addProduct(RigaOrdineBean rigaOrdineBean) {
        if (cart == null) {
            cart = new Vector<RigaOrdineBean>();
        }
        cart.add(rigaOrdineBean);
    }

    public static void removeProduct(RigaOrdineBean rigaOrdineBean) {
        if (cart == null) {
            cart = new Vector<RigaOrdineBean>();
        }

        cart.remove(rigaOrdineBean);
    }

    public static void getProduct(int i) {
        if (cart == null) {
            cart = new Vector<RigaOrdineBean>();
        }

        cart.get(i);
    }
}
