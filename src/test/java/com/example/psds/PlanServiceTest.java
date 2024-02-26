package com.example.psds;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.model.Plan;
import com.example.psds.knowledge_base.model.PlanAndProfile;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.repository.PlanAndProfileRepository;
import com.example.psds.knowledge_base.repository.PlanRepository;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.service.PlanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlanServiceTest {

    @Mock
    private PlanRepository planRepository;

    @Mock
    private PlanAndProfileRepository planAndProfileRepository;

    @Mock
    private SpecialistProfileRepository specialistProfileRepository;

    @InjectMocks
    private PlanService planService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePlan() {
        Plan plan = new Plan();
        planService.createPlan(plan);
        verify(planRepository, times(1)).save(plan);
    }

    @Test
    public void testAddSpecialistProfile() {
        Long linkUsersId = 1L;
        SpecialistProfileDTO specialistProfileDTO = new SpecialistProfileDTO();
        specialistProfileDTO.setId(1L);

        Plan plan = new Plan();
        SpecialistProfile specialistProfile = new SpecialistProfile();
        specialistProfile.setId(1L);

        when(planRepository.getPlanByRelationUsersId(linkUsersId)).thenReturn(plan);
        when(specialistProfileRepository.findSpecialistProfilesById(1L)).thenReturn(specialistProfile);
        when(planAndProfileRepository.save(any(PlanAndProfile.class))).thenReturn(new PlanAndProfile());

        planService.addSpecialistProfile(linkUsersId, specialistProfileDTO);

        verify(planAndProfileRepository, times(1)).save(any(PlanAndProfile.class));
    }

    @Test
    public void testCreatePlanBylinkUsers() {
        LinkUsersDTO linkUsersDTO = new LinkUsersDTO();
        linkUsersDTO.setLinkUsersId(1L);

        when(planRepository.findPlanByRelationUsersId(1L)).thenReturn(null);
        when(planRepository.save(any(Plan.class))).thenReturn(new Plan());

        planService.createPlanBylinkUsers(linkUsersDTO);

        verify(planRepository, times(1)).save(any(Plan.class));
    }

    @Test
    public void testDeleteSpecialistProfile() {
        Long linkUsersId = 1L;
        Long specialistProfileId = 1L;

        Plan plan = new Plan();
        SpecialistProfile specialistProfile = new SpecialistProfile();
        specialistProfile.setId(1L);

        when(planRepository.getPlanByRelationUsersId(linkUsersId)).thenReturn(plan);
        when(specialistProfileRepository.findSpecialistProfilesById(specialistProfileId)).thenReturn(specialistProfile);

        planService.deleteSpecialistProfile(linkUsersId, specialistProfileId);

        verify(planAndProfileRepository, times(1)).deleteByPlanAndSpecialistProfile(plan, specialistProfile);
    }

    @Test
    public void testGetPlanByRelationUsers() {
        Long linkUserId = 1L;
        Plan plan = new Plan();

        when(planRepository.getPlanByRelationUsersId(linkUserId)).thenReturn(plan);

        Plan result = planService.getPlanByRelationUsers(linkUserId);

        assertEquals(plan, result);
    }
}
