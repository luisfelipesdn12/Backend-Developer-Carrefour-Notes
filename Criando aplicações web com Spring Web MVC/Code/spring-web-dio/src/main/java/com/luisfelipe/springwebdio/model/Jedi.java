package com.luisfelipe.springwebdio.model;

import java.util.concurrent.ThreadLocalRandom;

import javax.validation.constraints.NotBlank;

public class Jedi {
    @NotBlank
    private String name, lastName;

    private long id;

    long retunNewId() {
        long idPretender = ThreadLocalRandom
                        .current()
                        .nextLong(1, 9999999999999999L);

        return idPretender;
    }

    public Jedi() {
        this.id = retunNewId();
    }

    public Jedi (final String name, final String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.id = retunNewId();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
    }
}