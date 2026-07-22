package seewald.rafael.CadastroFuncionarios.service;

import seewald.rafael.CadastroFuncionarios.repository.FuncionarioRepository;

public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService() {
        this.funcionarioRepository = new FuncionarioRepository();
    }
}
