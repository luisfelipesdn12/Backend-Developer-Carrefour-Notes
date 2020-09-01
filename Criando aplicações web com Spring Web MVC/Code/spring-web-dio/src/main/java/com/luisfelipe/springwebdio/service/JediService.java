package com.luisfelipe.springwebdio.service;

import java.util.List;

import com.luisfelipe.springwebdio.exceptions.JediNotFoundException;
import com.luisfelipe.springwebdio.model.Jedi;
import com.luisfelipe.springwebdio.repositories.JediRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JediService {
    @Autowired
    private JediRepository repository;

    public List<Jedi> getAllJedi() {
        return repository.getAllJedi();
    }

    public List<Jedi> getJediById(long id) {
        List<Jedi> JediFromRepository = repository.getJediById(id);

        if (! JediFromRepository.isEmpty()) {
            return JediFromRepository;
        } else {
            throw new JediNotFoundException();
        }
    }

    public Jedi addAndReturn(final Jedi jedi_) {
        return repository.addAndReturn(jedi_);
    }

    public List<Jedi> editJedi(long id, final Jedi editedJedi) {
        List<Jedi> jediToEdit = repository.getJediById(id);
        jediToEdit.stream()
            .forEach(j -> {
                j.setName(editedJedi.getName());
                j.setLastName(editedJedi.getLastName());
            });

        return jediToEdit;
    }

    public void deleteJedi(long id) {
        repository.getJediById(id)
                .stream()
                .forEach(j -> repository
                                .deleteJedi(j));
    }
}