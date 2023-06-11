package com.baccarin.baralho;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.RequiredArgsConstructor;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class CartaGameServiceTests {


//    private final CartaGameService cardGameService;
//    private final MaoRepository maoRepository;
//
//    @Test
//    public void testPlayGame() {
//        List<Mao> maos = cardGameService.playGame();
//        assertEquals(4, maos.size());
//
//        maos.forEach(mao -> {
//        	 assertEquals(5, mao.getCartas().size());
//        });
//
//    }
//
//    @Test
//    public void testHandRepository() {
//    	Mao mao = new Mao();
//        mao.setCartas(Arrays.asList(new Carta(), new Carta(), new Carta(), new Carta(), new Carta()));
//        maoRepository.save(mao);
//
//        List<Mao> savedHands = maoRepository.findAll();
//        assertEquals(1, savedHands.size());
//        assertEquals(5, savedHands.get(0).getCartas().size());
//    }
}
