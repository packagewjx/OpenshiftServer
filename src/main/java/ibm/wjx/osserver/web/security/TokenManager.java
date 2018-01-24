package ibm.wjx.osserver.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Create Date: 1/16/18
 *
 * @author <a href="mailto:wu812730157@gmail.com">Wujunxian</a>
 * Description:
 */
public class TokenManager {

    private static final int TOKEN_EXPIRE_SECOND = 600;
    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);


    private Map<String, Token> tokenMap = new ConcurrentHashMap<>();
    private ScheduledExecutorService service;

    /**
     * initialize a timer thread to update token periodicity
     */
    public void init() {
        service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new TokenUpdateRunnable(), TOKEN_EXPIRE_SECOND, 30, TimeUnit.SECONDS);
    }

    public void destroy() {
        service.shutdown();
    }

    /**
     * Generate a new token, and store it.
     *
     * @return
     */
    public String newToken(UserDetails user) {
        String tokenString = generateTokenString();
        Token token = new Token(tokenString, System.currentTimeMillis(), user);
        tokenMap.put(tokenString, token);
        logger.debug("Generating a token {} for user {}", tokenString, user.getUsername());
        return tokenString;
    }

    public boolean validateToken(String tokenString) {
        return tokenMap.containsKey(tokenString);
    }

    /**
     * use this before the old token expire, update the token and return the new token value.
     *
     * @return
     */
    public boolean updateToken(String tokenString) {
        if (tokenMap.containsKey(tokenString)) {
            tokenMap.get(tokenString).setStartTime(System.currentTimeMillis());
            return true;
        } else {
            return false;
        }
    }

    public boolean removeToken(String tokenString) {
        return tokenMap.remove(tokenString) != null;
    }

    public UserDetails getUserFromTokenString(String token) {
        if (tokenMap.containsKey(token)) {
            return tokenMap.get(token).getUser();
        } else {
            return null;
        }
    }

    private String generateTokenString() {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("sha-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        String value = "IBMOpenshiftServer" + System.currentTimeMillis();
        byte[] digest = algorithm.digest(value.getBytes());
        StringBuilder builder = new StringBuilder(64);
        for (int i = 0; i < digest.length; i++) {
            int num = digest[i] & 0x000000ff;
            builder.append(Integer.toHexString(num));
        }
        return builder.toString();
    }

    private class TokenUpdateRunnable implements Runnable {
        @Override
        public void run() {
            logger.debug("Token Update Thread started");
            Set<String> toRemove = new HashSet<>();
            long current = System.currentTimeMillis();
            for (Map.Entry<String, Token> entry : tokenMap.entrySet()) {
                //if a token out-dated, just to remove it.
                if (entry.getValue().getStartTime() + TOKEN_EXPIRE_SECOND * 1000 < current) {
                    toRemove.add(entry.getKey());
                }
            }
            for (String token : toRemove) {
                logger.debug("Removing expired token {}", token);
                removeToken(token);
            }
        }
    }

}
