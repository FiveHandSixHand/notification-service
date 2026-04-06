package com.fhsh.daitda.slack.infrastructure.persistance;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fhsh.daitda.slack.domain.entity.SlackMessage;

public interface JpaSlackMessageRepository extends JpaRepository<SlackMessage, UUID> {
	List<SlackMessage> findAllByOrderIdAndDeletedAtIsNull(UUID orderId);
}