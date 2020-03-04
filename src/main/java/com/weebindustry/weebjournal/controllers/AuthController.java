package com.weebindustry.weebjournal.controllers;

import com.weebindustry.weebjournal.exceptions.UserRoleNotFoundException;
import com.weebindustry.weebjournal.message.request.UserLoginDTO;
import com.weebindustry.weebjournal.message.request.UserSignupDTO;
import com.weebindustry.weebjournal.message.response.JwtResponse;
import com.weebindustry.weebjournal.models.Role;
import com.weebindustry.weebjournal.models.RoleName;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.RoleRepository;
import com.weebindustry.weebjournal.repositories.UserRepository;
import com.weebindustry.weebjournal.security.jwt.JwtProvider;
import com.weebindustry.weebjournal.services.UserServiceImpl;
import com.weebindustry.weebjournal.util.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody @DTO(UserLoginDTO.class) User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public ResponseEntity<String> registerUser(@Valid @RequestBody @DTO(UserSignupDTO.class) User user, @RequestParam(required = false, defaultValue = "member") String roleParam) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already in use!", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>("Fail -> Email is already in use!", HttpStatus.BAD_REQUEST);
        }

        Set<Role> roles = new HashSet<>();
        switch (roleParam) {
            case "admin":
                Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new UserRoleNotFoundException("User role " + roleParam + " not found."));
                roles.add(adminRole);
                break;
            case "moderator":
                Role modRole = roleRepository.findByName(RoleName.ROLE_MODERATOR).orElseThrow(() -> new UserRoleNotFoundException("User role " + roleParam + " not found."));
                roles.add(modRole);
                break;
            default:
                Role memberRole = roleRepository.findByName(RoleName.ROLE_MEMBER).orElseThrow(() -> new UserRoleNotFoundException("User role " + roleParam + " not found."));
                roles.add(memberRole);
                break;
        }

        user.setRoles(roles);
        userService.create(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }
}
