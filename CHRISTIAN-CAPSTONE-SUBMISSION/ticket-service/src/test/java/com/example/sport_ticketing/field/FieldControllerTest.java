package com.example.sport_ticketing.field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class FieldControllerTest {

    @Mock
    private FieldService fieldService;

    @InjectMocks
    private FieldController fieldController;

    @Test
    public void testGetFieldById() throws Exception {
        Long id = 1L;
        Field field = new Field();
        field.setFieldId(id);
        when(fieldService.findById(id)).thenReturn(Optional.of(field));

        ResponseEntity<Field> response = fieldController.getFieldById(id, "token");

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(field));
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFieldByIdWithNoSuchElementException() throws NoSuchElementException {
        Long id = 1L;
        when(fieldService.findById(id)).thenReturn(Optional.empty());

        fieldController.getFieldById(id, "token");
    }

    @Test
    public void testGetAllFields() throws Exception {
        Pageable pageable = Mockito.mock(Pageable.class);;
        List<Field> fields = Arrays.asList(new Field(), new Field(), new Field());
        Page<Field> pageFields = new PageImpl<>(fields, pageable, fields.size());
        when(fieldService.findAll(pageable)).thenReturn(pageFields);

        ResponseEntity<Page<Field>> response = fieldController.getAllFields(pageable, "token");

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(pageFields));
    }

    @Test
    public void testCreateField() throws Exception {
        FieldDto fieldDto = new FieldDto();
        Field field = new Field();
        field.setFieldId(1L);
        when(fieldService.save(fieldDto)).thenReturn(field);

        ResponseEntity<Field> response = fieldController.createField(fieldDto, "token");

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(field));
    }

    @Test
    public void testUpdateField() throws Exception {
        FieldDto fieldDto = new FieldDto();
        Field field = new Field();
        field.setFieldId(1L);
        when(fieldService.update(fieldDto)).thenReturn(field);

        ResponseEntity<Field> response = fieldController.updateField(fieldDto, "token");

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(field));
    }

    @Test
    public void testDeleteField() throws Exception {
        Long id = 1L;

        ResponseEntity<Void> response = fieldController.deleteField(id);

        verify(fieldService, times(1)).deleteById(id);
        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }
}