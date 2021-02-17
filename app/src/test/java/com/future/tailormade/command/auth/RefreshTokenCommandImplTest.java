package com.future.tailormade.command.auth;

import com.future.tailormade.BaseTest;
import com.future.tailormade.command.auth.impl.RefreshTokenCommandImpl;
import com.future.tailormade.component.JwtTokenProvider;
import com.future.tailormade.model.entity.auth.Token;
import com.future.tailormade.model.entity.user.User;
import com.future.tailormade.payload.request.auth.RefreshTokenRequest;
import com.future.tailormade.payload.response.auth.TokenResponse;
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
import reactor.core.publisher.Mono;

public class RefreshTokenCommandImplTest extends BaseTest {

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String REFRESH_TOKEN = "REFRESH_TOKEN";

    @InjectMocks
    private RefreshTokenCommandImpl command;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Captor
    private ArgumentCaptor<String> tokenCaptor;

    @Captor
    private ArgumentCaptor<String> userIdCaptor;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(
                userRepository,
                jwtTokenProvider
        );
    }

    @Test
    public void testRefreshToken_success() {
        User user = createUser();
        TokenResponse expectedResponse = createRefreshTokenResponse(createToken());

        Mockito.when(
                jwtTokenProvider.getUserIdFromToken(ArgumentMatchers.any(String.class))
        ).thenReturn(USER_ID);
        Mockito.when(
                userRepository.findById(ArgumentMatchers.any(String.class))
        ).thenReturn(Mono.just(user));
        Mockito.when(
                jwtTokenProvider.generateAccessToken(ArgumentMatchers.any(User.class))
        ).thenReturn(ACCESS_TOKEN);
        Mockito.when(
                jwtTokenProvider.generateRefreshToken(ArgumentMatchers.any(User.class))
        ).thenReturn(REFRESH_TOKEN);

        TokenResponse actualResponse = command.execute(createRefreshTokenRequest()).block();

        Mockito.verify(jwtTokenProvider).getUserIdFromToken(tokenCaptor.capture());
        Assert.assertEquals(tokenCaptor.getValue(), REFRESH_TOKEN);

        Mockito.verify(userRepository).findById(userIdCaptor.capture());
        Assert.assertEquals(userIdCaptor.getValue(), USER_ID);

        Mockito.verify(jwtTokenProvider).generateAccessToken(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue(), user);

        Mockito.verify(jwtTokenProvider).generateRefreshToken(userCaptor.capture());
        Assert.assertEquals(userCaptor.getValue(), user);

        Assert.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testRefreshToken_error() {
        User user = createUser();

        Mockito.when(
                jwtTokenProvider.getUserIdFromToken(ArgumentMatchers.any(String.class))
        ).thenReturn(USER_ID);
        Mockito.when(
                userRepository.findById(ArgumentMatchers.any(String.class))
        ).thenReturn(Mono.error(new Exception()));

        try {
            command.execute(createRefreshTokenRequest()).block();

            Mockito.verify(jwtTokenProvider).getUserIdFromToken(tokenCaptor.capture());
            Assert.assertEquals(tokenCaptor.getValue(), REFRESH_TOKEN);

            Mockito.verify(userRepository).findById(userIdCaptor.capture());
            Assert.assertEquals(userIdCaptor.getValue(), USER_ID);
        } catch (Exception ignored) {}
    }

    private User createUser() {
        return User.builder()
                .id(USER_ID)
                .role(USER_ROLE)
                .build();
    }

    private Token createToken() {
        return Token.builder()
                .access(ACCESS_TOKEN)
                .refresh(REFRESH_TOKEN)
                .build();
    }

    private RefreshTokenRequest createRefreshTokenRequest() {
        return RefreshTokenRequest.builder()
                .refreshToken(REFRESH_TOKEN)
                .build();
    }

    private TokenResponse createRefreshTokenResponse(Token token) {
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
