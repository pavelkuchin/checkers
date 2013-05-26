package com.checkers.server.beans;

import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */

public class User {
    // User unique identifier
    private Long    uuid;

    // User login
    private String  login;
    // First name
    private String  firstName;
    // Last name
    private String  lastName;

    // Email
    private String  email;

    // Password
    private String  password;

    // Enabled flag (if flag set in false then user hasn't access to system)
    private Boolean enabled;

    // Date of user creation
    private Date    created;
    // Date of user modification
    private Date    modified;
    // Date of last user login
    private Date    lastLogin;

    public User(){
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}

