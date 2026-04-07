package com.fhsh.daitda.ai.application.dto;

import java.util.List;

public record LogisticsPrediction(
	String orderId,
	String ordererId,
	String products,
	String summary,
	String expectedDispatchTime, // AI가 예측한 발송 시한
	String reasoning,            // 예측 근거 (한국 물류 상황 고려)
	List<String> routeCheck      // 경로상 특이점
) {}
