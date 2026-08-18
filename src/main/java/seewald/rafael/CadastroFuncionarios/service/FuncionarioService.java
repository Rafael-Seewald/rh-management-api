package seewald.rafael.CadastroFuncionarios.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import seewald.rafael.CadastroFuncionarios.dto.CorrigirCpfDTO;
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

        if (funcionarioRepository.existsByCpf(cpfSomenteNumeros)) {
            throw new DataIntegrityViolationException("Este CPF já está cadastrado.");
        }
        return funcionarioRepository.save(funcionario);
    }

    public List<FuncionarioModel> buscarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public FuncionarioModel buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com o id: " + id));
    }

    public FuncionarioModel atualizarFuncionario(Long Id, FuncionarioRequestDTO funcionarioRequestDTO) {
        FuncionarioModel funcionarioExistence = buscarFuncionarioPorId(Id);

        funcionarioExistence.setNome(funcionarioRequestDTO.nome());
        funcionarioExistence.setEmail(funcionarioRequestDTO.email());
        funcionarioExistence.setSalario(funcionarioRequestDTO.salario());
        funcionarioExistence.setCargo(funcionarioRequestDTO.cargo());
        return funcionarioRepository.save(funcionarioExistence);
    }

    public void deletarFuncionario(Long Id){
        FuncionarioModel funcionarioExistence = buscarFuncionarioPorId(Id);
        funcionarioRepository.delete(funcionarioExistence);
    }

    public FuncionarioModel corrigirCpf(Long id, CorrigirCpfDTO dto) {
        FuncionarioModel funcionario = buscarFuncionarioPorId(id);

        String novoCpfLimpo = dto.cpf().replaceAll("\\D", "");

        boolean cpfJaExiste = funcionarioRepository.existsByCpf(novoCpfLimpo);
        if (cpfJaExiste && !funcionario.getCpf().equals(novoCpfLimpo)) {
            throw new org.springframework.dao.DataIntegrityViolationException("Este CPF já está cadastrado para outro funcionário.");
        }

        System.out.println("AUDITORIA: CPF do funcionário ID " + id + " alterado de " + funcionario.getCpf() + " para " + novoCpfLimpo);

        funcionario.setCpf(novoCpfLimpo);
        return funcionarioRepository.save(funcionario);
    }
}
