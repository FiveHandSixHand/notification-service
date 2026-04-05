package com.fhsh.daitda.slack.infrastructure.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SlackClient {

	private final RestTemplate restTemplate;

	@Value("${slack.token}") // application.yml에 저장된 토큰 사용
	private String slackToken;

	public void send(String channelId, String message) {
		String url = "https://slack.com/api/chat.postMessage";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(slackToken);

		Map<String, Object> body = new HashMap<>();
		body.put("channel", channelId);
		body.put("text", message);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

		ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, entity, Map.class);
		Map<String, Object> response = responseEntity.getBody();

		// 실제 전송
		Map<String, Object> slackResponse = restTemplate.postForObject(url, entity, Map.class);

		// 슬랙 API는 HTTP 상태코드가 200이어도 'ok: false'로 에러를 줄 수 있음
		if (response == null || !(Boolean) response.get("ok")) {
			throw new RuntimeException("슬랙 전송 실패: " + (response != null ? response.get("error") : "응답 없음"));
		}
	}
}