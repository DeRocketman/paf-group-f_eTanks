package thl.gruppef.etankrest.etankrestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    //Sorgt dafür, dass bei jedem Request geprüft wird ob Token vorhanden ist. Wenn Ja, gültig? Wenn ja, melde den User an. Sorge dafür, dass er den Request bekommt.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);

        //Gibt es einen Token und ist dieser gültig
        if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)){
            String username = jwtTokenProvider.getUsernameFromToken(jwt);
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            //Fehlt mir noch der Überblick. Sorgt wohl für PasswortVergleich etc.
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            //Fehlt mir noch der Überblick
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    //Hilfsmethode die uns den Token aus dem RequestHeader filtert.
    private String getJwtFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7); //Ohne Prefix von 7 Zeichen
        }
        return null;
    }

}
