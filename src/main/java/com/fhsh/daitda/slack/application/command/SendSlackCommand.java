package com.fhsh.daitda.slack.application.command;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendSlackCommand {
	private final UUID orderId;
	private final String receiverId;
	private final String content; // AI가 조립해준 메시지 본문
	private String messageType;  // 메시지 유형 (예: DELIVERY_NOTIFICATION)

}