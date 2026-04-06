package com.fhsh.daitda.slack.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fhsh.daitda.slack.domain.entity.SlackMessage;

public interface SlackMessageRepository {

	// 기본 CRUD
	SlackMessage save(SlackMessage slackMessage);
	Optional<SlackMessage> findById(UUID id);
	List<SlackMessage> findAll();

	// 비즈니스 쿼리 (삭제되지 않은 데이터만 조회 등)
	List<SlackMessage> findAllByOrderIdAndDeletedAtIsNull(UUID orderId);

	// 삭제 (Soft Delete는 엔티티 업데이트로 처리하거나 여기서 정의)
	void delete(SlackMessage slackMessage);
}