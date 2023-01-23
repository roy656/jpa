package com.personal.jpa.domain;

import com.personal.jpa.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass       // 해당 class 의 필드를 -> 상속받는 Entity 의 Column 으로 포함시켜준다.
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {
    @CreatedDate    // 자동으로 생성 시간값을 생성해주는 annotation
    private LocalDateTime createdAt;
    @LastModifiedDate   // 자동으로 수정 시간값을 생성해주는 annotation
    private LocalDateTime updatedAt;
}
