package com.fhsh.daitda.slack.infrastructure.persistence;

import com.fhsh.daitda.slack.domain.entity.SlackMessage;
import com.fhsh.daitda.slack.domain.repository.SlackMessageRepository;
import com.fhsh.daitda.slack.infrastructure.persistance.JpaSlackMessageRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SlackMessageRepositoryImpl implements SlackMessageRepository {

	private final JpaSlackMessageRepository jpaRepository;

	@Override
	public SlackMessage save(SlackMessage slackMessage) {
		return jpaRepository.save(slackMessage);
	}

	@Override
	public Optional<SlackMessage> findById(UUID id) {
		return jpaRepository.findById(id);
	}

	@Override
	public List<SlackMessage> findAll() {
		return jpaRepository.findAll();
	}

	@Override
	public List<SlackMessage> findAllByOrderIdAndDeletedAtIsNull(UUID orderId) {
		return jpaRepository.findAllByOrderIdAndDeletedAtIsNull(orderId);
	}

	@Override
	public void delete(SlackMessage slackMessage) {
		// 프로젝트 규칙에 따른 Soft Delete 처리 로직이 들어갈 곳
		jpaRepository.delete(slackMessage);
	}
}