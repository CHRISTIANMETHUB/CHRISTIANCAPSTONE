package com.example.sport_ticketing.field;

import com.example.sport_ticketing.exception.DuplicateEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FieldService {

    @Autowired
    private FieldRepository fieldRepository;

    public Page<Field> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    public Optional<Field> findById(Long id) {
        Optional<Field> field = fieldRepository.findById(id);
        if (field.isPresent()) {
            return field;
        }
        throw new NoSuchElementException("Field not found");
    }

    public Field update(FieldDto fieldObject) {
        Optional<Field> field = fieldRepository.findById(fieldObject.getId());
        if (field.isPresent()) {
            fieldObject.setId(fieldObject.getId());
            FieldMapper fieldMapper = new FieldMapper();
            Field newField = fieldMapper.mapToField(fieldObject);
            return fieldRepository.save(newField);
        }
        throw new NoSuchElementException("Field not found");
    }

    public Field save(FieldDto field) {
        if (field.getId() != null) {
            Optional<Field> x = fieldRepository.findById(field.getId());
            if (x.isPresent()) {
                throw new DuplicateEntityException("Duplicate Field");
            }
        }
        FieldMapper fieldMapper = new FieldMapper();
        Field newField = fieldMapper.mapToField(field);
        return fieldRepository.save(newField);
    }

    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }

}