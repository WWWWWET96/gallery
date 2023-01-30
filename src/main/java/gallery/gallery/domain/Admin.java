package gallery.gallery.domain;

import gallery.gallery.common.enums.Role;
import gallery.gallery.common.base.BaseEntity;
import gallery.gallery.dto.AdminDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "admin", uniqueConstraints = {@UniqueConstraint(columnNames = {
       "email", "nickname"}
)})
public class Admin extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String adminName;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(nullable = false, length = 30)
    private String phone;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role adminRole;

    @Column(nullable = false, unique = true, length = 45)
    @Email
    private String email;

    @Builder
    public Admin(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String nickname, String adminName, String password, String phone, Role adminRole, String email) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.nickname = nickname;
        this.adminName = adminName;
        this.password = password;
        this.phone = phone;
        this.adminRole = adminRole;
        this.email = email;
    }

    public static Admin of(AdminDto adminDto){
       return Admin.builder()
               .nickname(adminDto.getNickname())
               .adminName(adminDto.getAdminName())
               .password(adminDto.getPassword())
               .phone(adminDto.getPhone())
               .adminRole(adminDto.getAdminRole())
               .email(adminDto.getEmail())
               .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        return collectors;
    }

    @Override
    public String getUsername() {
        return nickname;
    }
    /**
     * 계정 만료 여부 반환 true: 만료되지 않았음
     * */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠금 여부 반환 true: 잠금되지 않았음
     * */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 패스워드의 만료 여부 반환 true: 만료되지 않았음
     * */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 계정 사용 가능 여부 반환 true : 사용 가능
     * */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
