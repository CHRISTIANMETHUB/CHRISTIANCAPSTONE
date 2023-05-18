package com.example.sport_ticketing.field;

import com.example.sport_ticketing.exception.DuplicateEntityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FieldServiceTest {

    @Mock
    private FieldRepository fieldRepository;

    @InjectMocks
    private FieldService fieldService;

    @Test
    public void testFindAll() {
        // Arrange
        List<Field> fields = new ArrayList<>();
        fields.add(new Field(1L, "Field A", "Location A", 10));
        fields.add(new Field(2L, "Field B", "Location B", 10));
        Page<Field> expectedPage = new PageImpl<>(fields);

        Pageable pageable = PageRequest.of(0, 10);
        when(fieldRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Field> actualPage = fieldService.findAll(pageable);

        // Assert
        assertEquals(expectedPage, actualPage);
        verify(fieldRepository).findAll(pageable);
    }

    @Test
    public void testFindById_ExistingId_ReturnsField() {
        // Arrange
        Long id = 1L;
        Field expectedField = new Field(id, "Field A", "Location A", 10);

        when(fieldRepository.findById(id)).thenReturn(Optional.of(expectedField));

        // Act
        Optional<Field> actualField = fieldService.findById(id);

        // Assert
        assertTrue(actualField.isPresent());
        assertEquals(expectedField, actualField.get());
        verify(fieldRepository).findById(id);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindById_NonExistingId_ThrowsNoSuchElementException() {
        // Arrange
        Long id = 1L;

        when(fieldRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        fieldService.findById(id);

        // Assert (exception expected)
    }

    @Test
    public void testUpdate_ExistingField_ReturnsUpdatedField() {
        // Arrange
        Long id = 1L;
        FieldDto fieldDto = new FieldDto(id, "Field A Updated", "Location A Updated", 10);
        Field expectedField = mock(Field.class);
        when(fieldRepository.findById(id)).thenReturn(Optional.of(new Field(id, "Field A", "Location A", 10)));
        when(fieldRepository.save(any(Field.class))).thenReturn(expectedField);
        Field actualField = fieldService.update(fieldDto);

        // Assert
        assertEquals(expectedField, actualField);
        verify(fieldRepository).findById(id);
    }


    @Test(expected = NoSuchElementException.class)
    public void testUpdate_NonExistingField_ThrowsNoSuchElementException() {
        // Arrange
        Long id = 1L;
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(id);
        when(fieldRepository.findById(id)).thenReturn(Optional.empty());

        fieldService.update(fieldDto);
    }

    @Test
    public void testSave_NewField_ReturnsSavedField() {
        // Arrange
        FieldDto fieldDto = new FieldDto(null, "Field A", "Location A", 10);
        Field expectedField = new Field(1L, fieldDto.getName(), fieldDto.getAddress(), 10);

        when(fieldRepository.save(any(Field.class))).thenReturn(expectedField);

        // Act
        Field actualField = fieldService.save(fieldDto);

        // Assert
        assertEquals(expectedField, actualField);
        verify(fieldRepository).save(any(Field.class));
    }


    @Test
    public void testSave() {
        // Arrange
        Long id = 1L;
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(id);
        when(fieldRepository.findById(id)).thenReturn(Optional.empty());
        when(fieldRepository.save(any(Field.class))).thenAnswer(invocation -> {
            Field field = invocation.getArgument(0);
            field.setFieldId(id);
            return field;
        });

        // Act
        Field result = fieldService.save(fieldDto);

        // Assert
        assertEquals(id, result.getFieldId());
    }

    @Test(expected = DuplicateEntityException.class)
    public void testSave_ExistingField_ThrowsDuplicateEntityException() {
        // Arrange
        Long id = 1L;
        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(id);
        when(fieldRepository.findById(id)).thenThrow(DuplicateEntityException.class);

        // Act
        fieldService.save(fieldDto);

        // Assert
        // Expects an exception
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long id = 1L;

        // Act
        fieldService.deleteById(id);

        // Assert
        verify(fieldRepository).deleteById(id);
    }
}
