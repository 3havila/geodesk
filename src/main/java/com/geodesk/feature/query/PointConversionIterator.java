/*
 * Copyright (c) Clarisma / GeoDesk contributors
 *
 * This source code is licensed under the Apache 2.0 license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.geodesk.feature.query;

import com.geodesk.feature.Feature;
import java.util.Iterator;

/// @hidden
/// An iterator that wraps another iterator and converts each feature
/// to its center point.
public class PointConversionIterator implements Iterator<Feature>
{
    private final Iterator<Feature> delegate;
    private final boolean usePointOnSurface;
    
    public PointConversionIterator(Iterator<Feature> delegate, boolean usePointOnSurface)
    {
        this.delegate = delegate;
        this.usePointOnSurface = usePointOnSurface;
    }
    
    @Override
    public boolean hasNext()
    {
        return delegate.hasNext();
    }
    
    @Override
    public Feature next()
    {
        Feature feature = delegate.next();
        return usePointOnSurface ? 
            new PointFeatureOnSurface(feature) : 
            new PointFeature(feature);
    }
}
