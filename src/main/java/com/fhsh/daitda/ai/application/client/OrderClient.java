package com.fhsh.daitda.ai.application.client;

import java.util.UUID;

import com.fhsh.daitda.ai.application.client.dto.OrderClientResponse;

public interface OrderClient {
	public OrderClientResponse getOrder(UUID orderId);
}
