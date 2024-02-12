package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.SpecialistProfileDTO;
import com.example.psds.knowledge_base.model.SpecialistProfile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelSpecialistProfileAndObjectSpecialistProfile {
    SpecialistProfile objectToModel(SpecialistProfileDTO specialistProfileDTO);
    SpecialistProfileDTO modelToObject(SpecialistProfile specialistProfile);
}
