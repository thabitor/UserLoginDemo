package com.example.userlogin.registration;

import com.example.userlogin.appuser.AppUser;
import com.example.userlogin.appuser.AppUserRole;
import com.example.userlogin.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class RegistrationService {

    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    public String register(RegistrationRequest request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(new AppUser(
                request.getFirstName(), request.getLastName(), request.getPassword(), request.getEmail(), AppUserRole.USER
        ));
    }
}
