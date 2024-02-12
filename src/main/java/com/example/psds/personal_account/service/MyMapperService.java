package com.example.psds.personal_account.service;


import com.example.psds.personal_account.dto.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyMapperService {

    public MessageResponse editStringToMessageResponse(String message){
        String jsonString = "{\"message\":\"" + message + "\"}";
        MessageResponse messageResponse = new MessageResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            messageResponse = objectMapper.readValue(jsonString, MessageResponse.class);

        } catch (Exception e){
            e.printStackTrace();
        }
        return messageResponse;
    }
}
