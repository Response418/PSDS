package com.example.psds;

import com.example.psds.knowledge_base.dto.MaterialDTO;
import com.example.psds.knowledge_base.mapper.ModelMaterialAndObjectMaterial;
import com.example.psds.knowledge_base.model.Material;
import com.example.psds.knowledge_base.repository.MaterialRepository;
import com.example.psds.knowledge_base.service.MaterialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ModelMaterialAndObjectMaterial modelMaterialAndObjectMaterial;

    @InjectMocks
    private MaterialService materialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMaterialList() {
        // Arrange
        List<Material> materials = new ArrayList<>();
        materials.add(new Material(1L, "Material 1", "Description 1", null));
        materials.add(new Material(2L, "Material 2", "Description 2", null));

        List<MaterialDTO> materialDTOS = new ArrayList<>();
        materialDTOS.add(new MaterialDTO(1L, "Material 1", "Description 1"));
        materialDTOS.add(new MaterialDTO(2L, "Material 2", "Description 2"));

        when(materialRepository.findAll()).thenReturn(materials);
        when(modelMaterialAndObjectMaterial.modelToObject(any(Material.class))).thenReturn(materialDTOS.get(0), materialDTOS.get(1));

        // Act
        List<MaterialDTO> result = materialService.getMaterialList();

        // Assert
        assertEquals(materialDTOS.size(), result.size());
        assertEquals(materialDTOS.get(0), result.get(0));
        assertEquals(materialDTOS.get(1), result.get(1));

        verify(materialRepository, times(1)).findAll();
        verify(modelMaterialAndObjectMaterial, times(2)).modelToObject(any(Material.class));
    }

    @Test
    void testChangeMaterial() {
        // Arrange
        MaterialDTO materialDTO = new MaterialDTO(1L, "Updated Material", "Updated Description");
        Material material = new Material(1L, "Updated Material", "Updated Description", null);

        when(modelMaterialAndObjectMaterial.objectToModel(materialDTO)).thenReturn(material);

        // Act
        materialService.changeMaterial(materialDTO);

        // Assert
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    void testDeleteMaterial() {
        // Arrange
        Long materialId = 1L;

        // Act
        materialService.deleteMaterial(materialId);

        // Assert
        verify(materialRepository, times(1)).deleteById(materialId);
    }

    @Test
    void testGetMaterialsByLessonId() {
        // Arrange
        Long lessonId = 1L;

        List<Material> materials = new ArrayList<>();
        materials.add(new Material(1L, "Material 1", "Description 1", null));
        materials.add(new Material(2L, "Material 2", "Description 2", null));

        List<MaterialDTO> materialDTOS = new ArrayList<>();
        materialDTOS.add(new MaterialDTO(1L, "Material 1", "Description 1"));
        materialDTOS.add(new MaterialDTO(2L, "Material 2", "Description 2"));

        when(materialRepository.findMaterialsByLesson_Id(lessonId)).thenReturn(materials);
        when(modelMaterialAndObjectMaterial.modelToObject(any(Material.class))).thenReturn(materialDTOS.get(0), materialDTOS.get(1));

        // Act
        List<MaterialDTO> result = materialService.getMaterialsByLessonId(lessonId);

        // Assert
        assertEquals(materialDTOS.size(), result.size());
        assertEquals(materialDTOS.get(0), result.get(0));
        assertEquals(materialDTOS.get(1), result.get(1));

        verify(materialRepository, times(1)).findMaterialsByLesson_Id(lessonId);
        verify(modelMaterialAndObjectMaterial, times(2)).modelToObject(any(Material.class));
    }
}
