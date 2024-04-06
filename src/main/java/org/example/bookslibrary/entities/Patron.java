package org.example.bookslibrary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Table(name = "patrons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Patron{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Email
    private String email;
    private String phoneNumber;
    @Transient // This field will not be stored in the database
    private String contactInformation;
    public String getContactInformation() {
        return this.name + ", " + this.email + ", " + this.phoneNumber;
    }

}
