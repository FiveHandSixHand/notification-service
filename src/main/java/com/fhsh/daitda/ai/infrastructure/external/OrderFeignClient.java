package com.fhsh.daitda.ai.infrastructure.external;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fhsh.daitda.ai.infrastructure.external.dto.OrderResponse;

@FeignClient(name = "order-service", url = "http://localhost:8086")
public interface OrderFeignClient {

	@GetMapping("/internal/v1/orders/{orderId}")
	OrderResponse.GetOrderDetailsResult getOrder(@PathVariable("orderId") UUID orderId);
}
