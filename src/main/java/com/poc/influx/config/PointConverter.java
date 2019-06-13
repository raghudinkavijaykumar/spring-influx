/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.poc.influx.config;

import org.influxdb.dto.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PointConverter implements PointCollectionConverter<Point>
{
  @Override
  public List<Point> convert(final Point source)
  {
    final ArrayList<Point> list = new ArrayList<>(1);
    Collections.addAll(list, source);
    return list;
  }
}
