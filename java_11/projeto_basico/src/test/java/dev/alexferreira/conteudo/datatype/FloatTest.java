package dev.alexferreira.conteudo.datatype;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FloatTest {

	@Test
	public void wrapper() {
		float numero = 2.5f;

		Assertions.assertEquals((Float)2.5f, Float.valueOf(numero));
	}

	@DisplayName("Valida a promocao de tipos como char para int")
	@Test
	public void typePromotion() {
		float f = 2.5f;
		short s = 25;
		int i = 10;
		char c = 'A';

		Assertions.assertEquals(102.5, f+ s+ i+ c, 1);
	}
}
