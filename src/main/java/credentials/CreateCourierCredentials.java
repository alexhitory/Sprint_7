package credentials;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CreateCourierCredentials {

    private String login;
    private String password;
    private String firstName;

}