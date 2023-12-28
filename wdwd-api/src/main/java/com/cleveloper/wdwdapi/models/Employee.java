package com.cleveloper.wdwdapi.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Document(collection = "Employee")
public class Employee {

    @Transient
    public static final String SEQUENCE_NAME = "employees_sequence";

    @Id
    private long id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String firstName;
    private String lastName;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String emailId;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
                + ", emailId=" + emailId + "]";
    }

}
