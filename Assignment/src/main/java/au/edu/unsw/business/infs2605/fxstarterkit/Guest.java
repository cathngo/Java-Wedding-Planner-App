/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

/**
 *
 * @author Mimi
 */
public class Guest {
    private int guest_id;
    private String guest_fname;
    private String guest_lname;
    private String guest_email;
    private String guest_phone;
    private String diet_require;
    private String guest_access_code;
    private String guest_gender;

    public Guest(int guest_id, String guest_fname, String guest_lname, String guest_email, String guest_phone,String diet_require, String guest_access_code, String guest_gender) {
        this.guest_id = guest_id;
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_email = guest_email;
        this.guest_phone = guest_phone;
        this.guest_access_code = guest_access_code;
        this.diet_require = diet_require;
        this.guest_gender = guest_gender;
    }
       
    public Guest(String guest_fname, String guest_lname, String guest_email, int guest_id) {
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_email = guest_email;
        this.guest_id = guest_id;
        }
        
      
    public Guest(int guest_id, String guest_fname, String guest_lname){
        this.guest_id = guest_id;
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        }
    
    

    public String getGuest_fname() {
        return guest_fname;
    }

    public void setGuest_fname(String guest_fname) {
        this.guest_fname = guest_fname;
    }

    public String getGuest_lname() {
        return guest_lname;
    }

    public void setGuest_lname(String guest_lname) {
        this.guest_lname = guest_lname;
    }
    
    //use this to find the guest id of current user after they log in
    //Guest user is stored in login controller as a publicly accessible variable
    //call LoginController.guestUser.getGuest_id()
    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }

    public String getGuest_access_code() {
        return guest_access_code;
    }

    public void setGuest_access_code(String guest_access_code) {
        this.guest_access_code = guest_access_code;
    }

    public String getDiet_require() {
        return diet_require;
    }

    public void setDiet_require(String diet_require) {
        this.diet_require = diet_require;
    }

    public String getGuest_gender() {
        return guest_gender;
    }

    public void setGuest_gender(String guest_gender) {
        this.guest_gender = guest_gender;
    }
    
}
