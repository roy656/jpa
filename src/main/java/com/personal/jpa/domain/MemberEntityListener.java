package com.personal.jpa.domain;


import com.personal.jpa.repository.MemberHistoryRepository;
import com.personal.jpa.support.BeanUtils;
import lombok.NoArgsConstructor;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@NoArgsConstructor
public class MemberEntityListener {

    @PrePersist
    @PreUpdate
    public void prePersisAndPreUpdate(Object o) {

        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBeans(MemberHistoryRepository.class);

        Member member = (Member)o;

        MemberHistory memberHistory = new MemberHistory();
        memberHistory.setMemberId(member.getId());
        memberHistory.setName(member.getName());
        memberHistory.setEmail(member.getEmail());

        memberHistoryRepository.save(memberHistory);
    }
}
