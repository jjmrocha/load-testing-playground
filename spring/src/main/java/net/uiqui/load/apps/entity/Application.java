package net.uiqui.load.apps.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Application {
    @JsonProperty("app-id")
    private Integer appId;
    @JsonProperty("name")
    private String name;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {
        private Integer appId;
        private String name;

        private Builder() {
        }

        public static Builder anApplication() {
            return new Builder();
        }

        public Builder withAppId(Integer appId) {
            this.appId = appId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Application build() {
            Application application = new Application();
            application.setAppId(appId);
            application.setName(name);
            return application;
        }
    }
}
