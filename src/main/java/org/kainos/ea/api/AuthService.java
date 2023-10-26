package org.kainos.ea.api;

import org.kainos.ea.cli.Credential;
import org.kainos.ea.cli.UserRole;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.client.GenericActionFailedException;
import org.kainos.ea.db.IAuthenticationSource;

public class AuthService {
    private final IAuthenticationSource authenticationSource;

    public AuthService(IAuthenticationSource authenticationSource){
        this.authenticationSource = authenticationSource;
    }

    public String login(Credential login) throws AuthenticationException, GenericActionFailedException {
        if(authenticationSource.validateLogin(login)){
            return authenticationSource.generateToken(login.getUsername());
        }
        return null;
    }

    public boolean register(Credential login) throws AuthenticationException, GenericActionFailedException {
        return authenticationSource.registerUser(login);
    }

    public UserRole getTokenRole(String token) throws AuthenticationException, GenericActionFailedException {
        return authenticationSource.getRoleIdForToken(token);
    }

    public boolean doesTokenHaveRole(String token,UserRole role) throws GenericActionFailedException, AuthenticationException {
        return authenticationSource.getRoleIdForToken(token) == role;
    }
}
