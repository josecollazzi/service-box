package com.manywho.services.box.controllers;

import com.manywho.sdk.entities.run.EngineValue;
import com.manywho.sdk.entities.run.elements.config.ServiceRequest;
import com.manywho.sdk.entities.run.elements.config.ServiceResponse;
import com.manywho.sdk.entities.run.elements.type.MObject;
import com.manywho.sdk.entities.run.elements.type.ObjectCollection;
import com.manywho.sdk.entities.run.elements.type.Property;
import com.manywho.sdk.entities.run.elements.type.PropertyCollection;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.enums.InvokeType;
import com.manywho.sdk.services.PropertyCollectionParser;
import com.manywho.sdk.services.annotations.AuthorizationRequired;
import com.manywho.sdk.services.controllers.AbstractController;
import com.manywho.services.box.entities.actions.ChangeContextToUserApp;
import com.manywho.services.box.managers.CacheManagerInterface;
import com.manywho.services.box.types.UserApp;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/change-context")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChangeContext extends AbstractController{
    @Inject
    private CacheManagerInterface cacheManager;

    @Inject
    private PropertyCollectionParser propertyParser;

    @Path("/user-app")
    @POST
    @AuthorizationRequired
    public ServiceResponse changeToUserApp(ServiceRequest serviceRequest) throws Exception {
        ChangeContextToUserApp changeContextToUserApp = propertyParser.parse(serviceRequest.getInputs(), ChangeContextToUserApp.class);
        if(changeContextToUserApp.getActive()) {
            cacheManager.saveContextToUserApp(serviceRequest.getStateId(), changeContextToUserApp.getUserAppId());
        } else {
            cacheManager.deleteContextToUserApp(serviceRequest.getStateId(), changeContextToUserApp.getUserAppId());
        }
        return new ServiceResponse(InvokeType.Forward, serviceRequest.getToken());
    }
}
