package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface SpecialistProfileMapper {
    com.example.psds.knowledge_base.object.SpecialistProfile modelToObject(com.example.psds.knowledge_base.model.SpecialistProfile specialistProfile);
    com.example.psds.knowledge_base.model.SpecialistProfile objectToModel(com.example.psds.knowledge_base.object.SpecialistProfile specialistProfile);
}