/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.googlecode.gflot.examples.client.source;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.googlecode.gflot.examples.client.GFlotExamples;

/**
 * The annotations used in {@link GFlotExamples}.
 */
public class SourceAnnotations {
  /**
   * Indicates that a class variable should be included as source data in the
   * example. All data must have a JavaDoc style comment.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.FIELD)
  public @interface GFlotExamplesData {
  }

  /**
   * Indicates that a method or inner class should be included as source code in
   * the example. All source must have a JavaDoc style comment.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.TYPE, ElementType.METHOD})
  public @interface GFlotExamplesSource {
  }

  /**
   * Indicates the raw files that be included as raw source in a GFlot
   * example.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.TYPE)
  public static @interface GFlotExamplesRaw {
    String[] value();
  }
}
