package io.github.tenantmgt_android.Services;

public interface AuthStateService {
    String getAccessToken();
    String getRefreshToken();
}
