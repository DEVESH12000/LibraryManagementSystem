package com.lms.Controller;

import com.lms.Dto.LoginUserDto;
import com.lms.Dto.UserDTO;
import com.lms.Entity.AuthenticationResponse;
import com.lms.Service.AuthenticationService;
import com.lms.Service.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtHelper jwtHelper;

    private final AuthenticationService authenticationService;

    private  final UserDetailsService userDetailsService;

    private  final JwtHelper helper;

    private  final AuthenticationManager manager;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(JwtHelper jwtHelper, AuthenticationService authenticationService, UserDetailsService userDetailsService, JwtHelper helper, AuthenticationManager manager) {
        this.jwtHelper = jwtHelper;
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
        this.helper = helper;
        this.manager = manager;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDTO userDTO) {

        AuthenticationResponse register = authenticationService.register(userDTO);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
       // AuthenticationResponse authenticate = authenticationService.authenticate(loginUserDto);
        this.doAuthenticate(loginUserDto.getUsername(), loginUserDto.getPassword());
     /**   String jwtToken = jwtHelper.generateToken(authenticate);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);**/

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserDto.getUsername());
        String token = this.helper.generateToken(userDetails);
       AuthenticationResponse response = AuthenticationResponse.builder()
                .token(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    private void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credentials Invalid !!");
        }

    }
}