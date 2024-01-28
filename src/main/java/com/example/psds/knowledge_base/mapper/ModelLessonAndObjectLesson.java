package com.example.psds.knowledge_base.mapper;

import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.model.Lesson;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModelLessonAndObjectLesson {
    Lesson objectToModel(LessonDTO lessonDTO);
    LessonDTO modelToObject(Lesson lesson);
}
