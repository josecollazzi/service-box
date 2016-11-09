package com.manywho.services.box.managers;

import com.box.sdk.BoxUser;
import com.manywho.sdk.entities.run.elements.type.MObject;
import com.manywho.sdk.entities.run.elements.type.ObjectDataRequest;
import com.manywho.sdk.services.PropertyCollectionParser;
import com.manywho.services.box.entities.Configuration;
import com.manywho.services.box.entities.types.UserApp;
import com.manywho.services.box.services.ObjectMapperService;
import com.manywho.services.box.services.UserService;

import javax.inject.Inject;

public class UserManager {
    private UserService userService;
    private PropertyCollectionParser propertyParser;
    private ObjectMapperService objectMapperService;

    @Inject
    public UserManager(PropertyCollectionParser propertyParser, ObjectMapperService objectMapperService, UserService userService ) {
        this.propertyParser = propertyParser;
        this.objectMapperService = objectMapperService;
        this.userService = userService;
    }

    public MObject createAppUser(ObjectDataRequest objectDataRequest) throws Exception {
        UserApp userAppCreate = propertyParser.parse(objectDataRequest.getObjectData().get(0).getProperties(), UserApp.class);
        Configuration configuration  = propertyParser.parse(objectDataRequest.getConfigurationValues(), Configuration.class);

        if (userAppCreate != null) {
            BoxUser.Info userAppInfo = userService.createUserApp(
                    configuration.getEnterpriseId(),
                    userAppCreate.getName(),
                    userAppCreate.getSpaceAmount()
            );

            return objectMapperService.convertBoxUserApp(userAppInfo);
        }

        throw new Exception("An invalid user app creation request was given");
    }

}
