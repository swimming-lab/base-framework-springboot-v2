package swm.toy.baseframework.domain.user;

import swm.toy.baseframework.infrastructure.converter.CodeValue;

public enum UserStatus implements CodeValue {
    USED("00", "사용중"),
    UNUSED("01", "사용안함"),
    REMOVE("02", "삭제"),
    ;

    private String code;
    private String value;

    UserStatus(String code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}
