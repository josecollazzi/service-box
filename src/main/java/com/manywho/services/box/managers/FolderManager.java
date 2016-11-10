package com.manywho.services.box.managers;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.manywho.sdk.entities.run.elements.type.Object;
import com.manywho.sdk.entities.run.elements.type.ObjectDataRequest;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.services.PropertyCollectionParser;
import com.manywho.services.box.entities.types.Folder;
import com.manywho.services.box.facades.BoxFacadeInterface;
import com.manywho.services.box.services.FolderService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class FolderManager {
    @Inject
    private FolderService folderService;

    @Inject
    private PropertyCollectionParser propertyParser;

    @Inject
    private CacheManagerInterface cacheManager;

    @Inject
    private BoxFacadeInterface boxFacade;

    public Object createFolder(AuthenticatedWho user, ObjectDataRequest objectDataRequest) throws Exception {
        Folder folder = propertyParser.parse(objectDataRequest.getObjectData().get(0).getProperties(), Folder.class);

        if (folder == null) {
            throw new Exception("Unable to parse Folder save request");
        }

        if (StringUtils.isEmpty(folder.getParentFolder().getId())) {
            throw new Exception("The Parent Folder ID can not be empty");
        }

        String userAppId = cacheManager.getContextToUserApp(objectDataRequest.getStateId());

        String token = user.getToken();

        if (!StringUtils.isEmpty(userAppId)) {
            BoxDeveloperEditionAPIConnection connection = boxFacade.createDeveloperApiUserConnection(userAppId);
            token = connection.getAccessToken();
        }

        return folderService.createFolder(token, folder.getParentFolder().getId(), folder.getName());
    }
}
