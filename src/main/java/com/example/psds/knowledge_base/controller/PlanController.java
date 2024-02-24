package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.service.GradeService;
import com.example.psds.knowledge_base.service.PlanService;
import com.example.psds.knowledge_base.service.SpecialistProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/plans")
public class PlanController {
    private final PlanService planService;
    private final GradeService gradeService;
    private final SpecialistProfileService specialistProfileService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlanBylinkUsers(@RequestBody LinkUsersDTO linkUsersDTO) {
        planService.createPlanBylinkUsers(linkUsersDTO);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{linkUsersId}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDTO getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        return planService.getPlanByLinkUsersId(linkUsersId);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{linkUsersId}/specialistProfiles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSpecialistProfile(@PathVariable Long linkUsersId, @RequestBody SpecialistProfileDTO specialistProfileDTO) {
        planService.addSpecialistProfile(linkUsersId, specialistProfileDTO);
        gradeService.addNullGrades(specialistProfileDTO, linkUsersId);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{linkUsersId}/specialistProfiles/{specialistProfileId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSpecialistProfile(@PathVariable Long linkUsersId, @PathVariable Long specialistProfileId){
        planService.deleteSpecialistProfile(linkUsersId,specialistProfileId);
    }

    @GetMapping("/{linkUsersId}/specialistProfiles/{specialistProfilesId}")
    public void subscribeSpecialistProfile(
            @PathVariable Long linkUsersId,
            @PathVariable Long specialistProfilesId
    ) {
        SpecialistProfileDTO specialistProfileDTO = specialistProfileService.getSpecialistProfileById(specialistProfilesId);
        planService.addSpecialistProfile(linkUsersId, specialistProfileDTO);
        gradeService.addNullGrades(specialistProfileDTO, linkUsersId);
    }
}
