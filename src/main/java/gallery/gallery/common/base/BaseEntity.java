package gallery.gallery.common.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor //자식 클래스에게 필드 넘겨주기 위해
@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public abstract class BaseEntity {
    //JPA Audit사용
    @CreatedDate
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    @CreatedDate
    @Column(name = "modified_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedDate;
}
