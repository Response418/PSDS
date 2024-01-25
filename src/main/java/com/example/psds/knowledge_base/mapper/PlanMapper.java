package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PlanMapper {
    com.example.psds.knowledge_base.object.Plan modelToObject(com.example.psds.knowledge_base.Model.Plan plan);
    com.example.psds.knowledge_base.Model.Plan objectToModel(com.example.psds.knowledge_base.object.Plan plan);
}
