package com.borsoitech.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.borsoitech.tasks.entity.Habito;
import com.borsoitech.tasks.entity.Usuario;

@Repository
public interface HabitoRepository extends JpaRepository<Habito, Integer> {
    List<Habito> findByUsuario(Usuario usuario);
}
