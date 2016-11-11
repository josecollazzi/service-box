package com.manywho.services.box.controllers;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.manywho.sdk.entities.run.EngineValue;
import com.manywho.sdk.entities.run.EngineValueCollection;
import com.manywho.sdk.entities.run.elements.config.ServiceRequest;
import com.manywho.sdk.entities.run.elements.config.ServiceResponse;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.enums.InvokeType;
import com.manywho.sdk.services.annotations.AuthorizationRequired;
import com.manywho.sdk.services.controllers.AbstractController;
import com.manywho.services.box.client.BoxClient;
import com.manywho.services.box.entities.Credentials;
import com.manywho.services.box.facades.BoxFacadeInterface;
import com.manywho.services.box.managers.CacheManagerInterface;
import com.manywho.services.box.managers.TaskManager;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccessTokenController extends AbstractController {

    @Inject
    private BoxClient boxClient;

    @Inject
    private CacheManagerInterface cacheManager;

    @Inject
    private BoxFacadeInterface boxFacade;

    @Path("/access-token")
    @POST
    @AuthorizationRequired
    public ServiceResponse getAccessToken(ServiceRequest serviceRequest) throws Exception {
        Credentials credentials = boxClient.getLastCredentials(getAuthenticatedWho().getToken());

        String userAppId = cacheManager.getContextToUserApp(serviceRequest.getStateId());

        String token = credentials.getAccessToken();

        if (!StringUtils.isEmpty(userAppId)) {
            BoxDeveloperEditionAPIConnection connection = boxFacade.createDeveloperApiUserConnection(userAppId);
            token = connection.getAccessToken();
        }

        ServiceResponse serviceResponse = new ServiceResponse();
        EngineValueCollection engineValues = new EngineValueCollection();
        engineValues.add(new EngineValue("Access Token", ContentType.String, token));
        serviceResponse.setOutputs(engineValues);
        serviceResponse.setToken(serviceRequest.getToken());
        serviceResponse.setInvokeType(InvokeType.Forward);

        return serviceResponse;
    }
}
