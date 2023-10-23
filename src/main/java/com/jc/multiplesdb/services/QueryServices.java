package com.jc.multiplesdb.services;

import com.jc.multiplesdb.models.Resources;
import com.jc.multiplesdb.repository.IResource;
import lombok.extern.slf4j.Slf4j;
import com.jc.multiplesdb.utils.EntityManagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryServices {


    @Autowired
    private IResource resource;


    @Autowired
    private EntityManagerUtils managerUtils;


    private void setRepository(String db){
        resource = managerUtils.repositoryFactory(db).getRepository(IResource.class);
    }

    public List<Resources> findAll(String db){
        log.info("Estoy en el metodo findAll() y la base de datos es: " + db); 
        setRepository(db);
        return resource.findAll();
    }


}
