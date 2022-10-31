package gallery.gallery.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    /**
     *Spring Security에는 유저마다 권한을 관리하는 기능이 있음.
     * "ROLE_"로 시작하는 권한을 찾음
     */

    ADMIN("ADMIN");

  private final String role;

}
