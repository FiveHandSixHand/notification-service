package com.fhsh.daitda.ai.presentation;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fhsh.daitda.ai.application.OpenAIService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/v1/openai")
public class OpenAITestController {
	private final OpenAIService openAIService;

	@GetMapping
	public String responseTest(@RequestParam UUID orderId) {
		return openAIService.refineLogisticsMessage(orderId);
	}

}
