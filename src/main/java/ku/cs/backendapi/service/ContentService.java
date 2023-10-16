package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.Category;
import ku.cs.backendapi.entity.User;
import ku.cs.backendapi.exception.TokenNotfoundException;
import ku.cs.backendapi.exception.UserNotFoundException;
import ku.cs.backendapi.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContentService {

    private String host = "https://content.doksakura.com";

    @Autowired
    TokenService tokenService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public String getImageLinkCustomer(UUID tokenId) throws UserNotFoundException, TokenNotfoundException {
        User user = tokenService.getUser(tokenId);
        if(user.getImageLink() == null) return host + "/shibaqueue/userImage/profile.png";
        return user.getImageLink();
    }

    public List<String> getAllCategory() {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            categoryNames.add(category.getCategoryName());
        }
        return categoryNames;
    }
}