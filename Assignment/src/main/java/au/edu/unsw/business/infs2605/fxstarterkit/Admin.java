/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

/**
 *
 * @author jaydenso
 */
public class Admin {
    private int admin_id;
    private String admin_name;
    private String admin_username;
    private String admin_password;

    public Admin(int admin_id, String admin_name, String admin_username, String admin_password) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_username = admin_username;
        this.admin_password = admin_password;
    }
    //use this to find the admin id of current user after they log in
    //Admin user is stored in login controller as a publicly accessible variable
    //call LoginController.adminUser.getAdmin_id()
    public int getAdmin_id() {
        return admin_id;
    }

    
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }
      
}
