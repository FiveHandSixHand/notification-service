package com.fhsh.daitda.ai.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.fhsh.daitda.ai.application.client.OrderClient;
import com.fhsh.daitda.ai.application.client.dto.OrderClientResponse;

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

		 위 데이터를 바탕으로 요약해줘.
    """;

	private static String systemInstruction = """
        너는 전문 물류 데이터 요약 및 예상 시간 계산이 필요해. 
        아래 경로에 대한 거리와 예상 시간을 보고 예상 도착 시간을 구해줘.
        제공된 주문 정보를 바탕으로 아래 [출력 양식]에 맞춰 요약해줘.
        단, 데이터가 null이거나 비어있는 항목은 null이라고 표시해줘.
        
        
        ### 출력 양식 ###
        주문 번호 : {orderId}
        주문자 정보 : {orderer}
        주문 시간 : {orderAt}
        상품 정보 : {products}
        요청 사항 : {requestMessage}
        배송 번호 : {deliveryId}
        예상 시간 : {estimatedTime}
        """;

	public OpenAIService(ChatClient.Builder builder, OrderClient orderClient) {
		this.chatClient = builder.build();
		this.orderClient = orderClient;
	}

	public String refineLogisticsMessage(UUID orderId) {
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
			.content();
	}

	private String formatProducts(List<OrderClientResponse.OrderItemInfo> infos) {
		if (infos == null || infos.isEmpty()) return null;
		return infos.stream()
			.map(i -> i.productName() + " " + i.quantity() + "개")
			.collect(Collectors.joining(", "));
	}
}
