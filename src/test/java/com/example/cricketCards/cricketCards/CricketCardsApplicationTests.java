package com.example.cricketCards.cricketCards;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cricketCards.services.CricketService;
import com.example.cricketCards.services.StackService;

@SpringBootTest
class CricketCardsApplicationTests {
	
	@Autowired
	private CricketService cricketService;
	
	@Autowired
	private StackService stackService;

	@Test
	void contextLoads() {
		
	}
	
	@Test
	void randomArray() {
		int[] randomInts = new int[60];
		for(int i=0; i<60; i++) {
			randomInts[i] = i;
		}
		for(int i=0; i<60; i++) {
			int random = (int) Math.floor(Math.random()*(i+1));
			int temp = randomInts[i];
			randomInts[i] = randomInts[random] + 1;
			randomInts[random] = temp;
		}
		for(int i=0; i<60; i++) {
			System.out.print(randomInts[i] + ", ");
		}
	}
	
	@Test
	void testCricketService() {
		int output = cricketService.comparePlayers(1, 2, "runs");
		assertThat(output).isEqualTo(0);
		
		int out2 = cricketService.comparePlayers(5, 3, "strikeRate");
		assertThat(out2).isEqualTo(0);
		
		int out3 = cricketService.comparePlayers(4, 7, "best");
		assertThat(out3).isEqualTo(0);
		
		int out4 = cricketService.comparePlayers(4, 6, "economy");
		assertThat(out4).isEqualTo(1);
		
	}
	
	@Test
	void stackServiceTest() {
		int[] cards = {5, 7};
		stackService.addToStack(1, cards);
		//stackService.removeFromStack("double", "marcus");
	}

}
