/*
 * Copyright (c) Clarisma / GeoDesk contributors
 *
 * This source code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.geodesk.feature.query;

import com.geodesk.feature.*;
import com.geodesk.geom.Bounds;

import java.util.Iterator;

/// @hidden
/// A Features view that wraps another Features collection and converts
/// all features to their center point (centroid or point on surface).
public class PointConversionView implements Features
{
    private final Features delegate;
    private final boolean usePointOnSurface;
    
    public PointConversionView(Features delegate, boolean usePointOnSurface)
    {
        this.delegate = delegate;
        this.usePointOnSurface = usePointOnSurface;
    }
    
    @Override
    public Features select(String query)
    {
        return new PointConversionView(delegate.select(query), usePointOnSurface);
    }
    
    @Override
    public Features nodes()
    {
        return new PointConversionView(delegate.nodes(), usePointOnSurface);
    }
    
    @Override
    public Features nodes(String query)
    {
        return new PointConversionView(delegate.nodes(query), usePointOnSurface);
    }
    
    @Override
    public Features ways()
    {
        return new PointConversionView(delegate.ways(), usePointOnSurface);
    }
    
    @Override
    public Features ways(String query)
    {
        return new PointConversionView(delegate.ways(query), usePointOnSurface);
    }
    
    @Override
    public Features relations()
    {
        return new PointConversionView(delegate.relations(), usePointOnSurface);
    }
    
    @Override
    public Features relations(String query)
    {
        return new PointConversionView(delegate.relations(query), usePointOnSurface);
    }
    
    @Override
    public Features in(Bounds bbox)
    {
        return new PointConversionView(delegate.in(bbox), usePointOnSurface);
    }
    
    @Override
    public Features select(Filter filter)
    {
        return new PointConversionView(delegate.select(filter), usePointOnSurface);
    }
    
    @Override
    public Features nodesOf(Feature parent)
    {
        return new PointConversionView(delegate.nodesOf(parent), usePointOnSurface);
    }
    
    @Override
    public Features membersOf(Feature parent)
    {
        return new PointConversionView(delegate.membersOf(parent), usePointOnSurface);
    }
    
    @Override
    public Features parentsOf(Feature child)
    {
        return new PointConversionView(delegate.parentsOf(child), usePointOnSurface);
    }
    
    @Override
    public Features asPoints()
    {
        return this;  // Already converting to points
    }
    
    @Override
    public Features asPointsOnSurface()
    {
        return new PointConversionView(delegate, true);
    }
    
    @Override
    public Iterator<Feature> iterator()
    {
        return new PointConversionIterator(delegate.iterator(), usePointOnSurface);
    }
}
