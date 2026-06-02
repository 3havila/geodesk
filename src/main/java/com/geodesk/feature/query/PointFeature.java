/*
 * Copyright (c) Clarisma / GeoDesk contributors
 *
 * This source code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.geodesk.feature.query;

import com.geodesk.feature.*;
import com.geodesk.geom.Box;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import java.util.Iterator;

/// @hidden
/// A wrapper for a Feature that always returns its centroid as a Point.
public class PointFeature implements Feature
{
    protected final Feature delegate;
    protected final Point point;
    
    public PointFeature(Feature feature)
    {
        this.delegate = feature;
        
        // Get the original geometry
        Geometry geom = feature.toGeometry();
        
        // If already a point, use as-is
        if(geom instanceof Point) {
            this.point = (Point) geom;
        } else {
            // Convert to center point (centroid)
            this.point = geom.getCentroid();
        }
    }
    
    @Override
    public long id()
    {
        return delegate.id();
    }
    
    @Override
    public FeatureType type()
    {
        return delegate.type();
    }
    
    @Override
    public boolean isNode()
    {
        return true;  // Treat all converted features as nodes (they're points)
    }
    
    @Override
    public boolean isWay()
    {
        return false;
    }
    
    @Override
    public boolean isRelation()
    {
        return false;
    }
    
    @Override
    public int x()
    {
        return (int) point.getX();
    }
    
    @Override
    public int y()
    {
        return (int) point.getY();
    }
    
    @Override
    public Box bounds()
    {
        return Box.ofXY(x(), y());
    }
    
    @Override
    public int[] toXY()
    {
        return new int[] { x(), y() };
    }
    
    @Override
    public Tags tags()
    {
        return delegate.tags();
    }
    
    @Override
    public String tag(String key)
    {
        return delegate.tag(key);
    }
    
    @Override
    public boolean hasTag(String key)
    {
        return delegate.hasTag(key);
    }
    
    @Override
    public boolean hasTag(String key, String value)
    {
        return delegate.hasTag(key, value);
    }
    
    @Override
    public boolean belongsTo(Feature parent)
    {
        return delegate.belongsTo(parent);
    }
    
    @Override
    public String role()
    {
        return delegate.role();
    }
    
    @Override
    public String stringValue(String key)
    {
        return delegate.stringValue(key);
    }
    
    @Override
    public int intValue(String key)
    {
        return delegate.intValue(key);
    }
    
    @Override
    public long longValue(String key)
    {
        return delegate.longValue(key);
    }
    
    @Override
    public double doubleValue(String key)
    {
        return delegate.doubleValue(key);
    }
    
    @Override
    public boolean booleanValue(String key)
    {
        return delegate.booleanValue(key);
    }
    
    @Override
    public boolean belongsToRelation()
    {
        return delegate.belongsToRelation();
    }
    
    @Override
    public boolean isArea()
    {
        return false;  // Converted to point, so not an area
    }
    
    @Override
    public double length()
    {
        return 0;  // Points have no length
    }
    
    @Override
    public double area()
    {
        return 0;  // Points have no area
    }
    
    @Override
    public Geometry toGeometry()
    {
        return point;  // Always return the point!
    }
    
    @Override
    public Features nodes()
    {
        return EmptyView.ANY;  // Points have no nodes
    }
    
    @Override
    public Features nodes(String query)
    {
        return EmptyView.ANY;  // Points have no nodes
    }
    
    @Override
    public Features members()
    {
        return EmptyView.ANY;  // Points have no members
    }
    
    @Override
    public Features members(String query)
    {
        return EmptyView.ANY;  // Points have no members
    }
    
    @Override
    public Features parents()
    {
        return delegate.parents();
    }
    
    @Override
    public Features parents(String query)
    {
        return delegate.parents(query);
    }
    
    @Override
    public Iterator<Feature> iterator()
    {
        return delegate.iterator();
    }
}
