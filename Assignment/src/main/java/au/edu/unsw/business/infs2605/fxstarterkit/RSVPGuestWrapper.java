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
public class RSVPGuestWrapper {
    private String guest_fname;
    private String guest_lname;
    private String decision;

    public RSVPGuestWrapper(String guest_fname, String guest_lname, String decision) {
        this.guest_fname = guest_fname;
        this.guest_lname = guest_lname;
        this.decision = decision;
        
        
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

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    
    
    
}
