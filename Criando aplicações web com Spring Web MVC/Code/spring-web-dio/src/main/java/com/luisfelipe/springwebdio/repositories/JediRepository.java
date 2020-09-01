package com.luisfelipe.springwebdio.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.luisfelipe.springwebdio.model.Jedi;

import org.springframework.stereotype.Repository;

@Repository
public class JediRepository {
    private final List<Jedi> jedi;

    private List<Long> idsList = new ArrayList<>();

    public JediRepository() {
		jedi = new ArrayList<>();
        jedi.add(new Jedi("Anaquin", "Skywalker"));
	}

    public List<Jedi> getAllJedi() {
        return this.jedi;
    }
    public void add(final Jedi jedi_) {
        this.jedi.add(jedi_);
    }

    public Jedi addAndReturn(final Jedi jedi_) {
        this.jedi.add(jedi_);
        return(jedi_);
    }

    public List<Jedi> getJediById(long id) {
        return jedi.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
    }

    public void deleteJedi(Jedi jediToBeDeleted) {
        jedi.remove(jediToBeDeleted);
    }

    /** Generic Getters and Setters */

    public List<Long> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Long> idsList) {
        this.idsList = idsList;
    }
}