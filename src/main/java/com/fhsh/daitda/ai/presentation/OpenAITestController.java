package com.fhsh.daitda.ai.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhsh.daitda.ai.application.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/v1/openai")
public class OpenAITestController {
	private final OpenAIService openAIService;

	@GetMapping
	public String responseTest() {
		return openAIService.refineLogisticsMessage();
	}

}
