package com.example.sport_ticketing.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        Optional<Field> field = fieldService.findById(id);
        if(field.isPresent()) {
            return ResponseEntity.ok(field.get());
        }
        throw new NoSuchElementException();
    }

    @GetMapping
    public ResponseEntity<Page<Field>> getAllFields(Pageable page, @RequestHeader("Authorization") String token) {
        Page<Field> fields = fieldService.findAll(page);
        return ResponseEntity.ok(fields);
    }

    @PostMapping
    public ResponseEntity<Field> createField(@RequestBody FieldDto field, @RequestHeader("Authorization") String token) {
        Field createdField = fieldService.save(field);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdField);
    }

    @PutMapping("/update")
    public ResponseEntity<Field> updateField(@RequestBody FieldDto field, @RequestHeader("Authorization") String token) {
        Field updatedField = fieldService.update(field);
        return ResponseEntity.ok(updatedField);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
