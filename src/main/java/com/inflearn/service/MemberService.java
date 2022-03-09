package com.inflearn.service;

import java.util.List;
import java.util.Optional;

import com.inflearn.domain.Member;
import com.inflearn.repository.MemberRepository;

public class MemberService {
	
	private final MemberRepository memberRepository;
	
	// 생성자 주입 
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public Long join(Member member) {
		
		// 같은 이름이 있는 중복 회원 X
		validDuplicateMemeber(member);
		
		memberRepository.save(member);
		
		return member.getId();
	}

	
	// 전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	private void validDuplicateMemeber(Member member) {
		
		// member.get() 으로 꺼내도 되지만 권장하지는 않음.
		
		memberRepository.findByName(member.getName())
		// 값이 있으면 실행되는 메서드
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}

}
