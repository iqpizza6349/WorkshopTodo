package com.tistory.workshop6349.workshoptodo.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    private String comment;

    private boolean checked;

    private LocalDateTime checkedDate;

    @ManyToOne
    @JoinColumn
    private Member member;

}
