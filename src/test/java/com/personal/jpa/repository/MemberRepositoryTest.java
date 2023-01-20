package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.*;

import java.time.LocalDateTime;
import java.util.List;

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
        List<Member> members2 = memberRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

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
        Page<Member> pageMembers = memberRepository.findAll(PageRequest.of(0, 3));
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

    @Test
    void select () {

        // repository 에 필요한 형태로 선언한 query method 들을 사용
        memberRepository.findByName("Roy");
        memberRepository.findByEmail("roy@naver.com");
        memberRepository.findMemberById(3L);
        memberRepository.findMembersByEmail("mari@naver.com");

        // 두가지 조건 모두 충족
        memberRepository.findByNameAndEmail("Roy", "roy@naver.com");
        // 두가지중 한가지 조건 충족
        memberRepository.findByNameOrEmail("Roy", "1111@naver.com");
        // 시간에 대한 조건 충족
        memberRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L));   // 현재 시간에서 하루 전
        // After 와 Before 는 단순 숫자로 비교해 동작 가능하지만 가독성을 위해서 되도록이면 날짜와 시간의 경우에만 사용하는것을 권장
        // created_at > 2
        memberRepository.findByIdAfter(2L);
        // GreaterThan 은 모든 숫자값,날짜값 등에 범용적으로 사용하기 좋음
        // created_at > time
        memberRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L));
        // created_at >= time
        memberRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L));

        memberRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L));
        // between 은 양 조건을 포함 하는 결과를 가져옴
        memberRepository.findByIdBetween(2L, 3L);
        // between 쿼리와 동일한 조건
        memberRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L);

        memberRepository.findByIdIsNotNull();
        // List 의 name 들을 포함하는 name 을 갖고있는 결과값 조회, Or 과 비슷하게 동작
        memberRepository.findByNameIn(Lists.newArrayList("Roy", "Olivia"));

        // Oli 문자의 앞뒤로 양방향 like 검색
        memberRepository.findByNameLike("%Oli%");
        // 밑의 3가지는 위의 Like method 를 한번더 mapping 한것과 같다
        memberRepository.findByNameStartingWith("Mari");
        memberRepository.findByNameEndingWith("lin");
        memberRepository.findByNameContains("ivi");
    }

    void pagingAndSortingTesy() {

        // 첫번째 Collin 을 조회
        memberRepository.findTopByName("Collin");
        // 역순으로 정렬하여 첫번째 Collin 을 조회.  = 마지막 Collin
        memberRepository.findTopByNameOrderByIdDesc("Collin");
        // Id 는 역순, Email 은 정순으로 정렬하여 첫번째 Roy 조회    // OrderBy 뒤에는 And 를 쓰지않고 요소를 나열함
        memberRepository.findFirstByNameOrderByIdDescEmailAsc("Roy");
        // 위의 조건과 동일한 정렬, 검색    // Sort class 를 활용하여 추가적인 paramegter 제공
        memberRepository.findFirstByName("Roy", Sort.by(Order.desc("id"), Order.asc("email")));


    }
}