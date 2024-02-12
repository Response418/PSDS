package com.example.psds.knowledge_base.response;

import com.example.psds.knowledge_base.dto.GradeDTO;
import com.example.psds.knowledge_base.dto.GradeRequestDTO;
import com.example.psds.knowledge_base.mapper.MapperGrade;
import com.example.psds.knowledge_base.model.Grade;
import com.example.psds.knowledge_base.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GradeResponseBuilder {
    private final GradeService gradeService;
    private final MapperGrade mapperGrade;

    public ResponseEntity<?> updateGrade(Long lessonId, Long linkUserId, GradeRequestDTO gradeRequestDTO){
        Integer newGrade = gradeRequestDTO.getGrade();
        Grade grade = gradeService.updateGrade(lessonId, linkUserId, newGrade);
        GradeDTO gradeDTO = mapperGrade.modelToObject(grade);

        return new ResponseEntity<>(gradeDTO, HttpStatus.OK);
    }
}
