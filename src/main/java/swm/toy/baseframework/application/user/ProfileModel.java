package swm.toy.baseframework.application.user;

import lombok.Value;
import swm.toy.baseframework.domain.user.Profile;

import static java.lang.String.valueOf;

@Value
public class ProfileModel {

    ProfileModelNested profile;

    public static ProfileModel fromProfile(Profile profile) {
        return new ProfileModel(ProfileModelNested.fromProfile(profile));
    }

    @Value
    public static class ProfileModelNested {
        String username;
        String image;
        boolean following;

        public static ProfileModelNested fromProfile(Profile profile) {
            return new ProfileModelNested(
                    valueOf(profile.getUserName()),
                    valueOf(profile.getImage()),
                    profile.isFollowing());
        }
    }
}
