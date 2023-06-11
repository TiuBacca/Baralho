package com.baccarin.baralho;

import java.util.Collections;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.baccarin.baralho.vo.deckapi.Baralho;
import com.baccarin.baralho.vo.deckapi.DeckCartaResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IntegracaoAPI {

	public Baralho buscarBaralho() throws Exception {
		String url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1";
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity = getHttpEntity();
		ResponseEntity<Baralho> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Baralho.class);
		if (retorno.getStatusCode() == HttpStatus.OK) {
			return retorno.getBody();
		}
		throw new Exception("Erro ao buscar baralho.");
	}

	public DeckCartaResponse buscarListaDeCartas(String idBaralho, int numeroCartas) throws Exception {

		String url = "https://deckofcardsapi.com/api/deck/" + idBaralho + "/draw/?count=" + numeroCartas;
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity = getHttpEntity();
		ResponseEntity<DeckCartaResponse> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, DeckCartaResponse.class);
		if (retorno.getStatusCode() == HttpStatus.OK) {
			return retorno.getBody();
		}
		throw new Exception("Erro ao buscar lista de cartas.");
	}

	public void embaralharBaralho(String idBaralho) throws Exception {
		String url = "https://deckofcardsapi.com/api/deck/" + idBaralho + "/shuffle/";
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<String> entity = getHttpEntity();
		ResponseEntity<Baralho> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, Baralho.class);
		if (retorno.getStatusCode() == HttpStatus.OK) {
			return;
		}
		throw new Exception("Erro ao embaralhar baralho.");
	}

	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return new HttpEntity<String>(headers);
	}
}
