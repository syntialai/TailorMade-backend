package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.GetWishlistByIdCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.payload.request.wishlist.GetWishlistByIdRequest;
import com.future.tailormade.payload.response.wishlist.GetWishlistByIdResponse;
import com.future.tailormade.payload.response.wishlist.GetWishlistDesignByIdResponse;
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
import reactor.core.publisher.Mono;

public class GetWishlistByIdCommandImplTest extends BaseTest {

    @InjectMocks
    private GetWishlistByIdCommandImpl command;

    @Mock
    private WishlistRepository wishlistRepository;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<String> wishlistIdCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(wishlistRepository);
    }

    @Test
    public void getWishlistById_success() {
        GetWishlistByIdResponse expectedResponse = createGetWishlistByIdResponse();

        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(createWishlist()));

        GetWishlistByIdResponse actualResponse =
                command.execute(createGetWishlistByIdRequest()).block();

        Mockito.verify(wishlistRepository)
                .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getWishlistById_notFound() {
        GetWishlistByIdResponse expectedResponse = createGetWishlistByIdResponse();

        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());

        try {
            command.execute(createGetWishlistByIdRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(wishlistRepository)
                    .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);
        }
    }

    private GetWishlistByIdRequest createGetWishlistByIdRequest() {
        return GetWishlistByIdRequest.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .build();
    }

    private GetWishlistByIdResponse createGetWishlistByIdResponse() {
        return GetWishlistByIdResponse.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .design(createGetWishlistDesignByIdResponse())
                .build();
    }

    private GetWishlistDesignByIdResponse createGetWishlistDesignByIdResponse() {
        return GetWishlistDesignByIdResponse.builder()
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
