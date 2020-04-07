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
public class Event {
  
   String event_name;

   String event_date;
   String event_start_time;
   String event_end_time;
   String event_instructions;

    public Event(String event_name, String event_date, String event_start_time, String event_end_time, String event_instructions) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.event_start_time = event_start_time;
        this.event_end_time = event_end_time;
        this.event_instructions = event_instructions;
        
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

    public String getEvent_instructions() {
        return event_instructions;
    }

    public void setEvent_instructions(String event_instructions) {
        this.event_instructions = event_instructions;
    }

    

   
}
