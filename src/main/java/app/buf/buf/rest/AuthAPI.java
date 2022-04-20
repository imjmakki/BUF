package app.buf.buf.rest;

import app.buf.buf.dao.*;
import app.buf.buf.dto.*;
import app.buf.buf.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthAPI {

    private AuthenticationManager authenticationManager;
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthAPI(AuthenticationManager authenticationManager, UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody Login login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUsernameOrEmail(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUp signUp) {

        // add check for username exists in a DB
        if (userDAO.existsByUsername(signUp.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if (userDAO.existsByEmail(signUp.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setUsername(signUp.getUsername());
        user.setEmail(signUp.getEmail());
        user.setPassword(passwordEncoder.encode(signUp.getPassword()));

        Role roles = roleDAO.findByName("ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userDAO.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
