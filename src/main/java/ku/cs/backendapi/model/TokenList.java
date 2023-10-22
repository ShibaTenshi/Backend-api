package ku.cs.backendapi.model;

import ku.cs.backendapi.exception.TokenException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenList {
    private final Map<UUID, UserToken> tokenMap;

    public TokenList() {
        tokenMap = new HashMap<>();
    }

    public UUID addToken(UUID userId) {
        UUID tokenId = UUID.randomUUID();
        for(UUID keyUuid : tokenMap.keySet()) {
            if(tokenMap.get(keyUuid).getUuid().equals(userId)) {
                tokenMap.remove(keyUuid);
                break;
            }
        }
        tokenMap.put(tokenId, new UserToken(userId, LocalDateTime.now().plusDays(1)));
        return tokenId;
    }

    public UUID getUserId(UUID tokenId) throws TokenException {
        if(tokenMap.containsKey(tokenId)) {
            UserToken userToken = tokenMap.get(tokenId);
            if(userToken.getExpireDateTime().isBefore(LocalDateTime.now())) {
                tokenMap.remove(tokenId);
                throw new TokenException("Token Expired");
            }
            return userToken.getUuid();
        }
        else {
            throw new TokenException("Token Not Found");
        }
    }

    public boolean isTokenContain(UUID tokenId) {
        if(!tokenMap.containsKey(tokenId)) {
            return false;
        }
        UserToken userToken = tokenMap.get(tokenId);
        if(userToken.getExpireDateTime().isBefore(LocalDateTime.now())) {
            tokenMap.remove(tokenId);
            return false;
        }
        return true;
    }

    public void removeToken(UUID tokenId) {
        tokenMap.remove(tokenId);
    }

    public String getMap() {
        return tokenMap.toString();
    }
}
