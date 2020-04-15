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
public class RSVPEventWrapper {
    private String event_name;
    private String event_date;
    private String decision;
    private String event_start_time;
    private String event_end_time;
    private int event_id;

    public RSVPEventWrapper(String event_name, String event_date, String decision, String event_start_time, String event_end_time, int event_id) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.decision = decision;
        this.event_start_time = event_start_time;
        this.event_end_time = event_end_time;
        this.event_id = event_id;
    }

    public RSVPEventWrapper(String event_name, String event_date, String decision) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.decision = decision;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
    
    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    
    public String getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(String event_start_time) {
        this.event_start_time = event_start_time;
    }

    public String getEvent_end_time() {
        return event_end_time;
    }

    public void setEvent_end_time(String event_end_time) {
        this.event_end_time = event_end_time;
    }
}
