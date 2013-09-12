package com.flipkart.digital.models;

/**
 * User: priya.dharshini
 * Date: 13/09/13
 */
public class MemberDetail {
    public String account_id;
    public String role;
    public String club_name;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }
}
