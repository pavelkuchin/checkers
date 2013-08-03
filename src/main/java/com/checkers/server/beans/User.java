package com.checkers.server.beans;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 *
 *
 * @author Pavel_Kuchin
 */
@Entity
public class User {

    // User unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long    uuid;

    // User login
    @Column(unique=true)
    @Length(min = 4, max = 50, message = "Min login length is 4. Max is 50.")
    @Pattern(regexp = "[A-Za-z0-9//.-_]*",
            message =   "Incorrect login. Login should contain only latin letters in Upper or Lower cases , " +
                        "Numbers, point (.), dash(-), underscore(_)")
    private String  login;
    // First name
    private String  firstName;
    // Last name
    private String  lastName;

    // Email
    @Column(unique=true)
    @Email
    private String  email;

    // Password
    private String  password;

    // Enabled flag (if flag set in false then user hasn't access to system)
    private Boolean enabled;

    // User role (for Spring security)
    @Pattern(regexp = "ROLE_ADMIN|ROLE_USER", message = "Role field must be equal to ROLE_ADMIN or ROLE_USER")
    private String  role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @JsonProperty(value = "password")
    public Object getNull(){
        return null;
    }

}

