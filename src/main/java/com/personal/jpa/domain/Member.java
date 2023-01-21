package com.personal.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "member")    // 해당 클래스가 DB 의 테이블과 mapping 됨
@Builder
public class Member {

    @Id
    @GeneratedValue     // strategy = GenerationType.AUTO 가 default
    private Long id;
    @NonNull
    private String name;
    @NonNull
    @Column(unique = true)      // 해당 컬럼은 유니크 key
    private String email;
    @Enumerated(value = EnumType.STRING)        // default 가 ORDINAL(순서) 이기 때문에 잠재적 오류 발생 가능. STRING 으로 사용 권장.
    private Gender gender;
    @Column(nullable = false, updatable = false)       // null 을 허용하지 않음 , createdAt 값이 update 되지 않음
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Transient      // 해당 필드는 영속성처리에서 제외되기 때문에 DB 데이터에 반영되지 않음
    private String testData;    // DB 에서는 처리를 하지 않지만 객체로서 따로 사용을 원하는 필드값에 사용

}
