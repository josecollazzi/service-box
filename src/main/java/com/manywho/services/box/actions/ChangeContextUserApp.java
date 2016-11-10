package com.manywho.services.box.actions;

import com.manywho.sdk.entities.describe.DescribeValue;
import com.manywho.sdk.entities.describe.DescribeValueCollection;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.actions.AbstractAction;

public class ChangeContextUserApp extends AbstractAction {
    @Override
    public String getUriPart() {
        return "change-context/user-app";
    }

    @Override
    public String getDeveloperName() {
        return "Context User App";
    }

    @Override
    public String getDeveloperSummary() {
        return "Context User App";
    }

    @Override
    public DescribeValueCollection getServiceInputs() {
        DescribeValueCollection describeValues = new DescribeValueCollection();
        describeValues.add(new DescribeValue("User App ID", ContentType.String, false));
        describeValues.add(new DescribeValue("Active", ContentType.Boolean, true));

        return describeValues;
    }

    @Override
    public DescribeValueCollection getServiceOutputs() {
        return new DescribeValueCollection();
    }
}
