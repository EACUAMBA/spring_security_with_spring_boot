package com.mafurrasoft.springsecurity.auth;

import com.google.common.collect.Lists;
import com.mafurrasoft.springsecurity.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fakerepo")
public class FakeApplicationUserDao implements ApplicationUserDao {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDao(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream().filter((u)-> u.getUsername().equals(username))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers(){
        List<ApplicationUser> users = Lists.newArrayList(
                new ApplicationUser(
                        ApplicationUserRole.STUDENT.getGrantedAuthoritySet(),
                        "ana",
                        this.passwordEncoder.encode("senha2021"),
                        true,
                        true,
                        true,
                        true
                ),
        new ApplicationUser(
                ApplicationUserRole.ADMIN.getGrantedAuthoritySet(),
                "maria",
                this.passwordEncoder.encode("senha2021"),
                true,
                true,
                true,
                true
        ),
        new ApplicationUser(
                ApplicationUserRole.ADMINTRAINEE.getGrantedAuthoritySet(),
                "gilda",
                this.passwordEncoder.encode("senha2021"),
                true,
                true,
                true,
                true
        )
        );

        return users;
    }
}
