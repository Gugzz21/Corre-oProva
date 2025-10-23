package com.senac.gustavo.controller;

import com.senac.gustavo.dto.CreateUserDto;
import com.senac.gustavo.dto.LoginUserDto;
import com.senac.gustavo.dto.RecoveryJwtTokenDto;
import com.senac.gustavo.dto.request.FuncionarioDtoRequest;
import com.senac.gustavo.dto.response.FuncionarioDtoResponse;
import com.senac.gustavo.entity.Funcionario;
import com.senac.gustavo.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/lista/{id}")
    public ResponseEntity<Funcionario> listarFuncionario(@PathVariable("id") Integer id){
        return ResponseEntity.ok(funcionarioService.listaPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<FuncionarioDtoResponse> criarFuncionario(@RequestBody FuncionarioDtoRequest funcionarioRequest){
        return ResponseEntity.ok(funcionarioService.criarFuncionario(funcionarioRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> loginFuncionario(@RequestBody LoginUserDto loginUserDto){
        RecoveryJwtTokenDto tokenDto = funcionarioService.autenticarFuncionario(loginUserDto);
        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
