package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlanMapper {
    com.example.psds.knowledge_base.dto.Plan modelToObject(com.example.psds.knowledge_base.model.Plan plan);
    com.example.psds.knowledge_base.model.Plan objectToModel(com.example.psds.knowledge_base.dto.Plan plan);
}
