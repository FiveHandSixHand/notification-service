package com.fhsh.daitda.slack.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import com.fhsh.daitda.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_slack_message", schema = "notification_service") // 스키마 명시
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlackMessage extends BaseEntity { // 기존에 사용하시던 Auditing 상속

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID slackMessageId;

	@Column(nullable = false, length = 100)
	private String receiverSlackId;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String message;

	private LocalDateTime sentAt;

	private UUID orderId;

	@Column(length = 50)
	private String messageType;

	@Column(nullable = false)
	private boolean isSent = false;

	@Builder
	public SlackMessage(String receiverSlackId, String message, UUID orderId, String messageType) {
		this.receiverSlackId = receiverSlackId;
		this.message = message;
		this.orderId = orderId;
		this.messageType = messageType;
	}

	public void markAsSent() {
		this.isSent = true;
		this.sentAt = LocalDateTime.now();
	}
}