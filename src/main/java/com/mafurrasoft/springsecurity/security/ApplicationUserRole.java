package com.mafurrasoft.springsecurity.security;

import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Set;

import static com.mafurrasoft.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE));


    private Set<ApplicationUserPermission> permissionSet;
    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
