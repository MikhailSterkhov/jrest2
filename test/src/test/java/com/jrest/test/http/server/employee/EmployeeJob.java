package com.jrest.test.http.server.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class EmployeeJob {

    private final String company;
    private final String website;

    private final String profession;

    private final int salary;
}
