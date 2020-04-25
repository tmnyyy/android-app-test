package com.itstep.em.appa.data.model;


public class Member {

    private int user_id;
    private String user_fname;
    private String user_lname;
    private String user_phone;
    private String user_email;
    private String user_password;

    public Member(){

    }

    public Member(int id, String fname, String lname,
                  String phone, String email, String pass){
        this.user_id = id;
        this.user_fname = fname;
        this.user_lname = lname;
        this.user_phone = phone;
        this.user_email = email;
        this.user_password = pass;
    }

    public Member(String fname, String lname,
                  String phone, String email, String pass){

        this.user_fname = fname;
        this.user_lname = lname;
        this.user_phone = phone;
        this.user_email = email;
        this.user_password = pass;
    }



    public int getUser_id() {
        return user_id;
    }

    public String getUser_fname() {
        return user_fname;
    }

    public String getUser_lname() {
        return user_lname;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_fname(String user_fname) {
        this.user_fname = user_fname;
    }

    public void setUser_lname(String user_lname) {
        this.user_lname = user_lname;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


}
