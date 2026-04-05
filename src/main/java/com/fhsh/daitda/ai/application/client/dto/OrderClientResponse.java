package com.fhsh.daitda.ai.application.client.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fhsh.daitda.ai.infrastructure.external.dto.OrderResponse;

public record OrderClientResponse(
	UUID orderId,
	UUID orderer,
	LocalDateTime orderAt,
	List<OrderResponse.OrderItemInfo> infos,
	String requestMessage,
	UUID deliveryId
) {
}
