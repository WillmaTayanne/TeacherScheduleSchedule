package com.pdist.schedule.Repository;

import com.pdist.schedule.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
