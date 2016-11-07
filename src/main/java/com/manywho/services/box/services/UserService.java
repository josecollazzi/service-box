package com.manywho.services.box.services;

import com.box.sdk.BoxUser;
import com.manywho.services.box.client.BoxClient;

import javax.inject.Inject;

public class UserService {
    private BoxClient boxClient;

    @Inject
    public UserService(BoxClient boxClient) {
        this.boxClient = boxClient;
    }

    public BoxUser.Info createUserApp(String enterpriseID, String name) throws Exception {
        BoxUser.Info user = boxClient.createAppUser(enterpriseID, name);
        if (user == null) {
            throw new Exception("Unable to create an user with the name " + name);
        }

        return user;
    }
}
