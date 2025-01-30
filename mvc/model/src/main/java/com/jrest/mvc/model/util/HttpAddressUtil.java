package com.jrest.mvc.model.util;

import com.jrest.mvc.model.HttpRequest;
import lombok.experimental.UtilityClass;

import java.net.InetSocketAddress;

@UtilityClass
public class HttpAddressUtil {

    public boolean restrict(HttpRequest request, InetSocketAddress[] available) {
        for (InetSocketAddress availableInetAddress : available) {
            if (!request.getLocalAddress().equals(availableInetAddress) && !request.getRemoteAddress().equals(availableInetAddress)) {
                return false;
            }
        }
        return true;
    }

    public boolean restrictIPs(HttpRequest request, String[] availableIPs) {
        for (String availableInetAddress : availableIPs) {
            if (!request.getLocalAddress().getHostString().equals(availableInetAddress) && !request.getRemoteAddress().getHostString().equals(availableInetAddress)) {
                return false;
            }
        }
        return true;
    }

    public boolean restrictDomains(HttpRequest request, String[] availableDomains) {
        for (String availableInetAddress : availableDomains) {
            if (!request.getLocalAddress().getHostName().equals(availableInetAddress) && !request.getRemoteAddress().getHostName().equals(availableInetAddress)) {
                return false;
            }
        }
        return true;
    }
}
