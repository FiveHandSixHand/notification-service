package com.fhsh.daitda.slack.infrastructure.persistance;

import com.fhsh.daitda.slack.domain.entity.SlackMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface JpaSlackMessageRepository extends JpaRepository<SlackMessage, UUID> {
	List<SlackMessage> findAllByOrderIdAndDeletedAtIsNull(UUID orderId);
}