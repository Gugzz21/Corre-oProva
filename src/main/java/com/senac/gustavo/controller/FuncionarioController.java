package com.senac.gustavo.controller;

import com.senac.gustavo.dto.CreateUserDto;
import com.senac.gustavo.dto.request.FuncionarioDtoRequest;
import com.senac.gustavo.entity.Funcionario;
import com.senac.gustavo.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    //Endpoints para Listar Funcionários
    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listarFuncionarios(){

        return ResponseEntity.ok(funcionarioService.listarTodos());

    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Funcionario> listarFuncionarios(@PathVariable("id") Integer id){

        return ResponseEntity.ok(funcionarioService.listarPorId(id));

    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarFuncionario(@RequestBody FuncionarioDtoRequest funcionarioDtoRequest) {
        funcionarioService.criarFuncionario(funcionarioDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
