package com.manywho.services.box.actions;

import com.manywho.sdk.entities.describe.DescribeValue;
import com.manywho.sdk.entities.describe.DescribeValueCollection;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.actions.AbstractAction;
import com.manywho.services.box.types.File;
import com.manywho.services.box.types.Folder;
import com.manywho.services.box.types.TaskAssignment;

public class AccessToken extends AbstractAction {
    @Override
    public String getUriPart() {
        return "auth/access-token";
    }

    @Override
    public String getDeveloperName() {
        return "Access Token";
    }

    @Override
    public String getDeveloperSummary() {
        return "Retrieve the Access Token";
    }

    @Override
    public DescribeValueCollection getServiceInputs() {
        return new DescribeValueCollection();
    }

    @Override
    public DescribeValueCollection getServiceOutputs() {
        DescribeValueCollection describeValues = new DescribeValueCollection();
        describeValues.add(new DescribeValue("Access Token", ContentType.String, false, null));

        return describeValues;
    }
}
