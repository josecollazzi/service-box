package com.manywho.services.box.managers;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.manywho.sdk.entities.run.elements.config.ServiceRequest;
import com.manywho.sdk.entities.run.elements.config.ServiceResponse;
import com.manywho.sdk.entities.run.elements.type.*;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.enums.InvokeType;
import com.manywho.sdk.services.PropertyCollectionParser;
import com.manywho.services.box.entities.Configuration;
import com.manywho.services.box.entities.actions.FileCopy;
import com.manywho.services.box.entities.actions.FileMove;
import com.manywho.services.box.client.BoxClient;
import com.manywho.services.box.facades.BoxFacadeInterface;
import com.manywho.services.box.services.FileService;
import com.manywho.services.box.services.FileUploadService;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.inject.Inject;

public class FileManager {
    @Inject
    private FileService fileService;

    @Inject
    private FileUploadService fileUploadService;

    @Inject
    private PropertyCollectionParser propertyParser;

    @Inject
    private CacheManagerInterface cacheManager;

    @Inject
    private BoxClient boxClient;

    @Inject
    private BoxFacadeInterface boxFacade;

    public ObjectDataResponse uploadFile(AuthenticatedWho authenticatedWho, FileDataRequest fileDataRequest, FormDataMultiPart formDataMultiPart) throws Exception {
        BodyPart bodyPart = fileUploadService.getFilePart(formDataMultiPart);
        String userAppId = cacheManager.getContextToUserApp(fileDataRequest.getStateId());

        String token = authenticatedWho.getToken();

        if (!StringUtils.isEmpty(userAppId)) {
            BoxDeveloperEditionAPIConnection connection = boxFacade.createDeveloperApiUserConnection(userAppId);
            token = connection.getAccessToken();
        }

        if (bodyPart != null) {
            BoxFile.Info fileInformation = fileUploadService.uploadFileToBox(token, fileDataRequest, bodyPart);
            if (fileInformation != null) {
                return new ObjectDataResponse(fileService.buildManyWhoFileObject(fileInformation, fileInformation.getResource()));
            }
        }

        throw new Exception("A file was not provided to upload to Box");
    }

    public ObjectCollection loadFiles(AuthenticatedWho authenticatedWho, String stateId, String resourcePath) throws Exception {
        String userAppId = cacheManager.getContextToUserApp(stateId);
        BoxFolder folder;

        if (!StringUtils.isEmpty(userAppId)) {
            BoxDeveloperEditionAPIConnection connection = boxFacade.createDeveloperApiUserConnection(userAppId);
            folder = boxClient.getFolder(connection, resourcePath);
        } else {
            folder = boxClient.getFolder(authenticatedWho.getToken(), resourcePath);
        }

        ObjectCollection files = new ObjectCollection();

        for (BoxItem.Info itemInfo : folder) {
            if (itemInfo instanceof BoxFile.Info) {
                files.add(fileService.buildManyWhoFileObject((BoxFile.Info) itemInfo, (BoxFile) itemInfo.getResource()));
            }
        }
        return files;
    }


    public ObjectCollection loadManyWhoFile(AuthenticatedWho authenticatedWho, String fileId) {
        BoxFile file = boxClient.getFile(authenticatedWho.getToken(), fileId);

        ObjectCollection files = new ObjectCollection();
        files.add(fileService.buildManyWhoFileObject(file.getInfo(), file));

        return files;
    }

    public ServiceResponse copyFile(AuthenticatedWho user, ServiceRequest serviceRequest) throws Exception {
        FileCopy fileCopy = propertyParser.parse(serviceRequest.getInputs(), FileCopy.class);
        if (fileCopy == null) {
            throw new Exception("Unable to parse the incoming FileCopy request");
        }

        fileService.copyFile(user.getToken(), fileCopy.getFile().getId(), fileCopy.getFolder().getId(), fileCopy.getName());

        return new ServiceResponse(InvokeType.Forward, serviceRequest.getToken());
    }

    public ServiceResponse moveFile(AuthenticatedWho user, ServiceRequest serviceRequest) throws Exception {
        FileMove fileMove = propertyParser.parse(serviceRequest.getInputs(), FileMove.class);
        if (fileMove == null) {
            throw new Exception("Unable to parse the incoming FileMove request");
        }

        fileService.moveFile(user.getToken(), fileMove.getFile().getId(), fileMove.getFolder().getId(), fileMove.getName());

        return new ServiceResponse(InvokeType.Forward, serviceRequest.getToken());
    }
}
