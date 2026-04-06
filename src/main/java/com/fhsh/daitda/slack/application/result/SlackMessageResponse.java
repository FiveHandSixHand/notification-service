package com.fhsh.daitda.slack.application.result;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fhsh.daitda.slack.domain.entity.SlackMessage;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SlackMessageResponse {
	private final UUID slackMessageId;
	private final String receiverSlackId;
	private final String message;
	private final boolean isSent;
	private final LocalDateTime sentAt;

	// 엔티티를 Response DTO로 변환하는 정적 메서드
	public static SlackMessageResponse from(SlackMessage entity) {
		return SlackMessageResponse.builder()
			.slackMessageId(entity.getSlackMessageId())
			.receiverSlackId(entity.getReceiverSlackId())
			.message(entity.getMessage())
			.isSent(entity.isSent())
			.sentAt(entity.getSentAt()) // 엔티티의 필드명 확인 (sentAt 혹은 getAt)
			.build();
	}
}