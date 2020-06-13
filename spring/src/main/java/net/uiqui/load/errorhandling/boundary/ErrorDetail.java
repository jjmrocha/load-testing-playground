package net.uiqui.load.errorhandling.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDetail {
    @JsonProperty("type")
    private final String type;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("detail")
    private final String detail;
    @JsonProperty("status")
    private final int status;
    @JsonProperty("instance")
    private final String instance;

    private ErrorDetail(String type, String title, String detail, int status, String instance) {
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.status = status;
        this.instance = instance;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getStatus() {
        return status;
    }

    public String getInstance() {
        return instance;
    }

    public static final class Builder {
        private String type;
        private String title;
        private String detail;
        private int status;
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

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withInstance(String instance) {
            this.instance = instance;
            return this;
        }

        public ErrorDetail build() {
            return new ErrorDetail(type, title, detail, status, instance);
        }
    }
}
