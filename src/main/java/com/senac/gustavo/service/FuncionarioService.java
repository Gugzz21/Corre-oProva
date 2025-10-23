package com.senac.gustavo.service;


import com.senac.gustavo.config.SecurityConfiguration;
import com.senac.gustavo.dto.LoginUserDto;
import com.senac.gustavo.dto.RecoveryJwtTokenDto;
import com.senac.gustavo.dto.request.FuncionarioDtoRequest;
import com.senac.gustavo.dto.response.FuncionarioDtoResponse;
import com.senac.gustavo.entity.Funcionario;
import com.senac.gustavo.entity.Role;
import com.senac.gustavo.entity.RoleName;
import com.senac.gustavo.repository.FuncionarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final SecurityConfiguration securityConfig;
    private final RoleService roleService;

    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    public FuncionarioService(FuncionarioRepository funcionarioRepository, SecurityConfiguration securityConfig,
                              RoleService roleService, AuthenticationManager authenticationManager,
                                JwtTokenService jwtTokenService) {
        this.funcionarioRepository = funcionarioRepository;
        this.securityConfig = securityConfig;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }
    public FuncionarioDtoResponse criarFuncionario(FuncionarioDtoRequest funcionarioRequest){

        Funcionario funcionarioSalvo = new Funcionario();

        funcionarioSalvo.setMatricula(funcionarioRequest.getMatricula());
        funcionarioSalvo.setChaveAcesso(securityConfig.passwordEncoder().encode(funcionarioRequest.getChaveAcesso()));
        funcionarioSalvo.setNome(funcionarioRequest.getNome());
        funcionarioSalvo.setDataNascimento(funcionarioRequest.getDataNacimento());
        funcionarioSalvo.setStatus(funcionarioRequest.getStatus());
        Role role;
        RoleName roleNome = funcionarioRequest.getRole();
        role = roleService.getRolesByName(roleNome);
        funcionarioSalvo.setRoles(List.of(role));

        Funcionario funcionarioTemp = funcionarioRepository.save(funcionarioSalvo);
        FuncionarioDtoResponse funcionarioResponse = new FuncionarioDtoResponse();

        funcionarioResponse.setMatricula(funcionarioTemp.getMatricula());
        funcionarioResponse.setNome(funcionarioTemp.getNome());
        funcionarioResponse.setDataNacimento(funcionarioTemp.getDataNascimento());
        funcionarioResponse.setStatus(funcionarioTemp.getStatus());
        funcionarioResponse.setRoles(funcionarioSalvo.getRoles());

        return funcionarioResponse;
    }
    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll();
    }
    public Funcionario listaPorId(Integer id){
        return funcionarioRepository.findById(id).orElse(null);
    }

    public RecoveryJwtTokenDto autenticarFuncionario(LoginUserDto loginUserDto) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.matricula(), loginUserDto.chaveAcesso());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }
}














