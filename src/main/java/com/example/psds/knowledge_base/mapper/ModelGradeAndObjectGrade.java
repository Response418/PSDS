package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.GradeDTO;
import com.example.psds.knowledge_base.model.Grade;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelGradeAndObjectGrade {
    GradeDTO modelToObject (Grade grade);
    Grade objectToModel (GradeDTO gradeDTO);
}
