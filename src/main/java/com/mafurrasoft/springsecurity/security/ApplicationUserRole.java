package com.mafurrasoft.springsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mafurrasoft.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, COURSE_READ, COURSE_WRITE)),
    ADMINTRAINEE(Sets.newHashSet(STUDENT_READ, COURSE_READ));


    private Set<ApplicationUserPermission> permissionSet;
    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthoritySet(){
        Set<SimpleGrantedAuthority> permissions = getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return permissions;


    }
}
