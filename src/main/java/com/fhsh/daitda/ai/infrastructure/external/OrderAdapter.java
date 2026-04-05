package com.fhsh.daitda.ai.infrastructure.external;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fhsh.daitda.ai.application.client.OrderClient;
import com.fhsh.daitda.ai.application.client.dto.OrderClientResponse;
import com.fhsh.daitda.ai.infrastructure.external.dto.OrderResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class OrderAdapter implements OrderClient {
	private final OrderFeignClient orderFeignClient;

	@Override
	public OrderClientResponse getOrder(UUID orderId) {
		OrderResponse.GetOrderDetailsResult result = orderFeignClient.getOrder(orderId);
		OrderClientResponse res = new OrderClientResponse(
			result.orderId(),
			result.orderer(),
			result.orderAt(),
			result.infos(),
			result.requestMessage(),
			result.deliveryId()
		);
		return res;
	}
}
