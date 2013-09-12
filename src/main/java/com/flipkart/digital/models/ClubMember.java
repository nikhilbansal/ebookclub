package com.flipkart.digital.models;

/**
 * User: priya.dharshini
 * Date: 13/09/13
 */
public class ClubMember {
    public String account_id;
    public String role;

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

    @Override
    public String toString() {
        return "ClubMember{" +
                "account_id='" + account_id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
