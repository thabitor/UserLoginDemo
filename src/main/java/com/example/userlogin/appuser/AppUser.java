package com.example.userlogin.appuser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @SequenceGenerator(name = "student_sequence",
    sequenceName = "student_sequence",
    allocationSize = 1)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled;

    public AppUser(String name, String username, String password, String email, AppUserRole appUserRole, Boolean locked, Boolean enabled) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.appUserRole = appUserRole;
        this.locked = locked;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppUser appUser = (AppUser) o;
        return id != null && Objects.equals(id, appUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
