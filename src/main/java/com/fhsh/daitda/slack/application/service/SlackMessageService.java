package com.fhsh.daitda.slack.application.service;

import com.fhsh.daitda.slack.application.command.SendSlackCommand;
import com.fhsh.daitda.slack.application.result.SlackMessageResponse;
import com.fhsh.daitda.slack.domain.entity.SlackMessage;
import com.fhsh.daitda.slack.domain.repository.SlackMessageRepository;
import com.fhsh.daitda.slack.infrastructure.external.SlackClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SlackMessageService {

	private final SlackMessageRepository slackMessageRepository;
	private final SlackClient slackClient;

	@Transactional
	public UUID sendAndLogMessage(SendSlackCommand command) {
		// 1. 엔티티 생성
		SlackMessage slackMessage = SlackMessage.builder()
			.orderId(command.getOrderId())
			.receiverSlackId(command.getReceiverId())
			.message(command.getContent())
			.isSent(false)
			.build();

		SlackMessage savedMessage = slackMessageRepository.save(slackMessage);

		try {
			// 2. 슬랙 전송
			slackClient.send(savedMessage.getReceiverSlackId(), savedMessage.getMessage());
			// 3. 상태 변경
			savedMessage.markAsSent();
		} catch (Exception e) {
			throw new RuntimeException("슬랙 전송 실패: " + e.getMessage());
		}

		return savedMessage.getSlackMessageId();
	}


	// 모든 발송 로그 조회 (Read List)
	public List<SlackMessageResponse> getAllMessageLogs() {
		return slackMessageRepository.findAll().stream()
			.map(SlackMessageResponse::from)
			.collect(Collectors.toList());
	}

	// 로그 삭제 (Delete - Soft Delete 권장)
	@Transactional
	public void deleteLog(UUID id) {
		SlackMessage message = slackMessageRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("삭제할 기록이 없습니다."));

		// BaseEntity에 삭제 로직이 있다면 그것을 사용하고,
		// 없다면 리포지토리에서 직접 삭제합니다.
		slackMessageRepository.delete(message);
	}





	public SlackMessageResponse getMessageLog(UUID id) {
		SlackMessage message = slackMessageRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 알림 기록을 찾을 수 없습니다."));

		// SlackMessageResponse에 있는 from 메서드를 호출합니다.
		return SlackMessageResponse.from(message);
	}
}