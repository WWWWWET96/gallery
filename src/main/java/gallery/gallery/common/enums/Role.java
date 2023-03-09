package gallery.gallery.common.enums;

import lombok.Getter;

@Getter
public enum Role {
    /**
     *Spring Security에는 유저마다 권한을 관리하는 기능이 있음.
     * "ROLE_"로 시작하는 권한을 찾음
     */

    ADMIN;

}
