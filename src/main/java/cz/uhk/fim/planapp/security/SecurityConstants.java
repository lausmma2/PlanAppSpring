package cz.uhk.fim.planapp.security;

public class SecurityConstants {

    //Important constants used in security
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3000000; //in miliseconds... 60_000 = 60 sec = 1 min
}
