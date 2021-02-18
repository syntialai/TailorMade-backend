package com.future.tailormade.command.wishlist;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.wishlist.impl.AddWishlistCommandImpl;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.wishlist.AddWishlistDesignRequest;
import com.future.tailormade.payload.request.wishlist.AddWishlistRequest;
import com.future.tailormade.payload.response.wishlist.AddWishlistResponse;
import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.repository.WishlistRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
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

public class AddWishlistCommandImplTest extends BaseTest {

    private static final String WISHLIST_GENERATED_ID = "WSLT_USER_0001";
    private static final String DESIGN_IMAGE = "Design image";
    private static final String TITLE_GENERATED = "USER_DESI";
    private static final Double DESIGN_PRICE = 50000.0;
    private static final Double DESIGN_DISCOUNT = 0.0;
    private static final String DESIGN_SIZE = "S";
    private static final String DESIGN_COLOR = "Yellow";

    @InjectMocks
    private AddWishlistCommandImpl command;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SequenceService sequenceService;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<RoleEnum> userRoleCaptor;

    @Captor
    private ArgumentCaptor<WishlistDesign> designCaptor;

    @Captor
    private ArgumentCaptor<String> wishlistTypeCaptor;

    @Captor
    private ArgumentCaptor<String> titleIdCaptor;

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
    public void addWishlistCommand_wishlistIsExist_success() {
        Wishlist wishlist = createWishlist(DESIGN_QUANTITY);
        Wishlist savedWishlist = createWishlist(WISHLIST_QUANTITY);
        AddWishlistResponse expectedResponse = createAddWishlistResponse();

        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(WISHLIST_GENERATED_ID));
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(RoleEnum.class)
        )).thenReturn(Mono.just(User.builder().build()));
        Mockito.when(wishlistRepository.findByUserIdAndDesign(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(WishlistDesign.class)
        )).thenReturn(Mono.just(wishlist));
        Mockito.when(wishlistRepository.save(
                ArgumentMatchers.any(Wishlist.class)
        )).thenReturn(Mono.just(savedWishlist));

        AddWishlistResponse actualResponse = command.execute(createAddWishlistRequest()).block();

        Mockito.verify(sequenceService)
                .generateId(titleIdCaptor.capture(), wishlistTypeCaptor.capture());
        Assert.assertEquals(titleIdCaptor.getValue(), TITLE_GENERATED);
        Assert.assertEquals(wishlistTypeCaptor.getValue(), SequenceGeneratorUtil.WISHLIST);

        Mockito.verify(userRepository)
                .findByIdAndRole(userIdCaptor.capture(), userRoleCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(userRoleCaptor.getValue(), USER_ROLE);

        Mockito.verify(wishlistRepository)
                .findByUserIdAndDesign(userIdCaptor.capture(), designCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(designCaptor.getValue(), wishlist.getDesign());

        Mockito.verify(wishlistRepository).save(wishlistCaptor.capture());
        Assert.assertEquals(wishlistCaptor.getValue(), savedWishlist);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void addWishlistCommand_wishlistIsNotExist_success() {
        Wishlist wishlist = createWishlist(DESIGN_QUANTITY);
        AddWishlistResponse expectedResponse = createAddWishlistResponse();

        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(WISHLIST_GENERATED_ID));
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(RoleEnum.class)
        )).thenReturn(Mono.just(User.builder().build()));
        Mockito.when(wishlistRepository.findByUserIdAndDesign(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(WishlistDesign.class)
        )).thenReturn(Mono.empty());
        Mockito.when(wishlistRepository.save(
                ArgumentMatchers.any(Wishlist.class))).thenReturn(Mono.just(wishlist));

        AddWishlistResponse actualResponse = command.execute(createAddWishlistRequest()).block();

        Mockito.verify(sequenceService)
                .generateId(titleIdCaptor.capture(), wishlistTypeCaptor.capture());
        Assert.assertEquals(titleIdCaptor.getValue(), TITLE_GENERATED);
        Assert.assertEquals(wishlistTypeCaptor.getValue(), SequenceGeneratorUtil.WISHLIST);

        Mockito.verify(userRepository)
                .findByIdAndRole(userIdCaptor.capture(), userRoleCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(userRoleCaptor.getValue(), USER_ROLE);

        Mockito.verify(wishlistRepository)
                .findByUserIdAndDesign(userIdCaptor.capture(), designCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        Assert.assertEquals(designCaptor.getValue(), wishlist.getDesign());

        Mockito.verify(wishlistRepository).save(wishlistCaptor.capture());
        Assert.assertEquals(wishlistCaptor.getValue(), wishlist);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void addWishlistCommand_userNotFound() {
        Mockito.when(sequenceService.generateId(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        ).thenReturn(Mono.just(WISHLIST_GENERATED_ID));
        Mockito.when(userRepository.findByIdAndRole(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(RoleEnum.class)
        )).thenReturn(Mono.empty());

        try {
            command.execute(createAddWishlistRequest()).block();
        } catch (UnauthorizedException exception) {
            Mockito.verify(sequenceService)
                    .generateId(titleIdCaptor.capture(), wishlistTypeCaptor.capture());
            Assert.assertEquals(titleIdCaptor.getValue(), TITLE_GENERATED);
            Assert.assertEquals(wishlistTypeCaptor.getValue(), SequenceGeneratorUtil.WISHLIST);

            Mockito.verify(userRepository)
                    .findByIdAndRole(userIdCaptor.capture(), userRoleCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
            Assert.assertEquals(userRoleCaptor.getValue(), USER_ROLE);
        }
    }

    private AddWishlistRequest createAddWishlistRequest() {
        return AddWishlistRequest.builder()
                .userId(USER_ID)
                .userName(USER_NAME)
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .quantity(DESIGN_QUANTITY)
                .design(createAddWishlistDesignRequest())
                .build();
    }

    private AddWishlistDesignRequest createAddWishlistDesignRequest() {
        return AddWishlistDesignRequest.builder()
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .size(DESIGN_SIZE)
                .color(DESIGN_COLOR)
                .build();
    }

    private AddWishlistResponse createAddWishlistResponse() {
        return AddWishlistResponse.builder()
                .wishlistId(WISHLIST_GENERATED_ID)
                .build();
    }

    private Wishlist createWishlist(Integer quantity) {
        return Wishlist.builder()
                .id(WISHLIST_GENERATED_ID)
                .userId(USER_ID)
                .userId(USER_ID)
                .userName(USER_NAME)
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .quantity(quantity)
                .design(createWishlistDesign())
                .build();
    }

    private WishlistDesign createWishlistDesign() {
        return WishlistDesign.builder()
                .tailorId(TAILOR_ID)
                .tailorName(TAILOR_NAME)
                .id(DESIGN_ID)
                .title(DESIGN_TITLE)
                .image(DESIGN_IMAGE)
                .price(DESIGN_PRICE)
                .discount(DESIGN_DISCOUNT)
                .size(DESIGN_SIZE)
                .color(DESIGN_COLOR)
                .build();
    }
}
