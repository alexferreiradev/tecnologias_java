package dev.alexferreira.sampleapi.modules.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Document("produto-log")
public class ProdutoLog {

	@Id
	public String id;

	public String data;
	public String service;
	public String log;

	private ProdutoLog() {
	}

	public static ProdutoLog createFrom(Object service, String log) {
		ProdutoLog produtoLog = new ProdutoLog();
		produtoLog.data = DateTimeFormatter.ISO_ZONED_DATE_TIME.format(ZonedDateTime.now());
		produtoLog.service = service.getClass().getSimpleName();
		produtoLog.log = log;

		return produtoLog;
	}

	@Override
	public String toString() {
		return "ProdutoLog{" +
				"id='" + id + '\'' +
				", data='" + data + '\'' +
				", service='" + service + '\'' +
				", log='" + log + '\'' +
				'}';
	}
}
