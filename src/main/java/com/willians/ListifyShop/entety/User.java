package com.willians.ListifyShop.entety;

import com.willians.ListifyShop.validation.CpfValid;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
//    @Column(nullable = false, unique = true)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String urlImage;

    public User() {
    }

    public User(String name, String cpf, String email, String password) {
//        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(cpf, user.cpf) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
