/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.bean.validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.bootstrap.GenericBootstrap;

import org.apache.camel.BindToRegistry;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.condition.OS.AIX;

public class ValidatorFactoryTest extends CamelTestSupport {

    @BindToRegistry("myValidatorFactory")
    private ValidatorFactory validatorFactory;

    @Override
    @BeforeEach
    public void setUp() throws Exception {
        GenericBootstrap bootstrap = Validation.byDefaultProvider();
        bootstrap.providerResolver(new HibernateValidationProviderResolver());

        this.validatorFactory = bootstrap.configure().buildValidatorFactory();

        super.setUp();
    }

    @DisabledOnOs(AIX)
    @Test
    void configureValidatorFactoryFromRegistry() throws Exception {

        BeanValidatorEndpoint endpoint = context.getEndpoint("bean-validator:dummy?validatorFactory=#myValidatorFactory", BeanValidatorEndpoint.class);
        BeanValidatorProducer producer = (BeanValidatorProducer)endpoint.createProducer();

        assertSame(this.validatorFactory, endpoint.getValidatorFactory());
        assertSame(this.validatorFactory, producer.getValidatorFactory());
    }

    @DisabledOnOs(AIX)
    @Test
    void configureValidatorFactory() throws Exception {

        BeanValidatorEndpoint endpoint = context.getEndpoint("bean-validator:dummy", BeanValidatorEndpoint.class);
        BeanValidatorProducer producer = (BeanValidatorProducer)endpoint.createProducer();

        assertNull(endpoint.getValidatorFactory());
        assertNotSame(this.validatorFactory, endpoint.getValidatorFactory());
        assertNotNull(producer.getValidatorFactory());
        assertNotSame(this.validatorFactory, producer.getValidatorFactory());
    }
}