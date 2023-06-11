package com.baccarin.baralho.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baccarin.baralho.IntegracaoAPI;
import com.baccarin.baralho.domain.Jogo;
import com.baccarin.baralho.enums.StatusJogo;
import com.baccarin.baralho.repository.JogoRepository;
import com.baccarin.baralho.service.JogoService;
import com.baccarin.baralho.vo.deckapi.Baralho;
import com.baccarin.baralho.vo.deckapi.Carta;
import com.baccarin.baralho.vo.deckapi.DeckCartaResponse;
import com.baccarin.baralho.vo.response.ResponseGenerico;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JogoServiceImpl implements JogoService {

	private final IntegracaoAPI integracao;
	private final JogoRepository jogoRepository;

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
		Jogo jogo = new Jogo();
		jogo.setIdDeck(baralho.getDeck_id());
		jogo.setDataJogo(LocalDateTime.now());

		if (maioresJogadores.size() > 1) {
			response.setMensagem(DeckCartaResponse.toString(maioresJogadores));
			jogo.setListaCartas(null);
			jogo.setVencedor(null);
			jogo.setPontos(null);
			jogo.setStatus(StatusJogo.EMPATE);
		} else {
			DeckCartaResponse vencedor = maioresJogadores.get(0);
			response.setMensagem(vencedor.toString());
			jogo.setListaCartas(Carta.formatarParaSalvarJogo(vencedor.getCards()));
			jogo.setVencedor(vencedor.getJogador());
			jogo.setPontos(vencedor.getValorTotalCartas());
			jogo.setStatus(StatusJogo.VITORIA);
		}

		jogoRepository.save(jogo);

		return response;

	}

}
