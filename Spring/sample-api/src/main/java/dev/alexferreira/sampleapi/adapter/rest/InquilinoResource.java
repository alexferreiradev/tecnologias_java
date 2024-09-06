package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.usecase.CreateInquilino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/inquilinos", consumes = "application/json", produces = "application/json")
public class InquilinoResource {

	private final CreateInquilino createInquilino;

	@Autowired
	public InquilinoResource(CreateInquilino createInquilino) {
		this.createInquilino = createInquilino;
	}


	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createInquilino(@RequestBody CreateInquilinoRequest request) {
		createInquilino.execute(request.toInput());
	}
}
