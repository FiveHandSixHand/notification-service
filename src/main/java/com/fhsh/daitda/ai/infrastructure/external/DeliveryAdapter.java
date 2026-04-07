package com.fhsh.daitda.ai.infrastructure.external;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fhsh.daitda.ai.application.client.DeliveryClient;
import com.fhsh.daitda.ai.application.client.dto.DeliveryClientResponse;
import com.fhsh.daitda.ai.infrastructure.external.dto.DeliveryResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeliveryAdapter implements DeliveryClient {
	private final DeliveryFeignClient deliveryFeignClient;

	public DeliveryClientResponse.DeliveryRoutes getRoutes(UUID deliveryId) {
		DeliveryResponse.DeliveryRoutes routes = deliveryFeignClient.getRoutes(deliveryId);

		List<DeliveryClientResponse.DeliveryRoute> clientRoutes = routes.routeInfo().stream()
			.map(route -> new DeliveryClientResponse.DeliveryRoute(
				route.deliveryRouteId(),
				route.deliveryId(),
				route.status(),
				route.sequence(),
				route.srcNodeId(),
				route.srcNodeType(),
				route.destNodeId(),
				route.destNodeType(),
				route.duration(),
				route.distance(),
				route.estimatedDuration(),
				route.estimatedDistance(),
				route.deliveryManagersId(),
				route.createdAt(),
				route.createdBy(),
				route.updatedAt(),
				route.updatedBy()
			))
			.toList();

		return new DeliveryClientResponse.DeliveryRoutes(clientRoutes);
	}
}
