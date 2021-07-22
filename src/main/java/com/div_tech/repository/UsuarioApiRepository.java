package com.div_tech.repository;

import com.div_tech.entities.UsuarioApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioApiRepository extends JpaRepository<UsuarioApi, Integer>{

}
