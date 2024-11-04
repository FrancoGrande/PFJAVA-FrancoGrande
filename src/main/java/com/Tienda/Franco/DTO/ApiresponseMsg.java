package com.Tienda.Franco.DTO;

import org.springframework.http.HttpStatus;

public class ApiresponseMsg {
    
    private String message;
    private int status;
    private Object data;

    public ApiresponseMsg(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public ApiresponseMsg(String message, HttpStatus status, Object data) {
        this.message = message;
        this.status = status.value();
        this.data = data;
    }

    // Getters y setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}