package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.DeleteWishlistCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.payload.request.wishlist.DeleteWishlistRequest;
import com.future.tailormade.payload.response.wishlist.DeleteWishlistResponse;
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

public class DeleteWishlistCommandImplTest extends BaseTest {

    @InjectMocks
    private DeleteWishlistCommandImpl command;

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
        DeleteWishlistResponse expectedResponse = createDeleteWishlistResponse();

        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(createWishlist()));
        Mockito.when(wishlistRepository
                .deleteById(ArgumentMatchers.anyString())).thenReturn(Mono.empty());

        DeleteWishlistResponse actualResponse =
                command.execute(createDeleteWishlistRequest()).block();

        Mockito.verify(wishlistRepository)
                .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);

        Mockito.verify(wishlistRepository).deleteById(wishlistIdCaptor.capture());
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void getWishlistById_notFound() {
        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString())
        ).thenReturn(Mono.empty());

        try {
            command.execute(createDeleteWishlistRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(wishlistRepository)
                    .findByUserIdAndId(userIdCaptor.capture(), wishlistIdCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);
        }
    }

    private DeleteWishlistRequest createDeleteWishlistRequest() {
        return DeleteWishlistRequest.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .build();
    }

    private DeleteWishlistResponse createDeleteWishlistResponse() {
        return DeleteWishlistResponse.builder()
                .wishlistId(WISHLIST_ID)
                .build();
    }

    private Wishlist createWishlist() {
        return Wishlist.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .build();
    }
}
