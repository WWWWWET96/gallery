package gallery.gallery.domain.admin;

import gallery.gallery.common.Enum.Role;
import gallery.gallery.common.base.BaseEntity;
import gallery.gallery.web.dto.AdminDto;
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
@Table(name = "ADMINS", uniqueConstraints = {@UniqueConstraint(columnNames = {
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
    private String admin_name;

    @Column(nullable = false, length = 45)
    private String password;

    @Column(nullable = false, length = 30)
    private String phone;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role admin_role;

    @Column(nullable = false, unique = true, length = 45)
    @Email
    private String email;

    @Builder
    public Admin(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String nickname, String admin_name, String password, String phone, Role admin_role, String email) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.nickname = nickname;
        this.admin_name = admin_name;
        this.password = password;
        this.phone = phone;
        this.admin_role = admin_role;
        this.email = email;
    }

    public static Admin of(AdminDto adminDto){
       return Admin.builder()
               .nickname(adminDto.getNickname())
               .admin_name(adminDto.getAdmin_name())
               .password(adminDto.getPassword())
               .phone(adminDto.getPhone())
               .admin_role(adminDto.getAdmin_role())
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
