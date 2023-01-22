package com.personal.jpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(value = MyEntityListener.class)
public class Book implements Auditable{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
