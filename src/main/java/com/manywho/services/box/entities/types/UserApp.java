package com.manywho.services.box.entities.types;

import com.manywho.sdk.services.annotations.Id;
import com.manywho.sdk.services.annotations.Property;
import com.manywho.sdk.services.annotations.Type;

import java.util.Date;

@Type(com.manywho.services.box.types.Folder.NAME)
public class UserApp {

    @Id
    @Property("ID")
    private String id;

    @Property("Name")
    private String name;

    @Property("Login")
    private String login;

    @Property("Created At")
    private Date createdAd;

    @Property("Modified At")
    private Date modifiedAt;

    @Property("Language")
    private String language;

    @Property("Timezone")
    private String tiemzone;

    @Property("Space Amount")
    private Integer spaceAmount;

    @Property("Space Used")
    private Integer spaceUsed;

    @Property("Max Upload Size")
    private Integer maxUploadSize;

    @Property("Status")
    private String status;

    @Property("Job Title")
    private String jobTitle;

    @Property("Phone")
    private String phone;

    @Property("Address")
    private String address;

    @Property("Avatar Url")
    private String avatarUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public Date getCreatedAd() {
        return createdAd;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public String getLanguage() {
        return language;
    }

    public String getTiemzone() {
        return tiemzone;
    }

    public Integer getSpaceAmount() {
        return spaceAmount;
    }

    public Integer getSpaceUsed() {
        return spaceUsed;
    }

    public Integer getMaxUploadSize() {
        return maxUploadSize;
    }

    public String getStatus() {
        return status;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
