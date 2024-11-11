package com.example.vanille.configuration;

import com.example.vanille.model.Utilisateur;
import com.example.vanille.service.UtilisateurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

public class JWTInterceptor extends OncePerRequestFilter {

    @Autowired
    private JWTManager jwt;
    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJWTFromRequest(request);
        if (StringUtils.hasText(token)) {
            try {
                jwt.validateToken(token);
                int id = Integer.parseInt(jwt.getClaim(token, "id"));
                Utilisateur utilisateur = this.utilisateurService.getById(id);
                CustomUserDetails userDetails = new CustomUserDetails(utilisateur);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (AuthenticationCredentialsNotFoundException e) {
                logger.error("AuthenticationCredentialsNotFoundException: " + e.getMessage());
                HashMap<String, String> result = new HashMap<>();
                result.put("error", e.getMessage());

                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        filterChain.doFilter(request, response);
    }

    public static String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
