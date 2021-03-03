package dev.alexferreira.conteudo.datatype;

import org.junit.Assert;
import org.junit.Test;


public class FloatTest {

	@Test
	public void wrapper() {
		float numero = 2.5f;

		Assert.assertEquals((Float)2.5f, Float.valueOf(numero));
	}

	@Test
	public void typePromotion() {
		float f = 2.5f;
		short s = 25;
		int i = 10;

		Assert.assertEquals(10, f+ s+ i, 1);
	}
}
