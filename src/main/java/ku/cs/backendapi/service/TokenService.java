package ku.cs.backendapi.service;

import ku.cs.backendapi.exeption.TokenNotfoundException;
import ku.cs.backendapi.model.TokenList;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {
    private final TokenList tokenList = new TokenList();

    public UUID createToken(UUID userId) {
        UUID tokenId = UUID.randomUUID();
        tokenList.addToken(tokenId, userId);
        return tokenId;
    }

    public UUID getUserId(UUID tokenId) throws TokenNotfoundException {
        return tokenList.getUserId(tokenId);
    }

    public void validateToken(UUID tokenId) throws TokenNotfoundException {
        tokenList.isTokenContain(tokenId);
    }

    public void removeToken(UUID tokenId) {
        tokenList.removeToken(tokenId);
    }

    public String getTokenMap() {
        return tokenList.getMap();
    }
}
