package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Spring Context 를 로딩하여 Test 에 사용할수있게 하는 어노테이션
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void crud() {
        memberRepository.save(new Member());

        System.out.println(">>>>>" + memberRepository.findAll());
    }
}