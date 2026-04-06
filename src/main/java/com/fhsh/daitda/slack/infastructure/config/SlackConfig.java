package com.fhsh.daitda.slack.infastructure.config;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

	@Bean
	public MethodsClient methodsClient() {
		// 슬랙 API 통신을 담당하는 클라이언트 인스턴스 생성

		return Slack.getInstance().methods();
	}
}