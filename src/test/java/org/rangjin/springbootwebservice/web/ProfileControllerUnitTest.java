package org.rangjin.springbootwebservice.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void searchRealProfile() {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

//    @Test
//    public void doesNotExistRealProfile() {
//        // given
//        String expectedProfile = "oauth";
//        MockEnvironment env = new MockEnvironment();
//        env.addActiveProfile(expectedProfile);
//        env.addActiveProfile("real-db");
//
//        ProfileController controller = new ProfileController(env);
//
//        // when
//        String profile = controller.profile();
//
//        // then
//        assertThat(profile).isEqualTo(expectedProfile);
//    }

    @Test
    public void doesNotExistActiveProfile() {
        // given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertThat(profile).isEqualTo(expectedProfile);
    }

}