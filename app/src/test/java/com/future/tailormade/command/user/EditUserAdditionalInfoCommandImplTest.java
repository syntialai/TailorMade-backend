package com.future.tailormade.command.user;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.user.impl.EditUserAdditionalInfoCommandImpl;
import com.future.tailormade.model.entity.user.Education;
import com.future.tailormade.model.entity.user.Occupation;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.user.EditUserAdditionalInfoRequest;
import com.future.tailormade.payload.response.user.EditUserAdditionalInfoResponse;
import com.future.tailormade.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EditUserAdditionalInfoCommandImplTest extends BaseTest {

    private static final String USER_EDUCATION_SCHOOL = "USER_SCHOOL";
    private static final String USER_OCCUPATION_COMPANY = "USER_COMPANY";

    @InjectMocks
    private EditUserAdditionalInfoCommandImpl command;

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
    public void testEditUserAdditionalInfo_success() {
        User user = createUser();
        Mono<User> expectedUser = Mono.just(user);

        user.setEducation(createUserEducation());
        user.setOccupation(createUserOccupation());
        EditUserAdditionalInfoResponse expectedUserResponse = createUserResponse(user);

        Mockito.when(userRepository.findById(USER_ID)).thenReturn(expectedUser);
        Mockito.when(userRepository.save(user)).thenReturn(Mono.just(user));

        EditUserAdditionalInfoResponse actualUserResponse = command.execute(createUserRequest()).block();

        Mockito.verify(userRepository).findById(USER_ID);
        Mockito.verify(userRepository).save(userSavedCaptor.capture());

        Assert.assertEquals(userSavedCaptor.getValue(), expectedUser.block());
        Assert.assertEquals(expectedUserResponse, actualUserResponse);
    }

    @Test(expected = Exception.class)
    public void testEditUserAdditionalInfo_notFound() {
        Mockito.when(userRepository.findById(USER_ID)).thenThrow(new Exception());

        try {
            command.execute(createUserRequest()).block();
        } catch (Exception exception) {
            Mockito.verify(userRepository).findById(USER_ID);
        }
    }

    private User createUser() {
        return User.builder().id(USER_ID).build();
    }

    private Education createUserEducation() {
        return Education.builder().school(USER_EDUCATION_SCHOOL).build();
    }

    private Occupation createUserOccupation() {
        return Occupation.builder().company(USER_OCCUPATION_COMPANY).build();
    }

    private EditUserAdditionalInfoRequest createUserRequest() {
        return EditUserAdditionalInfoRequest.builder()
                .id(USER_ID)
                .education(createUserEducation())
                .occupation(createUserOccupation())
                .build();
    }

    private EditUserAdditionalInfoResponse createUserResponse(User user) {
        EditUserAdditionalInfoResponse userResponse = EditUserAdditionalInfoResponse.builder().build();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
}
