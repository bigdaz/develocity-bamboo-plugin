package com.gradle.develocity.bamboo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class JsonConfigurationConverterTest {

    private static final PersistentConfiguration config = new PersistentConfiguration()
        .setServer("https://mycompany.com")
        .setDevelocityPluginVersion("3.11")
        .setPluginRepository("https://plugins.mycompany.com")
        .setSharedCredentialName("develocity-creds")
        .setAllowUntrustedServer(true)
        .setCcudPluginVersion("1.11")
        .setInjectCcudExtension(true)
        .setInjectMavenExtension(true);

    private static final String json = "{\"server\":\"https://mycompany.com\",\"allowUntrustedServer\":true," +
        "\"sharedCredentialName\":\"develocity-creds\",\"develocityPluginVersion\":\"3.11\",\"ccudPluginVersion\":\"1.11\"," +
        "\"pluginRepository\":\"https://plugins.mycompany.com\",\"injectMavenExtension\":true," +
        "\"injectCcudExtension\":true}";

    @Test
    void toJson() throws JsonProcessingException {
        assertThat(JsonConfigurationConverter.toJson(config), is(json));
    }

    @Test
    void toNullJson() throws JsonProcessingException {
        assertThat(JsonConfigurationConverter.toJson(null), nullValue());
    }

    @Test
    void fromJson() throws IOException {
        assertThat(JsonConfigurationConverter.fromJson(json), equalTo(config));
    }

    @Test
    void fromNullJson() throws IOException {
        assertThat(JsonConfigurationConverter.fromJson(null), nullValue());
    }

    @Test
    void unknownAttributesAreIgnored() throws IOException {
        String jsonConfig = "{\"server\":\"https://mycompany.com\",\"foo\":\"bar\"}";

        assertThat(JsonConfigurationConverter.fromJson(jsonConfig),
            equalTo(new PersistentConfiguration().setServer("https://mycompany.com")));
    }
}
