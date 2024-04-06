package com.learn;

public class Data {
    String email;
    String first_name;
    String last_name;
    String avatar;

    public Data(String email, String first_name, String last_name, String avatar) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Data [email=" + email + ", first_name=" + first_name + ", last_name=" + last_name + ", avatar=" + avatar
                + ", getEmail()=" + getEmail() + ", getFirst_name()=" + getFirst_name() + ", getLast_name()="
                + getLast_name() + ", getAvatar()=" + getAvatar() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
    
    
}
