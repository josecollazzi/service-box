package com.manywho.services.box.controllers;

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
import com.manywho.services.box.managers.TaskManager;

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

    @Path("/access-token")
    @POST
    @AuthorizationRequired
    public ServiceResponse getAccessToken(ServiceRequest serviceRequest) throws Exception {
        Credentials credentials = boxClient.getLastCredentials(getAuthenticatedWho().getToken());

        ServiceResponse serviceResponse = new ServiceResponse();
        EngineValueCollection engineValues = new EngineValueCollection();
        engineValues.add(new EngineValue("Access Token", ContentType.String, credentials.getAccessToken()));
        serviceResponse.setOutputs(engineValues);
        serviceResponse.setToken(serviceRequest.getToken());
        serviceResponse.setInvokeType(InvokeType.Forward);

        return serviceResponse;
    }
}
