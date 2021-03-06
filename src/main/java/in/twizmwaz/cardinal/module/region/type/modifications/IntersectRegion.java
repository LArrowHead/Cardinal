/*
 * Copyright (c) 2016, Kevin Phoenix
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package in.twizmwaz.cardinal.module.region.type.modifications;

import com.google.common.collect.ImmutableSet;
import in.twizmwaz.cardinal.match.Match;
import in.twizmwaz.cardinal.module.region.AbstractRegion;
import in.twizmwaz.cardinal.module.region.Region;
import in.twizmwaz.cardinal.module.region.RegionBounds;
import in.twizmwaz.cardinal.module.region.parser.modifications.IntersectRegionParser;
import in.twizmwaz.cardinal.util.Geometry;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.stream.Collectors;

public class IntersectRegion extends AbstractRegion {

  private final Collection<Region> regions;

  public IntersectRegion(Match match, Collection<Region> regions) {
    super(new RegionBounds(match, Geometry.getCuboidEnclosing(regions)));
    this.regions = regions;
  }

  public IntersectRegion(Match match, IntersectRegionParser parser) {
    this(match, parser.getRegions());
  }

  @Override
  public boolean isRandomizable() {
    for (Region region : regions) {
      if (!region.isRandomizable()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isBounded() {
    for (Region region : regions) {
      if (!region.isBounded()) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Collection<Block> getBlocks() {
    if (!isBounded()) {
      throw new UnsupportedOperationException("Cannot get blocks in unbounded region");
    }
    if (super.getBlocks() != null) {
      return super.getBlocks();
    }
    Collection<Block> blocks = getBounds().getBlocks().stream().filter(
        block -> evaluate(block.getLocation().toVector().plus(0.5, 0.5, 0.5))).collect(Collectors.toSet());
    setBlocks(ImmutableSet.copyOf(blocks));
    return super.getBlocks();
  }

  @Override
  public Vector getRandomPoint() {
    throw new UnsupportedOperationException("Cannot get random point in non-randomizable region");
  }

  @Override
  public boolean evaluate(Vector evaluating) {
    for (Region region : regions) {
      if (!region.evaluate(evaluating)) {
        return false;
      }
    }
    return true;
  }

}
