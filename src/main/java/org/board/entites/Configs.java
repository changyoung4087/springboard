package org.board.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Entity @Data
public class Configs {
    @Id
    private String code;
    @Lob
    private String value;
}
