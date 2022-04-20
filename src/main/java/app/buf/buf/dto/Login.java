package app.buf.buf.dto;

import lombok.Data;

@Data
public class Login {
    private String usernameOrEmail;
    private String password;
}
