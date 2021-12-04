package thl.gruppef.etankrest.etankrestapi.request;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;

    private String password;

    private String publicName;
}

