package swm.toy.baseframework.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserNameTest {

    @Test
    void when_userName_created_expect_toString_with_name() {
        final var userName = new UserName("name");

        assertThat(userName).hasToString("name");
    }

    @Test
    void when_userName_has_same_name_expect_equal_and_hashcode() {
        final var userName = new UserName("name");
        final var userNameWithSameName = new UserName("name");

        assertThat(userNameWithSameName).isEqualTo(userName).hasSameHashCodeAs(userName);
    }
}
