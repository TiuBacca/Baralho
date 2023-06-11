package com.baccarin.baralho.domain;

import java.time.LocalDateTime;

import com.baccarin.baralho.enums.StatusJogo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jogo", schema = "baralho", uniqueConstraints = {})
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_deck", columnDefinition = "text")
	private String idDeck;

	@Column(name = "vencedor" , columnDefinition = "text")
	private String vencedor;

	@Column(name = "lista_cartas" , columnDefinition = "text")
	private String listaCartas;

	@Column(name = "pontos")
	private Integer pontos;
	
	@Column(name = "data_jogo")
	private LocalDateTime dataJogo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, updatable = true)
	private StatusJogo status;

}