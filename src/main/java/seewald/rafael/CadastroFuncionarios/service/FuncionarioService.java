package seewald.rafael.CadastroFuncionarios.service;

import org.springframework.stereotype.Service;
import seewald.rafael.CadastroFuncionarios.dto.FuncionarioRequestDTO;
import seewald.rafael.CadastroFuncionarios.model.FuncionarioModel;
import seewald.rafael.CadastroFuncionarios.repository.FuncionarioRepository;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public FuncionarioModel salvarFuncionario(FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioModel funcionario = new FuncionarioModel();
        funcionario.setNome(funcionarioRequestDTO.nome());
        funcionario.setEmail(funcionarioRequestDTO.email());
        funcionario.setSalario(funcionarioRequestDTO.salario());
        funcionario.setCargo(funcionarioRequestDTO.cargo());

        String cpfSomenteNumeros = funcionarioRequestDTO.cpf().replaceAll("\\D", "");
        funcionario.setCpf(cpfSomenteNumeros);

        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioModel> buscarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public FuncionarioModel atualizarFuncionario(Long Id, FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioModel funcionarioExistence = buscarFuncionarioPorId(Id);

        funcionarioExistence.setNome(funcionarioRequestDTO.nome());
        funcionarioExistence.setEmail(funcionarioRequestDTO.email());
        funcionarioExistence.setSalario(funcionarioRequestDTO.salario());
        funcionarioExistence.setCpf(funcionarioRequestDTO.cpf());
        funcionarioExistence.setCargo(funcionarioRequestDTO.cargo());
        return funcionarioRepository.save(funcionarioExistence);
    }

    public void deletarFuncionario(Long Id){
        FuncionarioModel funcionarioExistence = buscarFuncionarioPorId(Id);
        funcionarioRepository.deleteById(Id);
    }
}
