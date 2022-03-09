package com.inflearn.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.inflearn.domain.Member;

public class MemoryMemberRepository implements MemberRepository {
	
	// 동시성 문제 발생
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	@Override
	public Member save(Member member) {
		
		member.setId(++sequence);
		store.put(member.getId(), member);
		
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// null 발생 가능하기 때문에 Optional로 감쌈
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		
		 return store.values().stream()
			.filter(member -> member.getName().equals(name))
			.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}
	
	public void clearStore() {
		store.clear();
	}

}
