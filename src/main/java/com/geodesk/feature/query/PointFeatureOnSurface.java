/*
 * Copyright (c) Clarisma / GeoDesk contributors
 *
 * This source code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.geodesk.feature.query;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import com.geodesk.feature.Feature;

/// @hidden
/// A wrapper for a Feature that returns its point on surface (interior point)
/// instead of centroid. This is more accurate for non-convex shapes.
public class PointFeatureOnSurface extends PointFeature
{
    public PointFeatureOnSurface(Feature feature)
    {
        super(feature);
        // Override the point to use getPointOnSurface instead
        recalculatePoint();
    }
    
    private void recalculatePoint()
    {
        Geometry geom = delegate.toGeometry();
        
        // If already a point, use as-is
        if(geom instanceof Point) {
            // Use reflection to set the point field since it's final in parent
            try {
                java.lang.reflect.Field field = PointFeature.class.getDeclaredField("point");
                field.setAccessible(true);
                field.set(this, geom);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Convert to point on surface (better than centroid for non-convex shapes)
            try {
                java.lang.reflect.Field field = PointFeature.class.getDeclaredField("point");
                field.setAccessible(true);
                field.set(this, geom.getPointOnSurface());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
