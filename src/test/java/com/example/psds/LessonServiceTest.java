package com.example.psds;

import com.example.psds.knowledge_base.dto.LessonDTO;
import com.example.psds.knowledge_base.mapper.ModelLessonAndObjectLesson;
import com.example.psds.knowledge_base.model.Lesson;
import com.example.psds.knowledge_base.repository.LessonRepository;
import com.example.psds.knowledge_base.service.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private ModelLessonAndObjectLesson modelLessonAndObjectLesson;

    @InjectMocks
    private LessonService lessonService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLessonList() {
        // Arrange
        List<Lesson> lessons = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setTitle("Lesson 1");
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setTitle("Lesson 2");
        lessons.add(lesson1);
        lessons.add(lesson2);

        List<LessonDTO> expectedLessonDTOs = new ArrayList<>();
        LessonDTO lessonDTO1 = new LessonDTO();
        lessonDTO1.setId(1L);
        lessonDTO1.setTitle("Lesson 1");
        LessonDTO lessonDTO2 = new LessonDTO();
        lessonDTO2.setId(2L);
        lessonDTO2.setTitle("Lesson 2");
        expectedLessonDTOs.add(lessonDTO1);
        expectedLessonDTOs.add(lessonDTO2);

        when(lessonRepository.findAll()).thenReturn(lessons);
        when(modelLessonAndObjectLesson.modelToObject(lesson1)).thenReturn(lessonDTO1);
        when(modelLessonAndObjectLesson.modelToObject(lesson2)).thenReturn(lessonDTO2);

        // Act
        List<LessonDTO> actualLessonDTOs = lessonService.getLessonList();

        // Assert
        assertEquals(expectedLessonDTOs.size(), actualLessonDTOs.size());
        for (int i = 0; i < expectedLessonDTOs.size(); i++) {
            LessonDTO expectedLessonDTO = expectedLessonDTOs.get(i);
            LessonDTO actualLessonDTO = actualLessonDTOs.get(i);
            assertEquals(expectedLessonDTO.getId(), actualLessonDTO.getId());
            assertEquals(expectedLessonDTO.getTitle(), actualLessonDTO.getTitle());
        }

        verify(lessonRepository, times(1)).findAll();
        verify(modelLessonAndObjectLesson, times(2)).modelToObject(any(Lesson.class));
    }
}