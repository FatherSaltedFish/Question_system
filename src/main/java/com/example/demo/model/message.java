package com.example.demo.model;

public class message {
    String name,email,message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public message(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public message() {
    }

    @Override
    public String toString() {
        return "message{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
