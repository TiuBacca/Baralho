package com.baccarin.baralho.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baccarin.baralho.Baralho;
import com.baccarin.baralho.IntegracaoAPI;
import com.baccarin.baralho.domain.Carta;
import com.baccarin.baralho.service.JogoService;
import com.baccarin.baralho.vo.response.DeckCartaResponse;
import com.baccarin.baralho.vo.response.ResponseGenerico;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JogoServiceImpl implements JogoService {

	private final IntegracaoAPI integracao;

	private final int QUANTIDADE_JOGADORES = 4;
	private final int QUANTIDADE_QUARTAS = 5;

	@Override
	public ResponseGenerico jogar() throws Exception {
		Baralho baralho = integracao.buscarBaralho();

		List<DeckCartaResponse> jogadores = new ArrayList<>();

		for (int i = 0; i < QUANTIDADE_JOGADORES; i++) {
			DeckCartaResponse deck = integracao.buscarListaDeCartas(baralho.getDeck_id(), QUANTIDADE_QUARTAS);
			
//			DeckCartaResponse deck = new DeckCartaResponse();
//			List<Carta> cartas = new ArrayList<>();
//			cartas.add(new Carta(null, null, "5", null));
//			cartas.add(new Carta(null, null, "2", null));
//			cartas.add(new Carta(null, null, "7", null));
//			cartas.add(new Carta(null, null, "3", null));
//			deck.setCards(cartas);
			
			deck.setJogador("Jogador " + i);
			deck.getCards().stream().forEach(carta -> {
				deck.setValorTotalCartas(deck.getValorTotalCartas() + carta.valorCarta(carta));
			});

			jogadores.add(deck);
		}

		int maiorSomatoria = 0;
		List<DeckCartaResponse> maioresJogadores = new ArrayList<>();
		for (DeckCartaResponse deck : jogadores) {
			if (deck.getValorTotalCartas() > maiorSomatoria) {
				maiorSomatoria = deck.getValorTotalCartas();
				maioresJogadores.clear();
				maioresJogadores.add(deck);
			} else if (deck.getValorTotalCartas() == maiorSomatoria) {
				maioresJogadores.add(deck);
			}
		}

		ResponseGenerico response = new ResponseGenerico();

		if (maioresJogadores.size() > 1) {
			response.setMensagem(DeckCartaResponse.toString(maioresJogadores));
		} else {
			response.setMensagem(maioresJogadores.get(0).toString());
		}

		return response;

	}

}
