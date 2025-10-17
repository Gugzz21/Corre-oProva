package com.senac.gustavo.service;

import com.senac.gustavo.config.SecurityConfiguration;
import com.senac.gustavo.dto.request.FuncionarioDtoRequest;
import com.senac.gustavo.dto.response.FuncionarioDtoResponse;
import com.senac.gustavo.entity.Funcionario;
import com.senac.gustavo.entity.Role;
import com.senac.gustavo.entity.RoleName;
import com.senac.gustavo.repository.FuncionarioRepository;
import com.senac.gustavo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    private final SecurityConfiguration securityConfiguration;

    private final RoleRepository roleRepository;


    public FuncionarioService(FuncionarioRepository funcionarioRepository, SecurityConfiguration securityConfiguration, RoleRepository roleRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.securityConfiguration = securityConfiguration;
        this.roleRepository = roleRepository;

    }

    public List<Funcionario> listarTodos(){
       return funcionarioRepository.findAll();
    }

    public Funcionario listarPorId(Integer id){
        return funcionarioRepository.findById(id).orElse(null);
    }

    public FuncionarioDtoResponse criarFuncionario (FuncionarioDtoRequest funcionarioRequest){
        Funcionario funcionariosave = new Funcionario();

        funcionariosave.setMatricula(funcionarioRequest.getMatricula());
        funcionariosave.setNome(funcionarioRequest.getNome());
        funcionariosave.setChaveAcesso(securityConfiguration.passwordEncoder().encode(funcionarioRequest.getChaveAcesso()));
        funcionariosave.setDataNascimento(funcionarioRequest.getDataNascimento());
        funcionariosave.setStatus(funcionarioRequest.getStatus());
        Role role = roleRepository.findByNome(RoleName.valueOf(funcionarioRequest.getRoles().getNome().name()))
                .orElseThrow(() -> new RuntimeException("Role não encontrada para o nome fornecido."));


        Funcionario funcionarioTemp = funcionarioRepository.save(funcionariosave);
        FuncionarioDtoResponse funcionarioResponse = new FuncionarioDtoResponse();
        funcionarioResponse.setMatricula(funcionarioTemp.getMatricula());
        funcionarioResponse.setNome(funcionarioTemp.getNome());
        funcionarioResponse.setChaveAcesso(funcionarioTemp.getChaveAcesso());
        funcionarioResponse.setDataNascimento(funcionarioTemp.getDataNascimento());
        funcionarioResponse.setStatus(funcionarioTemp.getStatus());
        return funcionarioResponse;
    }






    }







