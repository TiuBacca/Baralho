package com.baccarin.baralho.vo.deckapi;

import java.util.List;
import java.util.StringJoiner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeckCartaResponse {

	private String jogador;
	private boolean success;
	private String deck_id;
	private List<Carta> cards;
	private int remaining;
	private int valorTotalCartas;

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ");
		for (Carta carta : cards) {
			joiner.add(carta.getValue());
		}
		return "Vencedor: " + jogador + ": " + joiner.toString() + " com o total de: " + valorTotalCartas + " pontos.";
	}

	public static String toString(List<DeckCartaResponse> decks) {
		StringBuilder sb = new StringBuilder();
		sb.append("EMPATE \n");
		for (DeckCartaResponse deck : decks) {
			sb.append(deck.getJogador()).append(" = [");
			List<Carta> cards = deck.getCards();
			for (int i = 0; i < cards.size(); i++) {
				sb.append(cards.get(i).getValue());
				if (i < cards.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("]").append(System.lineSeparator());
		}
		return sb.toString();
	}

}
