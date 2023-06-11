package com.baccarin.baralho.vo.deckapi;

import java.util.List;
import java.util.stream.Collectors;

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
	
	
    public static String formatarParaSalvarJogo(List<Carta> cartas) {
        return cartas.stream()
                .map(Carta::getValue)
                .collect(Collectors.joining(", "));
    }
	

}