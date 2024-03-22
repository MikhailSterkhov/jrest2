package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class HttpBrowserRequestContext {

    private final HttpRequest httpRequest;
    private final String requestedAttachmentName;
}
