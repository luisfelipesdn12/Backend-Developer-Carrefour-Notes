package com.luisfelipesdn12.dio.restful.service;

import com.luisfelipesdn12.dio.restful.dto.Soldado;

import org.springframework.stereotype.Service;

@Service
public class SoldadoService {
    
    public Soldado buscarSoldado(String CPF) {
        Soldado soldado = new Soldado();
        soldado.setCPF(CPF);
        soldado.setName("Légolas");
        soldado.setRaca("Elfo");
        soldado.setArma("Arco");
        return soldado;
    } 

    public void criarSoldado(Soldado soldado) {
        
    }
}