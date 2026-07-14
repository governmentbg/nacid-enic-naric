package bg.duosoft.nacidshared.web.config.security;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 22.06.2022
 * Time: 12:30
 */
public class CustomJwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String WRAPPER_CLAIM = "realm_access";
    private static final String ROLES_CLAIM = "roles";
    private static final String SCOPE_CLAIM = "scope";
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String SCOPE_PREFIX = "SCOPE_";

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        JSONObject access = (JSONObject)source.getClaims().get(WRAPPER_CLAIM);
        if(access != null){
            JSONArray rolesArray = (JSONArray)access.get(ROLES_CLAIM);
            if(rolesArray != null && !rolesArray.isEmpty()){
                rolesArray.forEach(role -> authorityList.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.toString())));
            }
        }
        String scope = (String)source.getClaims().get(SCOPE_CLAIM);
        if(scope != null){
            String[] scopes = scope.split("\s");
            Arrays.stream(scopes).forEach(singleScope -> authorityList.add(new SimpleGrantedAuthority(SCOPE_PREFIX + singleScope.trim())));
        }
        return authorityList;
    }
}
