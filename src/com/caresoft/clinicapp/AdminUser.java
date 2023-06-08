package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin{
    //... imports class definition...

    // Inside class:
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;

    // TO DO: Implement a constructor that takes an ID and a role
    // TO DO: Implement HIPAACompliantUser!
    // TO DO: Implement HIPAACompliantAdmin!

    public AdminUser(Integer id, String role) {
        super(id);
        this.role = role;
        this.securityIncidents = new ArrayList<>();
    }

    public void newIncident(String notes) {
        String report = String.format(
                "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n",
                new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
                "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n",
                new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }

    @Override
    public ArrayList<String> reportSecurityIncidents() {
        return this.securityIncidents;
    }

    @Override
    public boolean assignPin (int pin){
        if (Integer.toString(pin).length() == 6){
            this.pin = pin;
            return true;
        }
        return false;
    }

    @Override
    public boolean accessAuthorized(Integer givenId){
        if (this.id.equals(givenId)){
            return true;
        }
        authIncident();
        return false;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getSecurityIncidents() {
        return securityIncidents;
    }

    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
        this.securityIncidents = securityIncidents;
    }

}
