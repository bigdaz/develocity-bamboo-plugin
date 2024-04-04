package com.gradle.develocity.bamboo.config;

import org.jetbrains.annotations.Nullable;

public final class GradleConfiguration extends BuildToolConfiguration {

    @Nullable
    public final String develocityPluginVersion;
    @Nullable
    public final String ccudPluginVersion;
    @Nullable
    public final String pluginRepository;
    @Nullable
    public final String pluginRepositoryCredentialName;

    private GradleConfiguration(
            @Nullable String server,
            boolean allowUntrustedServer,
            @Nullable String sharedCredentialName,
            @Nullable String develocityPluginVersion,
            @Nullable String ccudPluginVersion,
            @Nullable String pluginRepository,
            @Nullable String pluginRepositoryCredentialName,
            boolean enforceUrl,
            String filter
    ) {
        super(server, allowUntrustedServer, sharedCredentialName, enforceUrl, filter);
        this.develocityPluginVersion = develocityPluginVersion;
        this.ccudPluginVersion = ccudPluginVersion;
        this.pluginRepository = pluginRepository;
        this.pluginRepositoryCredentialName = pluginRepositoryCredentialName;
    }

    public static GradleConfiguration of(PersistentConfiguration configuration) {
        return new GradleConfiguration(
            configuration.getServer(),
            configuration.isAllowUntrustedServer(),
            configuration.getSharedCredentialName(),
            configuration.getDevelocityPluginVersion(),
            configuration.getCcudPluginVersion(),
            configuration.getPluginRepository(),
            configuration.getPluginRepositoryCredentialName(),
            configuration.isEnforceUrl(),
            configuration.getVcsRepositoryFilter()
        );
    }

    @Override
    public boolean isDisabled() {
        return server == null || develocityPluginVersion == null;
    }
}
