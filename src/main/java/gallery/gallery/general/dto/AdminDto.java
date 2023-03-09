package gallery.gallery.general.dto;

import gallery.gallery.common.enums.Role;
import gallery.gallery.general.domain.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminDto {
    private String nickname;
    private String adminName;
    private String password;
    private String phone;
    private Role adminRole;
    private String email;

    @Builder
    public AdminDto(String nickname, String adminName, String password, String phone, Role adminRole, String email) {
        this.nickname = nickname;
        this.adminName = adminName;
        this.password = password;
        this.phone = phone;
        this.adminRole = adminRole;
        this.email = email;
    }

   public static AdminDto of(Admin admin){
        return AdminDto.builder()
                .nickname(admin.getNickname())
                .adminName(admin.getAdminName())
                .password(admin.getPassword())
                .phone(admin.getPhone())
                .adminRole(admin.getAdminRole())
                .email(admin.getEmail())
                .build();
   }
}
