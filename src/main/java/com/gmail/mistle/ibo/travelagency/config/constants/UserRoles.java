package com.gmail.mistle.ibo.travelagency.config.constants;

/**
 * List of available user roles
 */
public enum UserRoles {
    CUSTOMER("CUSTOMER"), MANAGER("MANAGER"), ADMIN("ADMIN");

    private final String roleName;

    UserRoles(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
