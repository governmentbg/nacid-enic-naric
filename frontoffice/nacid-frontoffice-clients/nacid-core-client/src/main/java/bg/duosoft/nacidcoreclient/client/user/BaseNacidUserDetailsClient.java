package bg.duosoft.nacidcoreclient.client.user;

import bg.duosoft.nacidfrontofficedto.user.NacidUserDetailsDTO;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 26.07.2022
 * Time: 16:38
 */
public interface BaseNacidUserDetailsClient {

    @GetMapping
    NacidUserDetailsDTO getCurrentNacidUserDetails();

}
