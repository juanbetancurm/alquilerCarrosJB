/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Gama;
import com.example.demo.Repositorio.GamaRepositorio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class GamaServicio {
    @Autowired
     private GamaRepositorio gamaRepository;

    public List<Gama> getAll() {
        return gamaRepository.getAll();
    }

    public Optional<Gama> getGama(int gamaId) {
        return gamaRepository.getGama(gamaId);
    }

    public Gama save(Gama gama) {
        if (gama.getIdGama() == null) {
            return gamaRepository.save(gama);
        } else {
            Optional<Gama> gama1 = gamaRepository.getGama(gama.getIdGama());
            if (gama1.isEmpty()) {
                return gamaRepository.save(gama);
            } else {
                return gama;
            }
        }
    }
    
    public Gama update(Gama gama){
        if(gama.getIdGama()!=null){
            Optional<Gama>g=gamaRepository.getGama(gama.getIdGama());
            if(!g.isEmpty()){
                if(gama.getDescription()!=null){
                    g.get().setDescription(gama.getDescription());
                }
                if(gama.getName()!=null){
                    g.get().setName(gama.getName());
                }
                return gamaRepository.save(g.get());
            }
        }
        return gama;
    }
    
    
    public boolean deleteGama(int gamaId){
        Boolean d=getGama(gamaId).map(gama -> {
            gamaRepository.delete(gama);
            return true;
        }).orElse(false);
        return d;
    }
    
    
}
