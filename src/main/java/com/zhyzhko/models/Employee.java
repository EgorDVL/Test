package com.zhyzhko.models;

import com.zhyzhko.util.validators.EmployeeVerification;
import net.sf.oval.constraint.*;

import java.util.Date;

/**
 * Created by user on 03.07.17.
 */
public class Employee {

    private int employeeId;

    @Length(min = 2, max = 15, message = "Name must be min length = 2, max length = 15")
    @NotNull(message = "Can't be null")
    @NotEmpty(message = "Fill in the field Name")
    private String name;

    @Length(min = 2, max = 15, message = "Surname must be min length = 2, max length = 15")
    @NotNull(message = "Can't be null")
    @NotEmpty(message = "Fill in the field Surname")
    private String surname;

    @NotEmpty(message = "Fill in the field Experience")
    @NotNegative(message = "Experience can't be negative")
    private int experience;

    @NotEmpty(message = "Fill in the field Email")
    @CheckWith(value = EmployeeVerification.class, message = "Email already exist")
    private String email;

    @NotEmpty(message = "Incorrect date field")
    @NotNull(message = "Fill in the field Date")
    @DateRange(format = "yyyy-MM-dd", min = "1917-01-01", max = "now", message = "Incorrect date of life")
    private Date dateOfBirth;

    private int departmentId;


    public Employee() {
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", experience=" + experience +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", departmentId=" + departmentId +
                '}';
    }
}
