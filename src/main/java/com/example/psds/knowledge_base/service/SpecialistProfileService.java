package com.example.psds.knowledge_base.service;

import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import com.example.psds.knowledge_base.model.ThemeAndProfile;
import com.example.psds.knowledge_base.repository.SpecialistProfileRepository;
import com.example.psds.knowledge_base.responce.LessonResponce;
import com.example.psds.knowledge_base.responce.SpecialistProfileResponce;
import com.example.psds.knowledge_base.responce.ThemeResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecialistProfileService {
    private final SpecialistProfileRepository specialistProfileRepository;

    public SpecialistProfileService(final SpecialistProfileRepository specialistProfileRepository) {
        this.specialistProfileRepository = specialistProfileRepository;
    }

    @Transactional
    public SpecialistProfileResponce getSpecialistProfileByProfessionProfileId(Long specialistProfileId) {
        SpecialistProfile specialistProfile = specialistProfileRepository.findSpecialistProfileById(specialistProfileId);
        SpecialistProfileResponce specialistProfileResponce = new SpecialistProfileResponce();
        specialistProfileResponce.setId(specialistProfile.getId());
        specialistProfileResponce.setTitle(specialistProfile.getTitle());
        specialistProfileResponce.setDescription(specialistProfile.getDescription());

        List<ThemeAndProfile> themeAndProfiles = specialistProfile.getTapSpecialistProfile();
        List<Lesson> lessons;
        for (int i=0; i<themeAndProfiles.size(); i++){
            specialistProfileResponce.getThemeResponses().add(i, new ThemeResponse());
            specialistProfileResponce.getThemeResponses().get(i).setId(themeAndProfiles.get(i).getTapTheme().getId());
            specialistProfileResponce.getThemeResponses().get(i).setTitle(themeAndProfiles.get(i).getTapTheme().getTitle());
            specialistProfileResponce.getThemeResponses().get(i).setDescription(themeAndProfiles.get(i).getTapTheme().getDescription());

            lessons = themeAndProfiles.get(i).getTapTheme().getLessons();

            for (int k=0; k<lessons.size(); k++){
                specialistProfileResponce.getThemeResponses().get(i).getLessonResponces().add(k, new LessonResponce());
                specialistProfileResponce.getThemeResponses().get(i).getLessonResponces().get(k).setId(lessons.get(k).getId());
                specialistProfileResponce.getThemeResponses().get(i).getLessonResponces().get(k).setTitle(lessons.get(k).getTitle());
                specialistProfileResponce.getThemeResponses().get(i).getLessonResponces().get(k).setDescription(lessons.get(k).getDescription());
                specialistProfileResponce.getThemeResponses().get(i).getLessonResponces().get(k).setLevel(lessons.get(k).getLevel());
            }
        }
        return specialistProfileResponce;
    }

}
