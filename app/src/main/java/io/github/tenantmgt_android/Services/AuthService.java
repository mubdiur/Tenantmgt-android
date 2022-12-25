package io.github.tenantmgt_android.Services;

public class AuthService {
    private static AuthState auth = null;

    private AuthService() {}

    public static AuthState getAuth() {
        if (auth == null) {
            // perform auth
//            auth = new AuthState();
        }

        return auth;
    }

    public static void performAuth() {

    }
    protected static class AuthState implements AuthStateService {
        protected String accessToken = "";
        protected String refreshToken = "";

        @Override
        public String getAccessToken() {
            return accessToken;
        }

        @Override
        public String getRefreshToken() {
            return refreshToken;
        }
    }
}


