package com.fhsh.daitda.ai.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fhsh.daitda.ai.application.client.OrderClient;
import com.fhsh.daitda.ai.application.client.dto.OrderClientResponse;
import com.fhsh.daitda.ai.application.dto.LogisticsPrediction;

@Service
public class OpenAIService {

	private final ChatClient chatClient;
	private final OrderClient orderClient;

	private static final String LOGISTICS_PROMPT = """
		분석할 주문 데이터입니다:
		 - 주문번호: {orderId}
		 - 주문자: {orderer}
		 - 시간: {orderAt}
		 - 상품: {products}
		 - 요청: {requestMessage}
		 - 배송: {deliveryId}

		 위 데이터를 바탕으로 데이터를 분석해서 정해진 형식으로 출력해줘.
    """;

	private static String systemInstruction = """
		당신은 대한민국 물류 전문가입니다.
		입력된 [주문 시간]과 [도착 희망 시각]을 분석하여,
		한국 내 간선 하차 및 허브(옥천, 대전 등) 경유 시간을 고려한
		'현실적인 최종 발송 시한'을 예측하세요.
		현재 위치는 대한민국(KST)입니다.
        """;

	public OpenAIService(ChatClient.Builder builder, OrderClient orderClient) {
		this.chatClient = builder.build();
		this.orderClient = orderClient;
	}

	public LogisticsPrediction refineLogisticsMessage(UUID orderId) {
		OrderClientResponse res = orderClient.getOrder(orderId);
		return chatClient.prompt()
			.system(systemInstruction)
			.user(u -> u.text(LOGISTICS_PROMPT)
				.param("orderId", res.orderId())
				.param("orderer", res.orderer() != null ? res.orderer().toString() : "정보 없음")
				.param("orderAt", res.orderAt() != null ? res.orderAt().toString() : "")
				.param("products", formatProducts(res.infos()))
				.param("requestMessage", res.requestMessage() != null ? res.requestMessage() : "없음")
				.param("deliveryId", res.deliveryId() != null ? res.deliveryId().toString() : "미정")
			)
			.call()
			.entity(LogisticsPrediction.class);
	}

	private String formatProducts(List<OrderClientResponse.OrderItemInfo> infos) {
		if (infos == null || infos.isEmpty()) return null;
		return infos.stream()
			.map(i -> i.productName() + " " + i.quantity() + "개")
			.collect(Collectors.joining(", "));
	}
}
