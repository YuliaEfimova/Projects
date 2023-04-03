package org.example.untitled.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "MIPT")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username; //snills
    private String password;
    private String first_Name;
    private String second_Name;
    private String middle_Name;
    private String date_Of_Birth;
    private Integer closed_Sheets;
    private String sick_Date;
    private Integer sick_Period;

    private Boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public void setActive(Boolean active) {
            this.active = active;
        }
    public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }
    public void setSecond_Name(String second_Name) {
        this.second_Name = second_Name;
    }
    public void setMiddle_Name(String middle_Name) {
        this.middle_Name = middle_Name;
    }
    public void setDate_Of_Birth(String date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }
    public void setClosed_Sheets(Integer closed_Sheets) {
        this.closed_Sheets = closed_Sheets;
    }
    public void setSick_Date(String sick_Date) {
        this.sick_Date = sick_Date;
    }
    public void setSick_Period(Integer sick_Period) {
        this.sick_Period = sick_Period;
    }


    public Integer getId() {
        return id;
    }
    public String getPassword() {
       return password;
    }
    public String getFirst_Name() {
        return first_Name;
    }
    public String getSecond_Name() {
        return second_Name;
    }
    public String getMiddle_Name() {
        return middle_Name;
    }
    public String getDate_Of_Birth() {
        return date_Of_Birth;
    }
    public Integer getClosed_Sheets() {
        return closed_Sheets;
    }
    public String getSick_Date() {
       return sick_Date;
    }
    public Integer getSick_Period() {
        return sick_Period;
    }
    public Boolean getActive() {
        return active;
    }
    public Set<Role> getRoles() {
        return roles;
     }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
