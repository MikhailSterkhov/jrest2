package com.jrest.http.server.test.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserEntity {

    private final int id;

    private final String firstName;
    private final String lastName;

    private final UserJobInfo jobInfo;
}
