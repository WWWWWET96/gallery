package gallery.gallery.domain;


import gallery.gallery.common.enums.AccountStatus;
import gallery.gallery.common.base.BaseEntity;
import gallery.gallery.dto.UserDto;
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


/**
 *PK: id
 * UK: userId, phone, email
 * */
@Entity
@NoArgsConstructor
@Getter
@Table(name = "USERS", uniqueConstraints = {@UniqueConstraint(columnNames = {
        "nickname", "email"}
)})
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String nickname; //로그인 시 ID

    @Column( nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 45)
    private String password;

    //핸드폰번호는 국가번호 등이 들어갈 수도 있으니까 int말고 String으로
    @Column(nullable = false, length = 30)
    private String phone;

    @Column(nullable = false, unique = true, length = 45)
    @Email
    private String email;

    /**@Enumerated(value = EnumType.STRING)
     * -> enum의 값을 index가아닌 텍스트 값 그대로 저장해주는 어노테이션
     * 없을 경우,  enum값이 아닌 인덱스 값이 db에 저장됨
     * */
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AccountStatus accountStatus;

    @Builder
    public User(LocalDateTime createdDate, LocalDateTime modifiedDate, Long id, String nickname, String name, String password, String phone, String email, AccountStatus accountStatus) {
        super(createdDate, modifiedDate);
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.accountStatus = accountStatus;
    }

    public static User of(UserDto userDto){
        return User.builder()
                .nickname(userDto.getNickname())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .accountStatus(userDto.getAccountStatus())
                .build();
    }
    /**
     * 사용자의 권한을 콜렉션 형태로 반환*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        return collectors;
    }
    /**
     * 사용자의 id를 반환(unique한 값)
     * */
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
