package com.future.tailormade.command.tailor;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.tailor.impl.GetTailorsCommandImpl;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.tailor.GetTailorsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.tailor.GetTailorsResponse;
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

public class GetTailorsCommandImplTest extends BaseTest {

    @InjectMocks
    private GetTailorsCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<String> nameCaptor;
    
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
    public void getTailors_success() {
        User expectedUser = createUser();
        BasePagingResponse<GetTailorsResponse> expectedResponse = createPaging(
                createGetTailorsResponse()
        );

        Mockito.when(userRepository.findAllByRoleAndNameStartsWith(
                ArgumentMatchers.any(RoleEnum.class),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Pageable.class))
        ).thenReturn(Flux.just(expectedUser));
        Mockito.when(userRepository.countAllByRoleAndNameStartsWith(
                ArgumentMatchers.any(RoleEnum.class), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetTailorsResponse> actualResponse = command
                .execute(createGetTailorsRequest())
                .block();

        Mockito.verify(userRepository).findAllByRoleAndNameStartsWith(
                userRoleCaptor.capture(), nameCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(nameCaptor.getValue(), TAILOR_NAME);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(userRepository)
                .countAllByRoleAndNameStartsWith(userRoleCaptor.capture(), nameCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(nameCaptor.getValue(), TAILOR_NAME);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getTailors_notFound() {
        BasePagingResponse<GetTailorsResponse> expectedResponse = createPaging();

        Mockito.when(userRepository.findAllByRoleAndNameStartsWith(
                ArgumentMatchers.any(RoleEnum.class),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(Pageable.class))
        ).thenReturn(Flux.empty());
        Mockito.when(userRepository.countAllByRoleAndNameStartsWith(
                ArgumentMatchers.any(RoleEnum.class), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetTailorsResponse> actualResponse = command
                .execute(createGetTailorsRequest())
                .block();

        Mockito.verify(userRepository).findAllByRoleAndNameStartsWith(
                userRoleCaptor.capture(), nameCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(nameCaptor.getValue(), TAILOR_NAME);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(userRepository)
                .countAllByRoleAndNameStartsWith(userRoleCaptor.capture(), nameCaptor.capture());
        Assert.assertEquals(userRoleCaptor.getValue(), TAILOR_ROLE);
        Assert.assertEquals(nameCaptor.getValue(), TAILOR_NAME);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetTailorsRequest createGetTailorsRequest() {
        return GetTailorsRequest.builder()
                .keyword(TAILOR_NAME)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetTailorsResponse createGetTailorsResponse() {
        return GetTailorsResponse.builder()
                .id(TAILOR_ID)
                .name(TAILOR_NAME)
                .build();
    }

    private User createUser() {
        return User.builder()
                .id(TAILOR_ID)
                .name(TAILOR_NAME)
                .build();
    }
}
