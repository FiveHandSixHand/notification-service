package com.fhsh.daitda.slack.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fhsh.daitda.slack.application.command.SendSlackCommand;
import com.fhsh.daitda.slack.application.result.SlackMessageResponse;
import com.fhsh.daitda.slack.application.service.SlackMessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/slack-messages") // 버전 미사용 규칙 준수
@RequiredArgsConstructor
public class SlackMessageController {

	private final SlackMessageService slackMessageService;

	/**
	 * [POST] 알림 전송 및 기록 저장
	 * AI 분석 결과가 포함된 메시지를 받아 전송합니다.
	 */
	@PostMapping
	public ResponseEntity<UUID> sendNotification(@RequestBody SendSlackCommand command) {
		UUID logId = slackMessageService.sendAndLogMessage(command);
		return ResponseEntity.ok(logId);
	}

	/**
	 * [GET] 알림 전송 이력 단건 조회
	 */
	// presentation/controller/SlackMessageController.java
	@GetMapping("/{id}")
	public ResponseEntity<SlackMessageResponse> getMessageLog(@PathVariable UUID id) {
		// 💡 서비스 파일에 getMessageLog라는 이름의 메서드가 있는지 꼭 확인하세요!
		return ResponseEntity.ok(slackMessageService.getMessageLog(id));
	}

	// 전체 조회
	@GetMapping
	public ResponseEntity<List<SlackMessageResponse>> getAllLogs() {
		return ResponseEntity.ok(slackMessageService.getAllMessageLogs());
	}

	// 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLog(@PathVariable UUID id) {
		slackMessageService.deleteLog(id);
		return ResponseEntity.noContent().build();
	}




}