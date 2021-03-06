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

package in.twizmwaz.cardinal.module.objective.destroyable;

import in.twizmwaz.cardinal.match.Match;
import in.twizmwaz.cardinal.module.objective.Objective;
import in.twizmwaz.cardinal.module.objective.ProximityMetric;
import in.twizmwaz.cardinal.module.region.Region;
import in.twizmwaz.cardinal.module.team.Team;
import in.twizmwaz.cardinal.util.MaterialPattern;
import lombok.Getter;
import org.bukkit.event.Listener;

@Getter
public class Destroyable extends Objective implements Listener {

  private final String name;
  private final Region region;
  private final MaterialPattern materials;
  private final Team owner;
  private final double completion;
  private final boolean modeChanges;
  private final boolean showProgress;
  private final boolean repairable;
  private final boolean sparks;
  private final ProximityMetric proximityMetric;
  private final boolean proximityHorizontal;

  /**
   * @param match               The match the destroyable belongs to.
   * @param id                  This destroyable's ID.
   * @param name                This destroyable's name.
   * @param required            Determines if this objective is required to win the match.
   * @param region              The region that contains this destroyable.
   * @param materials           The materials that make up this destroyable.
   * @param owner               The owner of this destroyable.
   * @param completion          The percentage that this monument needs to be broken to be consider complete.
   * @param modeChanges         Determines if this destroyable follows mode changes.
   * @param showProgress        Determines if the progress of this destroyable is shown on the scoreboard.
   * @param repairable          Determines if this destroyable can be repaired.
   * @param sparks              Determines if sparks show when part of the destroyable is broken.
   * @param show                Determines if this destroyable is shown on the scoreboard.
   * @param proximityMetric     The proximity metric that determines how proximity is calculated.
   * @param proximityHorizontal Determines if only horizontal distance is considered when
   *                            calculating proximity.
   */
  public Destroyable(Match match, String id, String name, boolean required, Region region,
                     MaterialPattern materials, Team owner,
                     double completion, boolean modeChanges, boolean showProgress,
                     boolean repairable, boolean sparks, boolean show,
                     ProximityMetric proximityMetric, boolean proximityHorizontal) {
    super(match, id, required, show);
    this.name = name;
    this.region = region;
    this.materials = materials;
    this.owner = owner;
    this.completion = completion;
    this.modeChanges = modeChanges;
    this.showProgress = showProgress;
    this.repairable = repairable;
    this.sparks = sparks;
    this.proximityMetric = proximityMetric;
    this.proximityHorizontal = proximityHorizontal;
  }

}
