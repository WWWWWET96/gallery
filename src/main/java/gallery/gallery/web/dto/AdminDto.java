package gallery.gallery.web.dto;

import gallery.gallery.common.Enum.Role;
import gallery.gallery.domain.admin.Admin;
import gallery.gallery.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminDto {
    private String nickname;
    private String admin_name;
    private String password;
    private String phone;
    private Role admin_role;
    private String email;

    @Builder
    public AdminDto(String nickname, String admin_name, String password, String phone, Role admin_role, String email) {
        this.nickname = nickname;
        this.admin_name = admin_name;
        this.password = password;
        this.phone = phone;
        this.admin_role = admin_role;
        this.email = email;
    }

   public static AdminDto of(Admin admin){
        return AdminDto.builder()
                .nickname(admin.getNickname())
                .admin_name(admin.getAdmin_name())
                .password(admin.getPassword())
                .phone(admin.getPhone())
                .admin_role(admin.getAdmin_role())
                .email(admin.getEmail())
                .build();
   }
}
