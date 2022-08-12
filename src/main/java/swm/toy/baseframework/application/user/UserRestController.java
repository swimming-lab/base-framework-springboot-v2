package swm.toy.baseframework.application.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import swm.toy.baseframework.domain.jwt.JWTSerializer;
import swm.toy.baseframework.domain.user.Email;
import swm.toy.baseframework.domain.user.UserService;
import swm.toy.baseframework.infrastructure.jwt.UserJWTPayload;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.of;
import static swm.toy.baseframework.application.user.UserModel.fromUserAndToken;

@RestController
class UserRestController {

    private final UserService userService;
    private final JWTSerializer jwtSerializer;

    UserRestController(UserService userService, JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.jwtSerializer = jwtSerializer;
    }

    @PostMapping("/users")
    public UserModel postUser(@Valid @RequestBody UserPostRequestDTO dto) {
        final var userSaved = userService.signUp(dto.toSignUpRequest());
        return fromUserAndToken(userSaved, jwtSerializer.jwtFromUser(userSaved));
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserModel> loginUser(@Valid @RequestBody UserLoginRequestDTO dto) {
        return of(userService.login(new Email(dto.getEmail()), dto.getPassword())
                .map(user -> fromUserAndToken(user, jwtSerializer.jwtFromUser(user))));
    }

    @GetMapping("/user")
    public ResponseEntity<UserModel> getUser(@AuthenticationPrincipal UserJWTPayload jwtPayload) {
        return of(userService.findById(jwtPayload.getUserId())
                .map(user -> fromUserAndToken(user, getCurrentCredential())));
    }

    @PutMapping("/user")
    public UserModel putUser(@AuthenticationPrincipal UserJWTPayload jwtPayload,
                             @Valid @RequestBody UserPutRequestDTO dto) {
        final var userUpdated = userService.updateUser(jwtPayload.getUserId(), dto.toUpdateRequest());
        return fromUserAndToken(userUpdated, getCurrentCredential());
    }

    private static String getCurrentCredential() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getCredentials()
                .toString();
    }
}
