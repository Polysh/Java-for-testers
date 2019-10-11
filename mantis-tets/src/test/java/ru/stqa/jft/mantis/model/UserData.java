package ru.stqa.jft.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mantis_user_table")
public class UserData {
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String userName;

    @Column(name = "email")
    @Type(type = "text")
    private String email;





    @Column(name = "password")
    @Type(type = "text")
    private String password;


    public Set<UserData> users = new HashSet<>();

    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }


    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserData withEmail(String email) {
        this.email = email;
        return this;
    }
    public UserData withPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return id == userData.id &&
                Objects.equals(userName, userData.userName) &&
                Objects.equals(email, userData.email) &&
                Objects.equals(users, userData.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, users);
    }

}
