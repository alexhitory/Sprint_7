package credentials;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCourierCredentials {

    private String login;
    private String password;

}
