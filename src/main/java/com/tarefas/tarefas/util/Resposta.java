package com.tarefas.tarefas.util;

public class Resposta<T> {
    private int status;
    private  String menssagen;
    private T data;

    public Resposta(int status, String menssagen, T data) {
        this.status = status;
        this.menssagen = menssagen;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMenssagen() {
        return menssagen;
    }

    public void setMenssagen(String menssagen) {
        this.menssagen = menssagen;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
