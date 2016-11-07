package com.manywho.services.box.types;

import com.manywho.sdk.entities.draw.elements.type.*;
import com.manywho.sdk.enums.ContentType;
import com.manywho.sdk.services.describe.types.AbstractType;

public class UserApp extends AbstractType {
    public final static String NAME = "User App";

    @Override
    public String getDeveloperName() {
        return NAME;
    }

    @Override
    public TypeElementBindingCollection getBindings() {
        TypeElementPropertyBindingCollection typeElementPropertyBindings = new TypeElementPropertyBindingCollection();
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("ID", "ID"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Name", "Name"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Login", "Login"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Created At", "Created At"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Modified At", "Modified At"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Language", "Language"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Timezone", "Timezone"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Space Amount", "Space Amount"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Space Used", "Space Used"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Max Upload Size", "Max Upload Size"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Status", "Status"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Job Title", "Job Title"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Phone", "Phone"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Address", "Address"));
        typeElementPropertyBindings.add(new TypeElementPropertyBinding("Avatar Url", "Avatar Url"));



        TypeElementBindingCollection typeElementBindings = new TypeElementBindingCollection();
        typeElementBindings.add(new TypeElementBinding(NAME, "Details about User App", NAME, typeElementPropertyBindings));

        return typeElementBindings;
    }

    @Override
    public TypeElementPropertyCollection getProperties() {
        TypeElementPropertyCollection typeElementProperties = new TypeElementPropertyCollection();
        typeElementProperties.add(new TypeElementProperty("ID", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Name", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Login", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Created At", ContentType.DateTime));
        typeElementProperties.add(new TypeElementProperty("Modified At", ContentType.DateTime));
        typeElementProperties.add(new TypeElementProperty("Language", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Timezone", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Space Amount", ContentType.Number));
        typeElementProperties.add(new TypeElementProperty("Space Used", ContentType.Number));
        typeElementProperties.add(new TypeElementProperty("Max Upload Size", ContentType.Number));
        typeElementProperties.add(new TypeElementProperty("Status", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Job Title", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Phone", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Address", ContentType.String));
        typeElementProperties.add(new TypeElementProperty("Avatar Url", ContentType.String));

        return typeElementProperties;
    }
}
