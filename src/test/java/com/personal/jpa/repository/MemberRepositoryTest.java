package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;


import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;

@SpringBootTest // Spring Context 를 로딩하여 Test 에 사용할수있게 하는 어노테이션
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud() {
        // repository 에서 전부 조회하지만 알파벳 역순으로 조회
        List<Member> members = memberRepository.findAll(Sort.by(Direction.DESC, "name"));

        // id 값이 1,3,5 번 인 것만 조회
        List<Member> members2 = memberRepository.findAllById(Lists.newArrayList(1L,3L,5L));

        // 여러 객체를 리스트로 저장
        Member roy = new Member("Roy", "1234@naver.com");
        Member oli = new Member("Olivia", "5555@gmail.com");
        List<Member> member3 = memberRepository.saveAll(Lists.newArrayList(roy, oli));

        // findById() 는 반환값이 Optional 객체로 mapping 되어있으며 원하는 값이 존재하지 않을 경우도 처리 할 수 있다.
        Member member4 = memberRepository.findById(1L).orElse(null);

        members.forEach(System.out::println);
    }
}