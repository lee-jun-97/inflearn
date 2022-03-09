package com.inflearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inflearn.repository.MemberRepository;
import com.inflearn.repository.MemoryMemberRepository;
import com.inflearn.service.MemberService;

@Configuration
public class SpringConfig {
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}

}
