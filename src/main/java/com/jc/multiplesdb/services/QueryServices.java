package com.jc.multiplesdb.services;

import com.jc.multiplesdb.models.Resources;
import com.jc.multiplesdb.repository.IResource;
import com.jc.multiplesdb.utils.EntityManagerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryServices {


    @Autowired
    private IResource resource;


    @Autowired
    private EntityManagerUtils managerUtils;


    private void setRepository(String db){
        resource = managerUtils.repositoryFactory(db).getRepository(IResource.class);
    }

    public List<Resources> findAll(String db){
        setRepository(db);
        return resource.findAll();
    }


}
