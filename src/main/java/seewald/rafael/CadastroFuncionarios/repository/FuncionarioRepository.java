package seewald.rafael.CadastroFuncionarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seewald.rafael.CadastroFuncionarios.model.FuncionarioModel;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, Long > {
}
