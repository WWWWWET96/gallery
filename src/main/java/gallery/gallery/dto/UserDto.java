package gallery.gallery.dto;

import gallery.gallery.common.Enum.AccountStatus;
import gallery.gallery.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private String nickname;
    private String name;
    private String password;
    private String phone;
    private String email;
    private AccountStatus accountStatus= AccountStatus.WAITING;

    @Builder
    public UserDto(String nickname, String name, String password, String phone, String email, AccountStatus accountStatus) {
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.accountStatus = accountStatus;
    }

    public static UserDto of(User user){
        return UserDto.builder()
                .nickname(user.getNickname())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .accountStatus(user.getAccountStatus())
                .build();
    }

}

