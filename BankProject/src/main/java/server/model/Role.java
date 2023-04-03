package server.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, BANK, CBANK;

    @Override
    public String getAuthority() {
        return name();
    }
}
