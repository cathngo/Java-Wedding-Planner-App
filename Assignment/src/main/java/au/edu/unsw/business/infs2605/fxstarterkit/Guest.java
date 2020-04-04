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
    private String guest_id;
    private String guest_name;
    private String guest_email;
    private String guest_phone;
    private String guest_access_code;

    public Guest(String guest_id, String guest_name, String guest_email, String guest_phone, String guest_access_code) {
        this.guest_id = guest_id;
        this.guest_name = guest_name;
        this.guest_email = guest_email;
        this.guest_phone = guest_phone;
        this.guest_access_code = guest_access_code;
    }

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
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

}
