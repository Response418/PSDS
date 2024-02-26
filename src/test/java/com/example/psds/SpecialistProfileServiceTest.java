package com.example.psds;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.mapper.ModelSpecialistProfileAndObjectSpecialistProfile;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpecialistProfileServiceTest {

    @Mock
    private SpecialistProfileRepository specialistProfileRepository;

    @Mock
    private ModelSpecialistProfileAndObjectSpecialistProfile modelSpecialistProfileAndObjectSpecialistProfile;

    @InjectMocks
    private SpecialistProfileService specialistProfileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSpecialistProfileList() {
        // Arrange
        List<SpecialistProfile> specialistProfiles = new ArrayList<>();
        SpecialistProfile specialistProfile = new SpecialistProfile();
        specialistProfiles.add(specialistProfile);

        List<SpecialistProfileDTO> specialistProfileDTOS = new ArrayList<>();
        SpecialistProfileDTO specialistProfileDTO = new SpecialistProfileDTO();
        specialistProfileDTOS.add(specialistProfileDTO);

        when(specialistProfileRepository.findAll()).thenReturn(specialistProfiles);
        when(modelSpecialistProfileAndObjectSpecialistProfile.modelToObject(specialistProfile)).thenReturn(specialistProfileDTO);

        // Act
        List<SpecialistProfileDTO> result = specialistProfileService.getSpecialistProfileList();

        // Assert
        assertEquals(specialistProfileDTOS, result);
        verify(specialistProfileRepository, times(1)).findAll();
        verify(modelSpecialistProfileAndObjectSpecialistProfile, times(1)).modelToObject(specialistProfile);
    }
}
