package com.baccarin.baralho.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.baralho.service.JogoService;
import com.baccarin.baralho.vo.response.ResponseGenerico;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jogo")
@RequiredArgsConstructor
public class JogoController {
	
	private final JogoService jogoService;
	
	@GetMapping("iniciar")
	@ApiOperation("Iniciar jogo")
	public ResponseEntity<ResponseGenerico> jogar()
			throws Exception {
		return new ResponseEntity<>( jogoService.jogar() , HttpStatus.OK);
	}
}