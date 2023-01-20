package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);
    Member findByEmail(String email);
    Member findMemberById(Long id);
    List<Member> findAllByName(String name);
    List<Member> findMembersByEmail(String email);
    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByNameOrEmail(String name, String email);
    List<Member> findByCreatedAtAfter(LocalDateTime yesterday);
    List<Member> findByIdAfter(Long id);
    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<Member> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
    List<Member> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);
    List<Member> findByIdBetween(Long id1, Long id2);
    List<Member> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);
    List<Member> findByIdIsNotNull();
    List<Member> findByNameIn(List<String> names);
    List<Member> findByNameStartingWith(String name);
    List<Member> findByNameEndingWith(String name);
    List<Member> findByNameContains(String name);
    List<Member> findByNameLike(String name);

    List<Member> findTopByName(String name);
    List<Member> findTopByNameOrderByIdDesc(String name);
    List<Member> findFirstByNameOrderByIdDescEmailAsc(String name);
    List<Member> findFirstByName(String name, Sort sort);
}
