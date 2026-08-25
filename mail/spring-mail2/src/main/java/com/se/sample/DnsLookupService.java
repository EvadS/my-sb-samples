package com.se.sample;

import org.springframework.stereotype.Service;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class DnsLookupService {

    public String getMxRecord(String email) {
        try {
            String domain = email.substring(email.indexOf("@") + 1);
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");

            InitialDirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            if (attr == null || attr.size() == 0) {
                return domain; // Fallback to raw domain if no MX record is found
            }

            // MX records have format: "10 ://example.com."
            String mxRecord = (String) attr.get(0);
            String[] parts = mxRecord.split(" ");
            String host = parts.length > 1 ? parts[1] : parts[0];

            // Strip trailing dot if present
            if (host.endsWith(".")) {
                host = host.substring(0, host.length() - 1);
            }
            return host;
        } catch (Exception e) {
            throw new RuntimeException("Failed to resolve MX record for email: " + email, e);
        }
    }
}
