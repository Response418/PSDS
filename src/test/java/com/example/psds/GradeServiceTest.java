package com.example.psds;

import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.repository.GradeRepository;
import com.example.psds.knowledge_base.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGradeModel() {
        Long lessonId = 1L;
        Long relationUsersId = 1L;
        Grade expectedGrade = new Grade();
        when(gradeRepository.findGradeByLesson_IdAndRelationUsersId(lessonId, relationUsersId)).thenReturn(expectedGrade);

        Grade result = gradeService.getGradeModel(lessonId, relationUsersId);

        assertEquals(expectedGrade, result);
    }

    @Test
    void testUpdateGrade() {
        Long lessonId = 1L;
        Long linkUserId = 1L;
        Integer newGrade = 90;
        Grade grade = new Grade();
        when(gradeRepository.getGradeByLessonIdAndRelationUsersId(lessonId, linkUserId)).thenReturn(grade);

        Grade result = gradeService.updateGrade(lessonId, linkUserId, newGrade);

        assertEquals(newGrade, result.getValue());
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void testGetGradeByLessonAndLink() {
        Long lessonId = 1L;
        Long linkUserId = 1L;
        Grade expectedGrade = new Grade();
        when(gradeRepository.getGradeByLessonIdAndRelationUsersId(lessonId, linkUserId)).thenReturn(expectedGrade);

        Grade result = gradeService.getGradeByLessonAndLink(lessonId, linkUserId);

        assertEquals(expectedGrade, result);
    }
}
