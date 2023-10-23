package com.jc.multiplesdb.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Newservices {


    public void impresion(String mensaje){
        log.info("Esta en el metodo de impresion: " + mensaje);
    }

}
