package com.unibank.elastic.model;

public class Response {
    private  String methodName;
    private boolean status;


    public Response(String methodName, boolean status) {
        this.methodName = methodName;
        this.status = status;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean getStatus() {
        return status;
    }
}
