package com.flipkart.digital.models;

/**
 * User: priya.dharshini
 * Date: 13/09/13
 */
public class ClubMember {
    public String name;
    public String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
