package com.jrest.mvc.model;

import com.jrest.mvc.model.util.UrlPathUtil;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpRequest {

    private HttpMethod method;

    private String url;
    private Attributes attributes;

    private Content content;
    private Headers headers;

    public String getPath() {
        return UrlPathUtil.stripPath(url);
    }
}
