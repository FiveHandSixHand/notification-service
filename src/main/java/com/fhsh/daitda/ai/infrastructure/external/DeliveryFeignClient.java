package com.fhsh.daitda.ai.infrastructure.external;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fhsh.daitda.ai.infrastructure.external.dto.DeliveryResponse;

@FeignClient(name = "delivery-service", url = "http://localhost:8083")
public interface DeliveryFeignClient {
	@GetMapping("/api/v1/deliveries/{deliveryId}/routes")
	public DeliveryResponse.DeliveryRoutes getRoutes(@PathVariable UUID deliveryId);
}
