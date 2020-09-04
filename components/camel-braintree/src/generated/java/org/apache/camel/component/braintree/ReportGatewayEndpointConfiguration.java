
/*
 * Camel EndpointConfiguration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.braintree;

import org.apache.camel.spi.Configurer;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;

/**
 * Camel EndpointConfiguration for com.braintreegateway.ReportGateway
 */
@UriParams(apiName = "report")
@Configurer
public final class ReportGatewayEndpointConfiguration extends BraintreeConfiguration {
    @UriParam(description = "The request")
    private com.braintreegateway.TransactionLevelFeeReportRequest request;

    public com.braintreegateway.TransactionLevelFeeReportRequest getRequest() {
        return request;
    }

    public void setRequest(com.braintreegateway.TransactionLevelFeeReportRequest request) {
        this.request = request;
    }
}