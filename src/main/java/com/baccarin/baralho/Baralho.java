package com.baccarin.baralho;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Baralho {

	private boolean success;
	private String deck_id;
	private boolean shuffled;
	private int remaining;

}
