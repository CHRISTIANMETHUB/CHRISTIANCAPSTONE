package com.example.sport_ticketing.field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {



    private FieldMapper mapper = new FieldMapper();
    @Test
    public void testGettersAndSetters() {
        Long fieldId = 1L;
        String name = "Field A";
        String address = "123 Main St";
        Integer capacity = 1000;

        Field field = new Field();
        field.setFieldId(fieldId);
        field.setName(name);
        field.setAddress(address);
        field.setCapacity(capacity);

        assertEquals(fieldId, field.getFieldId());
        assertEquals(name, field.getName());
        assertEquals(address, field.getAddress());
        assertEquals(capacity, field.getCapacity());
    }

    @Test
    public void testConstructor() {
        Long fieldId = 1L;
        String name = "Field A";
        String address = "123 Main St";
        Integer capacity = 1000;

        Field field = new Field(fieldId, name, address, capacity);

        assertEquals(fieldId, field.getFieldId());
        assertEquals(name, field.getName());
        assertEquals(address, field.getAddress());
        assertEquals(capacity, field.getCapacity());
    }

    @Test
    public void testDtoGetterAndSetter() {
        // Arrange
        Long id = 1L;
        String name = "Field 1";
        String address = "123 Main Street";
        Integer capacity = 10;

        FieldDto fieldDto = new FieldDto();
        fieldDto.setId(id);
        fieldDto.setName(name);
        fieldDto.setAddress(address);
        fieldDto.setCapacity(capacity);

        // Act and Assert
        assertEquals(id, fieldDto.getId());
        assertEquals(name, fieldDto.getName());
        assertEquals(address, fieldDto.getAddress());
        assertEquals(capacity, fieldDto.getCapacity());
    }

    @Test
    public void testDtoConstructor() {
        // Arrange
        Long id = 1L;
        String name = "Field 1";
        String address = "123 Main Street";
        Integer capacity = 10;

        // Act
        FieldDto fieldDto = new FieldDto(id, name, address, capacity);

        // Assert
        assertEquals(id, fieldDto.getId());
        assertEquals(name, fieldDto.getName());
        assertEquals(address, fieldDto.getAddress());
        assertEquals(capacity, fieldDto.getCapacity());
    }

    @Test
    public void testMapToField() {
        FieldDto dto = new FieldDto();
        dto.setId(1L);
        dto.setName("Field A");
        dto.setAddress("123 Main St.");
        dto.setCapacity(100);

        Field field = mapper.mapToField(dto);

        assertNotNull(field);
        assertEquals(dto.getId(), field.getFieldId());
        assertEquals(dto.getName(), field.getName());
        assertEquals(dto.getAddress(), field.getAddress());
        assertEquals(dto.getCapacity(), field.getCapacity());
    }

    @Test
    public void testMapToFieldDto() {
        Field field = new Field();
        field.setFieldId(1L);
        field.setName("Field A");
        field.setAddress("123 Main St.");
        field.setCapacity(100);

        FieldDto dto = mapper.mapToFieldDto(field);

        assertNotNull(dto);
        assertEquals(field.getFieldId(), dto.getId());
        assertEquals(field.getName(), dto.getName());
        assertEquals(field.getAddress(), dto.getAddress());
        assertEquals(field.getCapacity(), dto.getCapacity());
    }
}
