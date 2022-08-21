package swm.toy.baseframework.domain.user;

import swm.toy.baseframework.infrastructure.converter.CodeValueConverter;

public class UserStatusConverter extends CodeValueConverter<UserStatus> {
    UserStatusConverter() {
        super(UserStatus.class);
    }
}
