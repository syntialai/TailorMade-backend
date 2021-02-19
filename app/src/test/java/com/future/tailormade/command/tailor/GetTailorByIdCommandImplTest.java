package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.GetTailorByIdCommandImpl;
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
import reactor.core.publisher.Mono;

public class GetTailorByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetTailorByIdCommandImpl command;

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
    public void testGetTailorById_success() {
        User user = createUser();
        Mono<User> expectedUser = Mono.just(user);
        GetUserByIdResponse expectedUserResponse = createUserResponse();

        Mockito.when(userRepository
                .findByIdAndRole(TAILOR_ID, TAILOR_ROLE)).thenReturn(expectedUser);

        GetUserByIdResponse actualUserResponse = command.execute(TAILOR_ID).block();

        Mockito.verify(userRepository).findByIdAndRole(TAILOR_ID, TAILOR_ROLE);
        Assert.assertEquals(expectedUserResponse, actualUserResponse);
    }

    @Test
    public void testGetTailorById_notFound() {
        Mockito.when(userRepository
                .findByIdAndRole(TAILOR_ID, TAILOR_ROLE)).thenReturn(Mono.empty());

        try {
            command.execute(TAILOR_ID).block();
        } catch (NotFoundException exception) {
            Mockito.verify(userRepository).findByIdAndRole(TAILOR_ID, TAILOR_ROLE);
        }
    }

    private GetUserByIdResponse createUserResponse() {
        return GetUserByIdResponse.builder()
                .id(TAILOR_ID)
                .role(TAILOR_ROLE)
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(TAILOR_ID)
                .role(TAILOR_ROLE)
                .build();
    }
}
