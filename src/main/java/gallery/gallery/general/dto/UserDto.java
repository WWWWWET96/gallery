package gallery.gallery.general.dto;

import gallery.gallery.common.enums.AccountStatus;
import gallery.gallery.general.domain.User;
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

    public static User toEntity(UserDto userDto){
        return User.builder()
                .nickname(userDto.getNickname())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .accountStatus(userDto.getAccountStatus())
                .build();
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
    //비밀번호를 암호화한 후 넣기 위해 비밀번호 암호화 함수 만들기
    public UserDto toEncodePw(UserDto userDto, String encodePw){
        userDto.password = encodePw;
        return userDto;
    }
}

