package com.personal.jpa.domain;

import com.personal.jpa.domain.listener.MemberEntityListener;
import lombok.*;

import javax.persistence.*;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "member")    // 해당 클래스가 DB 의 테이블과 mapping 됨
// main method 의 클래스에 @EnableJPAAuditing 을 적용하고 기본으로 spring 에서 마련된 AuditingEntityListener 사용 가능
@EntityListeners(value = MemberEntityListener.class)
@Builder
public class Member extends BaseEntity {

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

    @Transient      // 해당 필드는 영속성처리에서 제외되기 때문에 DB 데이터에 반영되지 않음
    private String testData;    // DB 에서는 처리를 하지 않지만 객체로서 따로 사용을 원하는 필드값에 사용



//    // Entity Listener
//    @PrePersist     // insert query 동작되기 전에 실행.
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//    @PostPersist    // insert query 동작된 후 실행.
//    public void postPersist() {
//        this.updatedAt = LocalDateTime.now();
//    }
//    @PreUpdate      //  update query 동작 전 실행
//    public void preUpdate() {
//        System.out.println("----------preUpdate");
//    }
//    @PostUpdate     // update query 동작 후 실행
//    public void postUpdate() {
//        System.out.println("----------postUpdate");
//    }
//    @PreRemove      // delete query 동작 전 실행
//    public void preRemove() {
//        System.out.println("----------preRemove");
//    }
//    @PostRemove     // delete query 동작 후 실행
//    public void postRemove() {
//        System.out.println("----------postRemove");
//    }
//    @PostLoad       // select query 가 실행된 후 실행.
//    public void postLoad() {
//        System.out.println("----------postLoad");
//    }

}
