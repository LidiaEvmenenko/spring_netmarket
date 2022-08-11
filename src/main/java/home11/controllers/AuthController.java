package home11.controllers;

import home11.dto.AuthRequest;
import home11.dto.AuthResponse;
import home11.dto.RegistrationRequest;
import home11.exceptions.MarketError;
import home11.services.UserService;
import home11.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
     //   System.out.println("userDetails = " +userDetails);
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token, null));
    }
    @PostMapping("/auth/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationRequest registrationRequest) {
      //  try {
            if (userService.getUserByUsername(registrationRequest.getUsername()) == null){
                userService.registerNewUserAccount(registrationRequest);
                return ResponseEntity.ok(new AuthResponse(null, "OK"));
            }
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));
//            return new ResponseEntity<>(new MarketError("Incorrect username or password"), HttpStatus.UNAUTHORIZED);
      //  } catch (BadCredentialsException ex) {

        return new ResponseEntity<>(new MarketError("Login " + registrationRequest.getUsername() +
                " already exists"), HttpStatus.UNAUTHORIZED);

     //   }

    }
}
