package com.div_tech.repository;

import com.div_tech.entities.Empreendimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpreendimentoRepository extends CrudRepository<Empreendimentos, Integer> {


}
