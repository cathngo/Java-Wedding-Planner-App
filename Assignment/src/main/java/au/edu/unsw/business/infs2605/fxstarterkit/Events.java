/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.unsw.business.infs2605.fxstarterkit;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author cathy
 */
public class Events {
    private IntegerProperty event_id;
    private StringProperty event_name;
    private StringProperty event_date;
    private StringProperty event_start_time;
    private StringProperty event_end_time;
    private StringProperty event_address;
    private StringProperty event_description;
    private StringProperty event_instructions;
    
   

    public Events(int event_id, String event_name, String event_date, String event_start_time, String event_end_time, String event_address, String event_description, String event_instructions) {
        this.event_id = new SimpleIntegerProperty(event_id);
        this.event_name = new SimpleStringProperty(event_name);
        this.event_date = new SimpleStringProperty(event_date);
        this.event_start_time = new SimpleStringProperty(event_start_time);
        this.event_end_time = new SimpleStringProperty(event_end_time);
        this.event_address = new SimpleStringProperty(event_address);
        this.event_description = new SimpleStringProperty(event_description);
        this.event_instructions = new SimpleStringProperty(event_instructions);
    }

    public IntegerProperty getEvent_id() {
        return event_id;
    }

    public void setEvent_id(IntegerProperty event_id) {
        this.event_id = event_id;
    }

    public StringProperty getEvent_name() {
        return event_name;
    }

    public void setEvent_name(StringProperty event_name) {
        this.event_name = event_name;
    }
    
    public StringProperty event_nameProperty() {
        return event_name;
    }

    public StringProperty getEvent_date() {
        return event_date;
    }

    public void setEvent_date(StringProperty event_date) {
        this.event_date = event_date;
    }
    
    public StringProperty event_dateProperty() {
        return event_date;
    }

    public StringProperty getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(StringProperty event_start_time) {
        this.event_start_time = event_start_time;
    }
    
    public StringProperty event_start_timeProperty() {
        return event_date;
    }

    public StringProperty getEvent_end_time() {
        return event_end_time;
    }

    public void setEvent_end_time(StringProperty event_end_time) {
        this.event_end_time = event_end_time;
    }
    public StringProperty event_end_timeProperty() {
        return event_end_time;
    }

    public StringProperty getEvent_address() {
        return event_address;
    }

    public void setEvent_address(StringProperty event_address) {
        this.event_address = event_address;
    }
public StringProperty event_addressProperty() {
        return event_address;
    }
    public StringProperty getEvent_description() {
        return event_description;
    }

    public void setEvent_description(StringProperty event_description) {
        this.event_description = event_description;
    }
    
    public StringProperty event_descriptionProperty() {
        return event_description;
    }

    public StringProperty getEvent_instructions() {
        return event_instructions;
    }

    public void setEvent_instructions(StringProperty event_instructions) {
        this.event_instructions = event_instructions;
    }
      public StringProperty event_instructionsProperty() {
        return event_description;
    }
      
      public IntegerProperty event_idProperty(){
        return event_id;
    }

 
}
