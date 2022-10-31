package gallery.gallery.domain.admin;

import gallery.gallery.common.Enum.Role;
import gallery.gallery.common.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "ADMIN", uniqueConstraints = {@UniqueConstraint(columnNames = {
       "email", "nickname"}
)})
public class Admin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(nullable = false, length = 45)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role admin_role;

    @Column(nullable = false, unique = true, length = 45)
    @Email
    private String email;

    @Builder
    public Admin(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String nickname, String password, Role admin_role, String email) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.admin_role = admin_role;
        this.email = email;
    }
}
