package com.zhyzhko.models;

import com.zhyzhko.util.validators.DepartmentVerification;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

/**
 * Created by user on 03.07.17.
 */
public class Department {

    private int departmentId;

    @Length(min = 2, max = 15, message = "min length = 2, max length = 15")
    @NotNull(message = "Can't be null")
    @NotEmpty(message = "Fill in the field Name")
    @CheckWith(value = DepartmentVerification.class, message = "Department already exist")
    private String name;

    public Department() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }
}
