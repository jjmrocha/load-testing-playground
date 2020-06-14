package net.uiqui.load.errorhandling.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetail {
    @JsonProperty("type")
    private final String type;
    @JsonProperty("detail")
    private final String detail;
    @JsonProperty("instance")
    private final String instance;

    private ErrorDetail(String type, String detail, String instance) {
        this.type = type;
        this.detail = detail;
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }

    public static final class Builder {
        private String type;
        private String detail;
        private String instance;

        private Builder() {
        }

        public static Builder anErrorDetail() {
            return new Builder();
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder withInstance(String instance) {
            this.instance = instance;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(type, detail, instance);
        }
    }
}
