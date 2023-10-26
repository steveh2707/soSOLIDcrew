package org.kainos.ea.db;

import org.kainos.ea.cli.Credential;
import org.kainos.ea.cli.UserRole;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.client.GenericActionFailedException;

public interface IAuthenticationSource {
    boolean validateLogin(Credential credential) throws AuthenticationException, GenericActionFailedException;
    boolean registerUser(Credential credential) throws AuthenticationException, GenericActionFailedException;
    String generateToken(String username) throws GenericActionFailedException;
    UserRole getRoleIdForToken(String token) throws AuthenticationException, GenericActionFailedException;
}
