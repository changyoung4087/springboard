package org.board.entites;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseMemberEntity extends BaseEntity {

    @CreatedBy
    @Column(length = 40, updatable = false)
    private String createBy;
    @LastModifiedBy
    @Column(length = 40, insertable = false)
    private String modifiedBy;
}
