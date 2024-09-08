package com.borsoitech.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borsoitech.tasks.entity.Habito;
import com.borsoitech.tasks.entity.HabitoHistorico;
import com.borsoitech.tasks.entity.Usuario;

@Repository
public interface HabitoHistoricoRepository extends JpaRepository<HabitoHistorico, Integer> {
    List<HabitoHistorico> findByHabito(Habito habito);

	List<HabitoHistorico> findByUsuario(Usuario usuario);
}