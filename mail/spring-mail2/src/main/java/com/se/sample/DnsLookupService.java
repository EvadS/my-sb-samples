package com.se.sample;

import org.springframework.stereotype.Service;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Service
public class DnsLookupService {

    public String getMxRecord(String emailOrDomain) {
        try {
            String domain = emailOrDomain.contains("@") ? emailOrDomain.substring(emailOrDomain.indexOf("@") + 1) : emailOrDomain;
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");

            InitialDirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            if (attr == null || attr.size() == 0) {
                return domain; // fallback to domain itself
            }

            // MX records typically like: "10 mx.example.com."
            String mxRecord = (String) attr.get(0);
            String[] parts = mxRecord.split(" ");
            String host = parts.length > 1 ? parts[1] : parts[0];

            if (host.endsWith(".")) {
                host = host.substring(0, host.length() - 1);
            }
            return host;
        } catch (Exception e) {
            throw new RuntimeException("Failed to resolve MX record for: " + emailOrDomain, e);
        }
    }
}

