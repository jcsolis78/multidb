package com.jc.multiplesdb.repository;

import com.jc.multiplesdb.models.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IResource extends JpaRepository<Resources, Long> {
}
