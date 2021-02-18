package com.future.tailormade.command.user;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.user.impl.GetUserByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.response.user.GetUserByIdResponse;
import com.future.tailormade.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import reactor.core.publisher.Mono;

public class GetUserByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetUserByIdCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testGetUserById_success() {
        User user = createUser();
        Mono<User> expectedUser = Mono.just(user);
        GetUserByIdResponse expectedUserResponse = createUserResponse(user);

        Mockito.when(userRepository.findById(USER_ID)).thenReturn(expectedUser);

        GetUserByIdResponse actualUserResponse = command.execute(USER_ID).block();

        Mockito.verify(userRepository).findById(USER_ID);
        Assert.assertEquals(expectedUserResponse, actualUserResponse);
    }

    @Test
    public void testGetUserById_notFound() {
        Mockito.when(userRepository.findById(USER_ID)).thenReturn(Mono.empty());

        try {
            command.execute(USER_ID).block();
        } catch (NotFoundException exception) {
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

    private GetUserByIdResponse createUserResponse(User user) {
        GetUserByIdResponse userResponse = GetUserByIdResponse.builder().build();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
