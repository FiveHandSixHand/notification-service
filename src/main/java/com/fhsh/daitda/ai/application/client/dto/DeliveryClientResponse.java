package com.fhsh.daitda.ai.application.client.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DeliveryClientResponse {
	public record DeliveryRoutes (
		List<DeliveryRoute> routeInfo
	){}
	public record DeliveryRoute(
		UUID deliveryRouteId,
		UUID deliveryId,
		String status,
		Integer sequence,
		UUID srcNodeId,
		String srcNodeType,
		UUID destNodeId,
		String destNodeType,
		Integer duration,
		Double distance,
		Integer estimatedDuration,
		Double estimatedDistance,
		UUID deliveryManagersId,
		LocalDateTime createdAt, //OffsetDateTime
		UUID createdBy,
		LocalDateTime updatedAt,
		UUID updatedBy
	) {}
}
