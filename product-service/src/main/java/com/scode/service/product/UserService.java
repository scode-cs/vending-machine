package com.scode.service.product;

import com.scode.domain.ProductDomain;
import com.scode.domain.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements ProductDomain {

    @Value("${config.user.name}")
    private String configUser;
    private List<UserModel> users;

    @Override
    public List<UserModel> getAll() {
        if (CollectionUtils.isEmpty(users)) loadProducts();
        return users;
    }

    private void loadProducts() {
        users = Arrays.asList(new UserModel(1, "Product 1"),
                new UserModel(2, "Product 2"), new UserModel(3, configUser));
    }

}
