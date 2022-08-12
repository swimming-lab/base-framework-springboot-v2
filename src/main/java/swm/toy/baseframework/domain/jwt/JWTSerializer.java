package swm.toy.baseframework.domain.jwt;


import swm.toy.baseframework.domain.user.User;

public interface JWTSerializer {

    String jwtFromUser(User user);

}
