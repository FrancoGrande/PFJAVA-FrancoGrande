package com.Tienda.Franco.utils;

import lombok.Data;

@Data
public class TiendaResponseMsg {

    private String msg;
    private Object data;

    public TiendaResponseMsg(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }
}
