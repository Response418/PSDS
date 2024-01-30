package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.PlanDTO;
import com.example.psds.knowledge_base.model.Plan;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MapperPlan {
    Plan objectToModel(PlanDTO planDTO);
    PlanDTO modelToObject(Plan plan);
}
