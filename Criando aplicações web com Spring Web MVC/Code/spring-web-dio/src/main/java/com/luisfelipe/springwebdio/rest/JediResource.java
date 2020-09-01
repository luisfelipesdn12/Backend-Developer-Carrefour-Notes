package com.luisfelipe.springwebdio.rest;

import java.util.List;

import javax.validation.Valid;

import com.luisfelipe.springwebdio.model.Jedi;
import com.luisfelipe.springwebdio.service.JediService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JediResource {

    @Autowired
    private JediService service;
    
    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi() {
        return service.getAllJedi();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<List<Jedi>> getJedi(@PathVariable("id") long id) {
        return ResponseEntity.ok(service.getJediById(id));
    }
    
    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody final Jedi jedi) {
        return service.addAndReturn(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public List<Jedi> editJedi(@PathVariable long id, @Valid @RequestBody final Jedi editedJedi) {
        return service.editJedi(id, editedJedi);
    }

    @DeleteMapping("/api/jedi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJedi(@PathVariable long id) {
        service.deleteJedi(id);
    }
}