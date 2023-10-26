package org.kainos.ea.db;

import org.apache.commons.lang3.time.DateUtils;
import org.kainos.ea.cli.Credential;
import org.kainos.ea.cli.UserRole;
import org.kainos.ea.client.AuthenticationException;
import org.kainos.ea.client.GenericActionFailedException;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class AuthDao implements IAuthenticationSource {
    @Override
    public boolean validateLogin(Credential credential) throws AuthenticationException, GenericActionFailedException {
        try {
            Connection conn = DatabaseConnector.getConnection();
            String SQL = "SELECT Password FROM `User` WHERE Username = ?";
            PreparedStatement st = conn.prepareStatement(SQL);

            st.setString(1,credential.getUsername());

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                return rs.getString("Password").equals(credential.getPassword());
            }

            throw new AuthenticationException("The provided credentials could not be authenticated");
        } catch (SQLException e) {
            throw new GenericActionFailedException(e.getMessage());
        }
    }

    @Override
    public boolean registerUser(Credential credential) throws GenericActionFailedException, AuthenticationException {
        try {
            Connection conn = DatabaseConnector.getConnection();
            String SQL = "INSERT INTO User(Username,Password,RoleID) VALUES(?,?,?)";
            PreparedStatement st = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            st.setString(1,credential.getUsername());
            st.setString(2,credential.getPassword());
            st.setInt(3,3);

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();

            if(rs.next()){
                return true;
            }

            throw new AuthenticationException("Failed to register user");
        } catch (SQLException e) {
            throw new GenericActionFailedException(e.getMessage());
        }
    }

    @Override
    public String generateToken(String username) throws GenericActionFailedException {
        try {
            String token = UUID.randomUUID().toString();
            Date expiry = DateUtils.addHours(new Date(),8);

            Connection c = DatabaseConnector.getConnection();

            String insertStatement = "INSERT INTO Token(Username,Token, Expiry) VALUES(?,?,?)";

            PreparedStatement st = c.prepareStatement(insertStatement);

            st.setString(1,username);
            st.setString(2,token);
            st.setTimestamp(3,new java.sql.Timestamp(expiry.getTime()));

            st.executeUpdate();

            return token;
        } catch (SQLException e){
            throw new GenericActionFailedException(e.getMessage());
        }
    }

    @Override
    public UserRole getRoleIdForToken(String token) throws GenericActionFailedException,AuthenticationException {
        try{
            Connection conn = DatabaseConnector.getConnection();
            String SQL = "SELECT RoleID, Expiry FROM `User` JOIN `Token` using (Username) WHERE Token = ?";

            PreparedStatement st = conn.prepareStatement(SQL);

            st.setString(1,token);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Timestamp expiry = rs.getTimestamp("Expiry");

                if(expiry.after(new Date())) {
                    //Adjust for id to array index
                    int roleID = rs.getInt(1) - 1;
                    return UserRole.values()[roleID];
                } else {
                    throw new AuthenticationException("The token given has expired");
                }
            }

            throw new AuthenticationException("The token given has no valid role assigned");
        } catch(SQLException e){
            throw new GenericActionFailedException(e.getMessage());
        }
    }
}
