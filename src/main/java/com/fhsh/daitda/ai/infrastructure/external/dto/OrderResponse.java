package com.fhsh.daitda.ai.infrastructure.external.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderResponse {
	public record GetOrderDetailsResult(
		UUID orderId,
		UUID orderer,
		LocalDateTime orderAt,
		List<OrderItemInfo> infos,
		String requestMessage,
		UUID deliveryId
	) {}
	public record OrderItemInfo(
		UUID productId,
		String productName,
		int quantity
	) {}
}
