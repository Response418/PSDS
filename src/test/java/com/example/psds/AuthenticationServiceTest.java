package com.example.psds;

import com.example.psds.personal_account.dto.MessageResponse;
import com.example.psds.personal_account.dto.authentication.SignInRequest;
import com.example.psds.personal_account.dto.authentication.SignUpRequest;
import com.example.psds.personal_account.model.User;
import com.example.psds.personal_account.repository.UserRepository;
import com.example.psds.personal_account.service.MyMapperService;
import com.example.psds.personal_account.service.SessionService;
import com.example.psds.personal_account.service.UserService;
import com.example.psds.personal_account.service.authentication.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MyMapperService myMapperService;
    @Mock
    private SessionService sessionService;
    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void signUp_WhenUserDoesNotExist_ReturnOkResponse() {
        // Arrange
        SignUpRequest request = new SignUpRequest();
        request.setPhoneNumber("1234567890");
        request.setEmail("test@example.com");

        when(userRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(myMapperService.editStringToMessageResponse(any())).thenReturn(new MessageResponse("Success"));

        // Act
        ResponseEntity<?> response = authenticationService.signUp(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).create(any(User.class));
    }

    @Test
    void signUp_WhenUserExistsWithPhoneNumber_ReturnBadRequestResponse() {
        // Arrange
        SignUpRequest request = new SignUpRequest();
        request.setPhoneNumber("1234567890");
        request.setEmail("test@example.com");

        when(userRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(true);
        when(myMapperService.editStringToMessageResponse(any())).thenReturn(new MessageResponse("User with phone number already exists"));

        // Act
        ResponseEntity<?> response = authenticationService.signUp(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(userService, never()).create(any(User.class));
    }

    @Test
    void signUp_WhenUserExistsWithEmail_ReturnBadRequestResponse() {
        // Arrange
        SignUpRequest request = new SignUpRequest();
        request.setPhoneNumber("1234567890");
        request.setEmail("test@example.com");

        when(userRepository.existsByPhoneNumber(request.getPhoneNumber())).thenReturn(false);
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);
        when(myMapperService.editStringToMessageResponse(any())).thenReturn(new MessageResponse("User with email already exists"));

        // Act
        ResponseEntity<?> response = authenticationService.signUp(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(userService, never()).create(any(User.class));
    }

    @Test
    public void testSignIn_NonExistingUser_BadRequest() {
        // Arrange
        SignInRequest request = new SignInRequest();
        request.setEmail("nonexisting@example.com");
        request.setPassword("password");
        String sessionId = "session123";

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(myMapperService.editStringToMessageResponse("Пользователя с таким email не существует"))
                .thenReturn(new MessageResponse("Пользователя с таким email не существует"));

        // Act
        ResponseEntity<?> response = authenticationService.signIn(request, sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Add more assertions for the response body if needed
        // For example, if the response body is a MessageResponse object, you can assert its properties

        verify(sessionService, never()).createSession(anyLong(), anyString());
    }

    @Test
    void signOut_ReturnOkResponse() {
        // Arrange
        String sessionId = "sessionId";

        // Act
        ResponseEntity<?> response = authenticationService.signOut(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(sessionService, times(1)).deleteSession(sessionId);
    }
}