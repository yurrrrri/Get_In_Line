package com.fastcampus.corona.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@Table(indexes = {
        @Index(columnList = "adminId"),
        @Index(columnList = "placeId"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class AdminPlaceMap {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private Long adminId;

    @Setter
    @Column(nullable = false)
    private Long placeId;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    protected AdminPlaceMap(Long adminId, Long placeId) {
        this.adminId = adminId;
        this.placeId = placeId;
    }

    public static AdminPlaceMap of(Long adminId, Long placeId) {
        return new AdminPlaceMap(adminId, placeId);
    }
}