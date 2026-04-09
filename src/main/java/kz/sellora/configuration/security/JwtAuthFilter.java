package kz.sellora.configuration.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.sellora.core.service.security.AccessTokenService;
import kz.sellora.core.service.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;
    private static final String PUBLIC = "/public";

//    private final JwtService jwtService;
    private final AccessTokenService accessTokenService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
//
//        String header = request.getHeader(AUTH_HEADER);
//
//        if (header == null || !header.startsWith(BEARER)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = header.substring(TOKEN_BEGIN_INDEX);
//
//        if (!jwtService.isValidToken(token)) {
//            log.warn("Invalid JWT token");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        if (!accessTokenService.exists(token)) {
//            log.warn("Token not found in Redis: {}", token.substring(0, Math.min(20, token.length())));
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.extractUsername(token));
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//            userDetails,
//            null,
//            userDetails.getAuthorities()
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith(PUBLIC);
    }

}
