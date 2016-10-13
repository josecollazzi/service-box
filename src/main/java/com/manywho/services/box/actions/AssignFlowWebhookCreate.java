package com.manywho.services.box.actions;

import com.manywho.sdk.entities.describe.DescribeValue;
import com.manywho.sdk.entities.describe.DescribeValueCollection;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.actions.AbstractAction;

public class AssignFlowWebhookCreate extends AbstractAction {
    @Override
    public String getUriPart() {
        return "trigger-assign/flow";
    }

    @Override
    public String getDeveloperName() {
        return "Assign Webhook to Flow";
    }

    @Override
    public String getDeveloperSummary() {
        return "Assign a Webhook to a Flow";
    }

    @Override
    public DescribeValueCollection getServiceInputs() {
        return new DescribeValueCollection() {{
            add(new DescribeValue("Flow ID", ContentType.String, false));
            add(new DescribeValue("Flow Version ID", ContentType.String, false));
            add(new DescribeValue("Tenant ID", ContentType.String, false));
            add(new DescribeValue("Box Target Item Type", ContentType.String, false));
            add(new DescribeValue("Box Target Item ID", ContentType.String, false));
            add(new DescribeValue("Trigger", ContentType.String, false));
        }};
    }

    @Override
    public DescribeValueCollection getServiceOutputs() {
        return new DescribeValueCollection() {{
        }};
    }
}
