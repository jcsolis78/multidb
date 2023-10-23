package com.jc.multiplesdb.repository;

import com.jc.multiplesdb.models.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Personas, Long> {
}
