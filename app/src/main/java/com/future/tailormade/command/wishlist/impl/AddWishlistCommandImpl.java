package com.future.tailormade.command.wishlist.impl;

import com.future.tailormade.command.wishlist.AddWishlistCommand;
import com.future.tailormade.exceptions.UnauthorizedException;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.model.entity.wishlist.Wishlist;
import com.future.tailormade.model.entity.wishlist.WishlistDesign;
import com.future.tailormade.model.enums.RoleEnum;
import com.future.tailormade.payload.request.wishlist.AddWishlistDesignRequest;
import com.future.tailormade.payload.request.wishlist.AddWishlistRequest;
import com.future.tailormade.repository.UserRepository;
import com.future.tailormade.repository.WishlistRepository;
import com.future.tailormade.service.SequenceService;
import com.future.tailormade.utils.SequenceGeneratorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddWishlistCommandImpl implements AddWishlistCommand {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SequenceService sequenceService;

    @Override
    public Mono<Void> execute(AddWishlistRequest request) {
        String title = getTitle(request.getUserName(), request.getDesign().getTitle());
        return sequenceService.generateId(title, SequenceGeneratorUtil.WISHLIST)
                .map(id -> createWishlist(id, request))
                .flatMap(wishlist -> saveWishlist(request.getUserId(), wishlist))
                .then();
    }

    private Wishlist createWishlist(String id, AddWishlistRequest request) {
        Wishlist wishlist = Wishlist.builder()
                .id(id)
                .build();
        BeanUtils.copyProperties(request, wishlist);
        wishlist.setDesign(createWishlistDesign(
                request.getDesign(), request.getTailorId(), request.getTailorName()));
        return wishlist;
    }

    private WishlistDesign createWishlistDesign(
            AddWishlistDesignRequest designRequest, String tailorId, String tailorName
    ) {
        WishlistDesign wishlistDesign = WishlistDesign.builder()
                .tailorId(tailorId)
                .tailorName(tailorName)
                .build();
        BeanUtils.copyProperties(designRequest, wishlistDesign);
        return wishlistDesign;
    }

    private Mono<User> findUser(String userId) {
        return userRepository.findByIdAndRole(userId, RoleEnum.ROLE_USER)
                .switchIfEmpty(Mono.error(UnauthorizedException::new));
    }

    private String getTitle(String userName, String designTitle) {
        return SequenceGeneratorUtil.getName(userName)
                + "_" + SequenceGeneratorUtil.getName(designTitle);
    }

    private Mono<Wishlist> saveWishlist(String userId, Wishlist wishlist) {
        return findUser(userId)
                .flatMap(user -> getFinalWishlist(wishlist))
                .flatMap(finalWishlist -> wishlistRepository.save(finalWishlist));
    }

    private Mono<Wishlist> getFinalWishlist(Wishlist wishlist) {
        return wishlistRepository
                .findByUserIdAndDesign(wishlist.getUserId(), wishlist.getDesign())
                .map(existWishlist -> {
                    existWishlist.setQuantity(existWishlist.getQuantity() + wishlist.getQuantity());
                    return existWishlist;
                }).switchIfEmpty(Mono.just(wishlist));
    }
}
