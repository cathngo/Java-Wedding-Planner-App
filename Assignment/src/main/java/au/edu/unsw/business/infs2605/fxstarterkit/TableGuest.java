/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

/**
 *
 * @author cathy
 */
public class TableGuest {
    String guest_fname;
    String guest_lname;
    String guest_email;
    int guest_id;

    public TableGuest(String guest_fname, String guest_lname, String guest_email, int guest_id) {
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.guest_email = guest_email;
        this.guest_id = guest_id;
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

    public String getGuest_email() {
        return guest_email;
    }

    public void setGuest_email(String guest_email) {
        this.guest_email = guest_email;
    }

    public int getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(int guest_id) {
        this.guest_id = guest_id;
    }

   
            
}
