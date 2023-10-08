package ku.cs.backendapi.model;

import ku.cs.backendapi.exeption.TokenNotfoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenList {
    private final Map<UUID, UUID> tokenMap;

    public TokenList() {
        tokenMap = new HashMap<>();
    }

    public void addToken(UUID tokenId, UUID userId) {
        for(UUID keyUuid : tokenMap.keySet()) {
            if(tokenMap.get(keyUuid).equals(userId)) {
                tokenMap.remove(keyUuid);
                break;
            }
        }
        tokenMap.put(tokenId, userId);
    }

    public UUID getUserId(UUID tokenId) throws TokenNotfoundException {
        if(tokenMap.containsKey(tokenId)) {
            return tokenMap.get(tokenId);
        }
        else {
            throw new TokenNotfoundException();
        }
    }

    public boolean isTokenContain(UUID tokenId){
        if(!tokenMap.containsKey(tokenId)) {
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
