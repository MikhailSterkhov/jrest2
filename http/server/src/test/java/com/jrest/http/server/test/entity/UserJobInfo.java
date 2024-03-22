package com.jrest.http.server.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserJobInfo {

    private final String company;
    private final String website;

    private final String profession;

    private final int salary;
}
