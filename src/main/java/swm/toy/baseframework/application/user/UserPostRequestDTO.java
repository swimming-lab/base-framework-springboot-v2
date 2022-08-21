package swm.toy.baseframework.application.user;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import swm.toy.baseframework.domain.user.Email;
import swm.toy.baseframework.domain.user.UserName;
import swm.toy.baseframework.domain.user.UserSignUpRequest;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("user")
@JsonTypeInfo(include = WRAPPER_OBJECT, use = NAME)
class UserPostRequestDTO {

    @javax.validation.constraints.Email String email;
    @NotBlank String username;
    @NotBlank String password;

    UserSignUpRequest toSignUpRequest() {
        return new UserSignUpRequest(new Email(email), new UserName(username), password);
    }
}
