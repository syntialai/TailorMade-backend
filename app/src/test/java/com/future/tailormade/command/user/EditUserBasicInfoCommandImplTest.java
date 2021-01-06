package com.future.tailormade.command.user;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.user.impl.EditUserBasicInfoCommandImpl;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.user.EditUserBasicInfoRequest;
import com.future.tailormade.payload.response.user.EditUserBasicInfoResponse;
import com.future.tailormade.repository.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EditUserBasicInfoCommandImplTest extends BaseTest {

    private static final String USER_ID = UUID.randomUUID().toString();
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_EMAIL = "user@mail.com";
    private static final String USER_PHONE_NUMBER = "081990333333";

    @InjectMocks
    private EditUserBasicInfoCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> userSavedCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testEditUserBasicInfo_success() {
        User user = createUser();
        Mono<User> expectedUser = Mono.just(user);

        user.setPhoneNumber(USER_PHONE_NUMBER);
        EditUserBasicInfoResponse expectedUserResponse = createUserResponse(user);

        Mockito.when(userRepository.findById(USER_ID)).thenReturn(expectedUser);
        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));

        EditUserBasicInfoResponse actualUserResponse = command.execute(createUserRequest()).block();

        Mockito.verify(userRepository).findById(USER_ID);
        Mockito.verify(userRepository).save(userSavedCaptor.capture());

        Assert.assertEquals(userSavedCaptor.getValue(), expectedUser.block());
        Assert.assertEquals(expectedUserResponse, actualUserResponse);
    }

    @Test(expected = Exception.class)
    public void testEditUserBasicInfo_notFound() {
        Mockito.when(userRepository.findById(USER_ID)).thenThrow(new Exception());

        try {
            command.execute(createUserRequest()).block();
        } catch (Exception exception) {
            Mockito.verify(userRepository).findById(USER_ID);
        }
    }

    private User createUser() {
        return User.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .email(USER_EMAIL)
                .build();
    }

    private EditUserBasicInfoRequest createUserRequest() {
        return EditUserBasicInfoRequest.builder()
                .id(USER_ID)
                .name(USER_NAME)
                .phoneNumber(USER_PHONE_NUMBER)
                .build();
    }

    private EditUserBasicInfoResponse createUserResponse(User user) {
        EditUserBasicInfoResponse userResponse = EditUserBasicInfoResponse.builder().build();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
