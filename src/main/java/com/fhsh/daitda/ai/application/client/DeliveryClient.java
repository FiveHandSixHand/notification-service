package com.fhsh.daitda.ai.application.client;

import java.util.UUID;

import com.fhsh.daitda.ai.application.client.dto.DeliveryClientResponse;

public interface DeliveryClient {
	public DeliveryClientResponse.DeliveryRoutes getRoutes(UUID deliveryId);
}
