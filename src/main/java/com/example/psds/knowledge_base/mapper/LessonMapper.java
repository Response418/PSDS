package com.example.psds.knowledge_base.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")

public interface LessonMapper {
    com.example.psds.knowledge_base.dto.Lesson modelToObject(com.example.psds.knowledge_base.model.Lesson lesson);
    com.example.psds.knowledge_base.model.Lesson objectToModel(com.example.psds.knowledge_base.dto.Lesson lesson);
}
