package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.GetWishlistsCommandImpl;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.payload.request.wishlist.GetWishlistsRequest;
import com.future.tailormade.payload.response.base.BasePagingResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistsDesignResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistsResponse;
import com.future.tailormade.repository.WishlistRepository;
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

public class GetWishlistsCommandImplTest extends BaseTest {

    @InjectMocks
    private GetWishlistsCommandImpl command;

    @Mock
    private WishlistRepository wishlistRepository;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(wishlistRepository);
    }

    @Test
    public void getWishlists_success() {
        Wishlist expectedWishlist = createWishlist();
        BasePagingResponse<GetWishlistsResponse> expectedResponse = createPaging(
                createGetWishlistsResponse()
        );

        Mockito.when(
                wishlistRepository.findAllByUserId(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.just(expectedWishlist));
        Mockito.when(
                wishlistRepository.countAllByUserId(ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_COUNT));

        BasePagingResponse<GetWishlistsResponse> actualResponse = command
                .execute(createGetWishlistsRequest())
                .block();

        Mockito.verify(wishlistRepository)
                .findAllByUserId(userIdCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(wishlistRepository).countAllByUserId(userIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getWishlists_notFound() {
        BasePagingResponse<GetWishlistsResponse> expectedResponse = createPaging();

        Mockito.when(
                wishlistRepository.findAllByUserId(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(Pageable.class)
                )
        ).thenReturn(Flux.empty());
        Mockito.when(
                wishlistRepository.countAllByUserId(ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(ITEM_EMPTY_COUNT));

        BasePagingResponse<GetWishlistsResponse> actualResponse = command
                .execute(createGetWishlistsRequest())
                .block();

        Mockito.verify(wishlistRepository)
                .findAllByUserId(userIdCaptor.capture(), pageableCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(pageableCaptor.getValue(), getPageable());

        Mockito.verify(wishlistRepository).countAllByUserId(userIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    private GetWishlistsRequest createGetWishlistsRequest() {
        return GetWishlistsRequest.builder()
                .userId(USER_ID)
                .page(PAGE)
                .itemPerPage(ITEM_PER_PAGE)
                .build();
    }

    private GetWishlistsResponse createGetWishlistsResponse() {
        return GetWishlistsResponse.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .design(createGetWishlistsDesignResponse())
                .build();
    }

    private GetWishlistsDesignResponse createGetWishlistsDesignResponse() {
        return GetWishlistsDesignResponse.builder()
                .id(DESIGN_ID)
                .build();
    }

    private Wishlist createWishlist() {
        return Wishlist.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .design(createWishlistDesign())
                .build();
    }

    private WishlistDesign createWishlistDesign() {
        return WishlistDesign.builder()
                .id(DESIGN_ID)
                .build();
    }
}
