package org.example.bookslibrary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patron", cascade = CascadeType.ALL)
    private Set<BorrowingRecord> borrowedBooks = new HashSet<>();

    @Transient // This field will not be stored in the database
    private String contactInformation;
    public String getContactInformation() {
        return this.name + ", " + this.email + ", " + this.phoneNumber;
    }

}
