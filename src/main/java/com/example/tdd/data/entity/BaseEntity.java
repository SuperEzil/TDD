package com.example.tdd.data.entity;

import com.example.tdd.configuration.PropertiesAuditorAware;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 기본 엔티티 <br/>
 *
 * {@link PropertiesAuditorAware propertiesAuditorAware }
 */
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class BaseEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iD", nullable = false, updatable = false)
    private Integer iD;

    
    @Comment("생성자")
    @Column(name = "createdByUser", nullable = false, updatable = false)
    @CreatedBy
    private String createdByUser;

    @Comment("생성 시간")
    @Column(name = "creationTime", nullable = false, updatable = false)
    @CreatedDate
    private Timestamp creationTime;

    @Comment("마지막 변경자")
    @Column(name = "lastModifiedByUser", nullable = false)
    @LastModifiedBy
    private String lastModifiedByUser;

    @Comment("수정 시간")
    @Column(name = "lastModificationTime")
    @LastModifiedDate
    private Timestamp lastModificationTime;
}
