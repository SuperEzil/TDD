package com.example.tdd.data.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 가입 정보 [Entity]
 */
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Comment("이름")
    @NotNull
    @Size(min = 1, max = 4, message = "사용자명의 크기는 1에서 4 사이여야 합니다")
    @Schema(minLength = 1, maxLength = 4, description = "사용자 명", example = "jack")
    private String name;



    @Comment("비밀번호")
    @NotNull
    @Size(min = 4, max = 8, message = "비밀번호의 크기는 4에서 8 사이여야 합니다")
    @Schema(minLength = 4, maxLength = 8, description = "비밀 번호", example = "1234")
    private String password;

}

