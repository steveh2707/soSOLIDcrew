package org.kainos.ea.cli;

public enum UserRole {
    MANAGEMENT(1),
    SALES(2),
    HR(3);

    private final int userRoleId;
    UserRole(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    public int getUserRoleId() {
        return this.userRoleId;
    }
}
