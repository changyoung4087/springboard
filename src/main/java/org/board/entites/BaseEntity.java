package org.board.entites;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass // 상위클래스에 호환될 수 있는공통
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity { // 하위클래스로만 사용

    @CreatedDate // 등록할 때 저장
    @Column(updatable = false) // update 시 false
    private LocalDateTime createAt;
    @LastModifiedDate // 수정할 때 저장
    @Column(insertable = false) // insert 시 false
    private LocalDateTime modifiedAt;
}

