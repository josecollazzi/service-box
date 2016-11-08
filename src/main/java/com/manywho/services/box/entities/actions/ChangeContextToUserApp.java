package com.manywho.services.box.entities.actions;

import com.manywho.sdk.services.annotations.Property;
import javax.validation.constraints.NotNull;

public class ChangeContextToUserApp {
    @Property(value = "User App ID", isObject = false)
    @NotNull(message = "A User App ID is required when change to User App Context")
    private String userAppId;

    public String getUserAppId() {
        return userAppId;
    }
}
