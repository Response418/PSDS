package com.example.psds;

import com.example.psds.personal_account.dto.MessageResponse;
import com.example.psds.personal_account.service.MyMapperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MyMapperServiceTest {

    private MyMapperService myMapperService;

    @BeforeEach
    public void setUp() {
        myMapperService = new MyMapperService();
    }

    @Test
    public void testEditStringToMessageResponse() {
        // Arrange
        String message = "Hello, World!";

        // Act
        MessageResponse messageResponse = myMapperService.editStringToMessageResponse(message);

        // Assert
        Assertions.assertEquals(message, messageResponse.getMessage());
    }
}
