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

package in.twizmwaz.cardinal.module.team;

import in.twizmwaz.cardinal.Cardinal;
import in.twizmwaz.cardinal.event.player.PlayerJoinMatchThreadEvent;
import in.twizmwaz.cardinal.event.player.PlayerQuitMatchThreadEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class TeamHandler implements Listener {

  /**
   * Puts the player on the Observers when they join a match thread.
   *
   * @param event The event.
   */
  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerJoinMatchThread(PlayerJoinMatchThreadEvent event) {
    Team.getObservers(event.getMatchThread().getCurrentMatch()).addPlayer(event.getPlayer(), true, false);
  }

  /**
   * Removes the player from their team when they quit a match thread.
   *
   * @param event The event.
   */
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerQuitMatchThread(PlayerQuitMatchThreadEvent event) {
    Player player = event.getPlayer();
    Cardinal.getModule(TeamModule.class).getTeamByPlayer(event.getMatchThread().getCurrentMatch(), player)
        .removePlayer(player);
  }

}
