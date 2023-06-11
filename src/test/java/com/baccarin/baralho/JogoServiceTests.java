package com.baccarin.baralho;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.baccarin.baralho.integracao.IntegracaoAPI;
import com.baccarin.baralho.repository.JogoRepository;
import com.baccarin.baralho.service.impl.JogoServiceImpl;
import com.baccarin.baralho.vo.deckapi.Baralho;
import com.baccarin.baralho.vo.deckapi.Carta;
import com.baccarin.baralho.vo.deckapi.DeckCartaResponse;
import com.baccarin.baralho.vo.response.ResponseGenerico;

class JogoServiceImplTest {

    @Mock
    private IntegracaoAPI integracaoAPI;
    
    @Mock
    private JogoRepository jogoRepository;
    
    @InjectMocks
    private JogoServiceImpl jogoService;
    
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void jogar_DeckAPIRetornaBaralho_Correto() throws Exception {
        // Arrange
        Baralho baralho = new Baralho();
        baralho.setDeck_id("deck_id");
        when(integracaoAPI.buscarBaralho()).thenReturn(baralho);
        
        // Act
        ResponseGenerico response = jogoService.jogar();
        
        // Assert
        verify(integracaoAPI).buscarBaralho();
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isNull();
    }
    
    @Test
    void jogar_DeckAPIRetornaListaCartas_Correto() throws Exception {
        // Arrange
        Baralho baralho = new Baralho();
        baralho.setDeck_id("deck_id");
        when(integracaoAPI.buscarBaralho()).thenReturn(baralho);
        
        Carta carta1 = new Carta(null, null, "5", null);
        Carta carta2 = new Carta(null, null, "2", null);
        Carta carta3 = new Carta(null, null, "7", null);
        Carta carta4 = new Carta(null, null, "3", null);
        List<Carta> cartas = Arrays.asList(carta1, carta2, carta3, carta4);
        DeckCartaResponse deckCartaResponse = new DeckCartaResponse();
        deckCartaResponse.setCards(cartas);
        when(integracaoAPI.buscarListaDeCartas(baralho.getDeck_id(), 5)).thenReturn(deckCartaResponse);
        
        // Act
        ResponseGenerico response = jogoService.jogar();
        
        // Assert
        verify(integracaoAPI).buscarListaDeCartas(baralho.getDeck_id(), 5);
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isNull();
    }
    
    @Test
    void jogar_JogadoresComValoresIguais_Empate() throws Exception {
        // Arrange
        Baralho baralho = new Baralho();
        baralho.setDeck_id("deck_id");
        when(integracaoAPI.buscarBaralho()).thenReturn(baralho);
        
        Carta carta1 = new Carta(null, null, "5", null);
        Carta carta2 = new Carta(null, null, "5", null);
        Carta carta3 = new Carta(null, null, "5", null);
        Carta carta4 = new Carta(null, null, "5", null);
        List<Carta> cartas = Arrays.asList(carta1, carta2, carta3, carta4);
        DeckCartaResponse deckCartaResponse = new DeckCartaResponse();
        deckCartaResponse.setCards(cartas);
        when(integracaoAPI.buscarListaDeCartas(baralho.getDeck_id(), 5)).thenReturn(deckCartaResponse);
        
        // Act
        ResponseGenerico response = jogoService.jogar();
        
        // Assert
        verify(integracaoAPI).buscarListaDeCartas(baralho.getDeck_id(), 5);
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isEqualTo("Jogador 0, Jogador 1, Jogador 2, Jogador 3");
    }
    
    @Test
    void jogar_JogadorComMaiorValor_Vitoria() throws Exception {
        // Arrange
        Baralho baralho = new Baralho();
        baralho.setDeck_id("deck_id");
        when(integracaoAPI.buscarBaralho()).thenReturn(baralho);
        
        Carta carta1 = new Carta(null, null, "5", null);
        Carta carta2 = new Carta(null, null, "2", null);
        Carta carta3 = new Carta(null, null, "7", null);
        Carta carta4 = new Carta(null, null, "3", null);
        List<Carta> cartas = Arrays.asList(carta1, carta2, carta3, carta4);
        DeckCartaResponse deckCartaResponse = new DeckCartaResponse();
        deckCartaResponse.setCards(cartas);
        when(integracaoAPI.buscarListaDeCartas(baralho.getDeck_id(), 5)).thenReturn(deckCartaResponse);
        
        // Act
        ResponseGenerico response = jogoService.jogar();
        
        // Assert
        verify(integracaoAPI).buscarListaDeCartas(baralho.getDeck_id(), 5);
        assertThat(response).isNotNull();
        assertThat(response.getMensagem()).isEqualTo("Jogador 2: 5, 2, 7, 3");
    }
}