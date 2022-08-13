package swm.toy.baseframework.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import swm.toy.baseframework.domain.common.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.GenerationType.IDENTITY;

@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Profile profile;

    @Embedded
    private Password password;

    @JoinTable(name = "user_followings",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "followee_id", referencedColumnName = "id"))
    @OneToMany(cascade = REMOVE)
    private Set<User> followingUsers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities = new HashSet<>();

    static User of(Email email, UserName name, Password password) {
        return new User(email, new Profile(name), password, null);
    }

    static User of(Email email, UserName name, Password password, Authority authority) {
        return new User(email, new Profile(name), password, authority);
    }

    private User(Email email, Profile profile, Password password, Authority authority) {
        this.email = email;
        this.profile = profile;
        this.password = password;
        if (authority != null) {
            this.authorities.add(authority);
        }
    }

    protected User() {
    }

    User followUser(User followee) {
        followingUsers.add(followee);
        return this;
    }

    User unfollowUser(User followee) {
        followingUsers.remove(followee);
        return this;
    }

    Profile viewProfile(User user) {
        return user.profile.withFollowing(followingUsers.contains(user));
    }

    public Profile getProfile() {
        return profile;
    }

    boolean matchesPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return password.matchesPassword(rawPassword, passwordEncoder);
    }

    void changeEmail(Email email) {
        this.email = email;
    }

    void changePassword(Password password) {
        this.password = password;
    }

    void changeName(UserName userName) {
        profile.changeUserName(userName);
    }

    void changeBio(String bio) {
        profile.changeBio(bio);
    }

    void changeImage(Image image) {
        profile.changeImage(image);
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getName() {
        return profile.getUserName();
    }

    String getBio() {
        return profile.getBio();
    }

    Image getImage() {
        return profile.getImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final var user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
