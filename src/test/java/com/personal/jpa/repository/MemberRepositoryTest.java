package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Direction;


import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

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

        // 해당 repository 안의 데이터 수를 조회
        Long count = memberRepository.count();

        // 해당 id 를 가진 데이터가 존재하는지 조회
        boolean exists = memberRepository.existsById(1L);

        members.forEach(System.out::println);


        // ================= 페이징
        // PageRequest 는 Page 의 구현체, 0 페이지의 3개 데이터를 가져온다.
        Page<Member> pageMembers = memberRepository.findAll(PageRequest.of(0,3));
        // Page 안에 이미 만들어져있는 여러 메소드들을 활용할수 있다.
        System.out.println("page : " + pageMembers);
        System.out.println("totalElements : " + pageMembers.getTotalElements());
        System.out.println("numberOfElements : " + pageMembers.getNumberOfElements());
        System.out.println("sort : " + pageMembers.getSort());
        System.out.println("size : " + pageMembers.getSize());


        // Query by example :  Entity 를 Example 로 만들고 Matcher 를 이용해서 매칭되는 데이터, 필요한 쿼리들을 만드는 방법
        ExampleMatcher marcher = ExampleMatcher.matching()
                .withIgnorePaths("name")    // 네임은 매칭하지 않겠다
                .withMatcher("email", endsWith());  // email 로 매칭을 하고 해당 인자로 끝나는것으로 매칭 하겠다
        Example<Member> example = Example.of(new Member("na", "personal"), marcher);

        memberRepository.findAll(example).forEach(System.out::println);


    }
}