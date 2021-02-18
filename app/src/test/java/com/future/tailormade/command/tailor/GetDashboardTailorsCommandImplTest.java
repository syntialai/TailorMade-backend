package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.GetDashboardTailorsCommandImpl;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.GetDashboardTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetDashboardTailorsResponse;
import com.future.tailormade.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetDashboardTailorsCommandImplTest extends BaseTest {

    @InjectMocks
    private GetDashboardTailorsCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<RoleEnum> userRoleCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void getDashboardTailors_success() {
        User expectedUser = createUser();
        BasePagingResponse<GetDashboardTailorsResponse> expectedResponse = createPaging(
                createGetDashboardTailorsResponse()
        );

        Mockito.when(userRepository.findAllByRole(
                ArgumentMatchers.any(RoleEnum.class), ArgumentMatchers.any(Pageable.class))
        ).thenReturn(Flux.just(expectedUser));
        Mockito.when(
                userRepository.countAllByRole(ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetDashboardTailorsResponse> actualResponse = command
                .execute(createGetDashboardTailorsRequest())
                .block();

        Mockito.verify(userRepository)
                .findAllByRole(userRoleCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(userRepository).countAllByRole(userRoleCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getDashboardTailors_notFound() {
        BasePagingResponse<GetDashboardTailorsResponse> expectedResponse = createPaging();

        Mockito.when(
                userRepository.findAllByRole(
                        ArgumentMatchers.any(RoleEnum.class),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.empty());
        Mockito.when(
                userRepository.countAllByRole(ArgumentMatchers.any(RoleEnum.class))
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetDashboardTailorsResponse> actualResponse = command
                .execute(createGetDashboardTailorsRequest())
                .block();

        Mockito.verify(userRepository)
                .findAllByRole(userRoleCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(userRepository).countAllByRole(userRoleCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetDashboardTailorsRequest createGetDashboardTailorsRequest() {
        return GetDashboardTailorsRequest.builder()
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetDashboardTailorsResponse createGetDashboardTailorsResponse() {
        return GetDashboardTailorsResponse.builder()
                .id(TAILOR_ID)
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(TAILOR_ID)
                .build();
    }
}
