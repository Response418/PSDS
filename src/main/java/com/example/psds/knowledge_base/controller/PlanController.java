package com.example.psds.knowledge_base.controller;

import com.example.psds.knowledge_base.dto.LinkUsersDTO;
import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.dto.ThemeDTO;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.model.Theme;
import com.example.psds.knowledge_base.service.GradeService;
import com.example.psds.knowledge_base.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/plans")
public class PlanController {
    private final PlanService planService;

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlanBylinkUsers(@RequestBody LinkUsersDTO linkUsersDTO){
        planService.createPlanBylinkUsers(linkUsersDTO);
    private final GradeService gradeService;

    @RequestMapping(method = RequestMethod.GET, path = "/{linkUsersId}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDTO getPlanByLinkUsersId (@PathVariable Long linkUsersId){
        PlanDTO planDTO = planService.getPlanByLinkUsersId(linkUsersId);
        List<SpecialistProfileDTO> specialistProfileDTOS = planDTO.getSpecialistProfiles();
        List<ThemeDTO> themeDTOS;
        List<LessonDTO> lessonDTOS;
        Grade grade;
        for(SpecialistProfileDTO specialistProfileDTO : specialistProfileDTOS){
            themeDTOS = specialistProfileDTO.getThemes();
            for (ThemeDTO themeDTO : themeDTOS){
                lessonDTOS = themeDTO.getLessons();
                for (LessonDTO lessonDTO : lessonDTOS){
                    grade = gradeService.getGradeModel(lessonDTO.getId(), linkUsersId);
                    if (grade!=null) {
                        lessonDTO.setGrade(grade.getValue());
                    }
                }
            }
        }
        return planDTO;
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
}
