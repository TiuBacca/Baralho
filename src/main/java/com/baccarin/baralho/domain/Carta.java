package com.baccarin.baralho.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carta {

	private String code;
	private String image;
	private String value;
	private String suit;

	public int valorCarta(Carta carta) {
		String value = carta.getValue().toUpperCase();
		int valor;

		try {
			valor = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			switch (value) {
			case "A":
				valor = 1;
				break;
			case "K":
				valor = 13;
				break;
			case "Q":
				valor = 12;
				break;
			case "J":
				valor = 11;
				break;
			default:
				valor = 0;
				break;
			}
		}

		return valor;
	}

}