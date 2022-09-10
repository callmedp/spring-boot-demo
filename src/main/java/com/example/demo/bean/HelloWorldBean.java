package com.example.demo.bean;

public class HelloWorldBean {
    private String message;
    private String information;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    public HelloWorldBean(String message, String information) {
        this.message = message;
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMessage() {
        return message;
    }


    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
