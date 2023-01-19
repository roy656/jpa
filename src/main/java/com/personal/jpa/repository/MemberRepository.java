package com.personal.jpa.repository;

import com.personal.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);
    Member findByEmail(String email);

    Member findMemberById(Long id);

    List<Member> findAllByName(String name);

    List<Member> findMembersByEmail(String email);
}
