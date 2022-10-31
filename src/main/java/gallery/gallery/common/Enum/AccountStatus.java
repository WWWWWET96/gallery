package gallery.gallery.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountStatus {
    WAITING("승인 대기"),
    MEMBER("승인 완료"),
    DELETED("계정 삭제"),
    UNAVAILABLE("휴면 계정");

    private final String accountStatus;

}
