package swm.toy.baseframework.application.admin;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swm.toy.baseframework.domain.jwt.JWTSerializer;
import swm.toy.baseframework.domain.user.UserService;
import swm.toy.baseframework.infrastructure.jwt.UserJWTPayload;

@RequestMapping("/admin")
@RestController
public class AdminRestController {

    private final UserService userService;
    private final JWTSerializer jwtSerializer;

    AdminRestController(UserService userService, JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.jwtSerializer = jwtSerializer;
    }

    @Operation(summary = "Admin api sample", hidden = true)
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/users")
    public String getSomething(@AuthenticationPrincipal UserJWTPayload jwtPayload) {
        return "This is Admin api sample";
    }
}
