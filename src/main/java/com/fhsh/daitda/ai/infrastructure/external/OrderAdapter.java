package com.fhsh.daitda.ai.infrastructure.external;

import java.util.List;
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
		List<OrderClientResponse.OrderItemInfo> items = result.infos().stream().map(
			itemInfo -> new OrderClientResponse.OrderItemInfo(itemInfo.productId(), itemInfo.productName(), itemInfo.quantity()))
			.toList();
		OrderClientResponse res = new OrderClientResponse(
			result.orderId(),
			result.orderer(),
			result.orderAt(),
			items,
			result.requestMessage(),
			result.deliveryId()
		);
		return res;
	}
}
