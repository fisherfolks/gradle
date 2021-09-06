/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.tasks.testing;

import org.gradle.api.Action;
import org.gradle.api.Incubating;
import org.gradle.api.internal.tasks.testing.WorkerTestClassProcessorFactory;
import org.gradle.api.internal.tasks.testing.detection.TestFrameworkDetector;
import org.gradle.api.tasks.Internal;
import org.gradle.api.tasks.Nested;
import org.gradle.internal.scan.UsedByScanPlugin;
import org.gradle.process.internal.worker.WorkerProcessBuilder;

import java.util.List;

/**
 * Defines a framework for running automated tests (JUnit 4/5, TestNG).
 *
 * @since 7.3
 */
@Incubating
@UsedByScanPlugin("test-retry")
public interface TestFramework {

    /**
     * Returns a detector which is used to determine which of the candidate class files correspond to test classes to be
     * executed.
     */
    @Internal
    TestFrameworkDetector getDetector();

    @Nested
    TestFrameworkOptions getOptions();

    /**
     * Returns a factory which is used to create a {@link org.gradle.api.internal.tasks.testing.TestClassProcessor} in
     * each worker process. This factory is serialized across to the worker process, and then its {@link
     * org.gradle.api.internal.tasks.testing.WorkerTestClassProcessorFactory#create(org.gradle.internal.service.ServiceRegistry)}
     * method is called to create the test processor.
     */
    @Internal
    WorkerTestClassProcessorFactory getProcessorFactory();

    /**
     * Returns an action which is used to perform some framework specific worker process configuration. This action is
     * executed before starting each worker process.
     */
    @Internal
    Action<WorkerProcessBuilder> getWorkerConfigurationAction();

    /**
     * Returns a list of modules the test worker requires on the --module-path if it runs as a module.
     */
    @Internal
    List<String> getTestWorkerImplementationModules();
}
