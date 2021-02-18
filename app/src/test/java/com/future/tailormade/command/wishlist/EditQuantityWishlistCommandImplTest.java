package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.EditQuantityWishlistCommandImpl;
import com.future.tailormade.exceptions.NotFoundException;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.payload.request.wishlist.EditQuantityWishlistRequest;
import com.future.tailormade.payload.response.wishlist.EditQuantityWishlistResponse;
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

public class EditQuantityWishlistCommandImplTest extends BaseTest {

    @InjectMocks
    private EditQuantityWishlistCommandImpl command;

    @Mock
    private WishlistRepository wishlistRepository;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<String> wishlistIdCaptor;

    @Captor
    private ArgumentCaptor<Wishlist> wishlistCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(wishlistRepository);
    }

    @Test
    public void editQuantityWishlist_success() {
        Wishlist expectedWishlist = createWishlist(DESIGN_QUANTITY);
        Wishlist expectedSavedWishlist = createWishlist(WISHLIST_QUANTITY);
        EditQuantityWishlistResponse expectedResponse = createEditQuantityWishlistResponse();

        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        )).thenReturn(Mono.just(expectedWishlist));
        Mockito.when(
                wishlistRepository.save(ArgumentMatchers.any(Wishlist.class))
        ).thenReturn(Mono.just(expectedSavedWishlist));

        EditQuantityWishlistResponse actualResponse =
                command.execute(createEditQuantityWishlistRequest()).block();

        Mockito.verify(wishlistRepository).findByUserIdAndId(
                userIdCaptor.capture(),
                wishlistIdCaptor.capture()
        );
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);

        Mockito.verify(wishlistRepository).save(wishlistCaptor.capture());
        Assert.assertEquals(wishlistCaptor.getValue(), expectedSavedWishlist);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void editQuantityWishlist_notFound() {
        Mockito.when(wishlistRepository.findByUserIdAndId(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        )).thenReturn(Mono.empty());

        try {
            command.execute(createEditQuantityWishlistRequest()).block();
        } catch (NotFoundException exception) {
            Mockito.verify(wishlistRepository).findByUserIdAndId(
                    userIdCaptor.capture(),
                    wishlistIdCaptor.capture()
            );
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(wishlistIdCaptor.getValue(), WISHLIST_ID);
        }
    }

    private EditQuantityWishlistRequest createEditQuantityWishlistRequest() {
        return EditQuantityWishlistRequest.builder()
                .wishlistId(WISHLIST_ID)
                .quantity(WISHLIST_QUANTITY)
                .userId(USER_ID)
                .build();
    }
    
    private EditQuantityWishlistResponse createEditQuantityWishlistResponse() {
        return EditQuantityWishlistResponse.builder()
                .id(WISHLIST_ID)
                .quantity(WISHLIST_QUANTITY)
                .build();
    }

    private Wishlist createWishlist(Integer quantity) {
        return Wishlist.builder()
                .id(WISHLIST_ID)
                .userId(USER_ID)
                .quantity(quantity)
                .build();
    }
}
