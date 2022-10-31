package gallery.gallery.common.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Selling {
    BEFORE("판매전"),
    OPEN("판매중"),
    CLOSED("판매완료");

    private final String selling;

}
