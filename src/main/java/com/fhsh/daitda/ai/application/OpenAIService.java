package com.fhsh.daitda.ai.application;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

	private final ChatClient chatClient;

	public OpenAIService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public String refineLogisticsMessage() {

		return chatClient.prompt()
			.user(u -> u.text("""
                    당신은 전문 물류 관리자입니다. 아래 데이터를 분석해 최종 발송 시한을 도출하세요.
                    - 상품: {item} ({count}개)
                    - 도착지: {dest}
                    - 납기: {limit}
                    - 담당자 근무: 09:00 - 18:00
                    
                    최종 발송 시한(일시)을 포함하여 담당자가 한눈에 보기 좋게 요약해 주세요.
                    """)
				.param("item","item")
				.param("count", "50")
				.param("dest", "dest")
				.param("limit", "~"))
			.call()
			.content();
	}
}
