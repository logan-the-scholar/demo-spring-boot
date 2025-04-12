package com.study.demo.common.github;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;

//        @JsonProperty("token_type")
//        private String tokenType;
//
//        private String scope;
//
//        private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

//        public String getTokenType() {
//            return tokenType;
//        }

//        public void setTokenType(String tokenType) {
//            this.tokenType = tokenType;
//        }

//        public String getScope() {
//            return scope;
//        }

//        public void setScope(String scope) {
//            this.scope = scope;
//        }

//        public String getError() {
//            return error;
//        }

//        public void setError(String error) {
//            this.error = error;
//        }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
