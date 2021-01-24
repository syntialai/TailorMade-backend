package com.future.tailormade.command.auth;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.auth.impl.SignUpCommandImpl;
import com.future.tailormade.component.CustomPasswordEncoder;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.GenderEnum;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.auth.SignUpRequest;
import com.future.tailormade.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUpCommandImplTest extends BaseTest {

    private static final String USER_NAME = "USER_NAME";
    private static final String USER_EMAIL = "user444@mail.com";
    private static final String USER_PASSWORD = "USER_PASSWORD";
    private static final String USER_PASSWORD_ENCODED = "USER_PASSWORD_ENCODED";
    private static final Long USER_BIRTH_DATE = 1500594930L;
    private static final GenderEnum USER_GENDER = GenderEnum.Female;
    private static final RoleEnum USER_ROLE = RoleEnum.ROLE_USER;

    @InjectMocks
    private SignUpCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomPasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userCreatedCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testSignUp_success() {
        User user = createUser();

        Mockito.when(passwordEncoder.encode(USER_PASSWORD)).thenReturn(USER_PASSWORD_ENCODED);
        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));

        try {
            command.execute(createSignUpRequest()).block();
        } catch (Exception exception) {
            Mockito.verify(passwordEncoder).encode(USER_PASSWORD);
            Mockito.verify(userRepository).save(userCreatedCaptor.capture());

            validateCaptor(user, userCreatedCaptor.getValue());
        }
    }

    private void validateCaptor(User user, User actualUser) {
        Assert.assertNotNull(actualUser.getId());
        Assert.assertEquals(user.getEmail(), actualUser.getEmail());
        Assert.assertEquals(user.getPassword(), actualUser.getPassword());
        Assert.assertEquals(user.getName(), actualUser.getName());
        Assert.assertEquals(user.getBirthDate(), actualUser.getBirthDate());
        Assert.assertEquals(user.getGender(), actualUser.getGender());
        Assert.assertEquals(user.getRole(), actualUser.getRole());
    }

    private User createUser() {
        return User.builder()
                .id(ArgumentMatchers.anyString())
                .name(USER_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD_ENCODED)
                .birthDate(USER_BIRTH_DATE)
                .gender(USER_GENDER)
                .role(USER_ROLE)
                .build();
    }

    private SignUpRequest createSignUpRequest() {
        return SignUpRequest.builder()
                .name(USER_NAME)
                .email(USER_EMAIL)
                .password(USER_PASSWORD)
                .birthDate(USER_BIRTH_DATE)
                .gender(USER_GENDER)
                .role(USER_ROLE)
                .build();
    }
}
