package com.harry.zyneticbookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Email Required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password Required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
