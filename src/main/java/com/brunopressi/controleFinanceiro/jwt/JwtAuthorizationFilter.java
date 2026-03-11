package com.brunopressi.controleFinanceiro.jwt;

import com.brunopressi.controleFinanceiro.web.exception.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader(jwtUtils.getJWT_AUTHORIZATION());

        if (token == null || !token.startsWith(jwtUtils.getJWT_BEARER())) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!jwtUtils.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtils.getEmailFromToken(token);

        try {
            toAuthentication(request, email);
        }
        catch (EntityNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setStatus(401);
            ErrorMessage errorMessage = new ErrorMessage(
                    Instant.now().now(),
                    "Token JWT inválido ou ausente",
                    HttpStatus.UNAUTHORIZED.value(),
                    request.getRequestURI(),
                    HttpStatus.UNAUTHORIZED.getReasonPhrase()
            );

            response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
            return;
        }

        filterChain.doFilter(request , response);
    }

    private void toAuthentication(HttpServletRequest request, String email) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken
                .authenticated(userDetails,null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

}
