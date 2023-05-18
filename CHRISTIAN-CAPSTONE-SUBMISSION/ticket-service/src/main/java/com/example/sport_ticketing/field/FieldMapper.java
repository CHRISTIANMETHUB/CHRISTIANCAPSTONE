package com.example.sport_ticketing.field;


public class FieldMapper {

    public Field mapToField(FieldDto fieldDto) {
        return new Field(
                fieldDto.getId(),
                fieldDto.getName(),
                fieldDto.getAddress(),
                fieldDto.getCapacity()
        );
    }

    public FieldDto mapToFieldDto(Field field) {
        return new FieldDto(
                field.getFieldId(),
                field.getName(),
                field.getAddress(),
                field.getCapacity()
        );
    }
}
