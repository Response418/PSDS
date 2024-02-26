package com.example.psds.personal_account.service.authentication;

import com.example.psds.personal_account.dto.MessageResponse;
import com.example.psds.personal_account.dto.authentication.JwtResponse;
import com.example.psds.personal_account.dto.authentication.SignUpRequest;
import com.example.psds.personal_account.dto.authentication.SignInRequest;
import com.example.psds.personal_account.model.ERole;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.service.MyMapperService;
import com.example.psds.personal_account.service.SessionService;
import com.example.psds.personal_account.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final MyMapperService myMapperService;
    private final SessionService sessionService;


    public ResponseEntity<?> signUp(SignUpRequest request) {
        MessageResponse messageResponse;
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            messageResponse = myMapperService.
                    editStringToMessageResponse("Пользователь с таким номером телефона уже существует");
            return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            messageResponse = myMapperService.
                    editStringToMessageResponse("Пользователь с таким email уже существует");
            return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
        }

        var user = User.builder()
                .lastName(request.getLastName())
                .firstName(request.getFirstName())
                .fatherName(request.getFatherName())
                .city(request.getCity())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(ERole.ROLE_STUDENT)
                .build();
        userService.create(user);

        messageResponse = myMapperService.editStringToMessageResponse("Регистрация прошла успешно");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


    public ResponseEntity<?> signIn(SignInRequest request, String sessionId) {
        if(userRepository.findByEmail(request.getEmail()).isEmpty()){
            MessageResponse messageResponse = myMapperService.
                    editStringToMessageResponse("Пользователя с таким email не существует");
            return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        var jwt = jwtService.generateToken(user);

        Long userId = userRepository.findByEmail(request.getEmail()).orElseThrow().getId();
        sessionService.createSession(userId, sessionId);

        return new ResponseEntity<>(new JwtResponse(jwt), HttpStatus.OK);
    }

    public ResponseEntity<?> signOut(String sessionId) {
        sessionService.deleteSession(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
