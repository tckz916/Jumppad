package com.github.tckz916.jumppad.listener;

import com.github.tckz916.jumppad.JumpPad;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by tckz916 on 2016/02/05
 */
public class PlayerListener implements Listener {

    private JumpPad plugin = JumpPad.getInstance();

    @EventHandler
    public void move(PlayerMoveEvent event) {
        if (!((event.getFrom().getBlock().getType() != Material.GOLD_PLATE && event.getTo().getBlock().getType() == Material.GOLD_PLATE)
                || (event.getFrom().getBlock().getType() != Material.IRON_PLATE && event.getTo().getBlock().getType() == Material.IRON_PLATE)
                || (event.getFrom().getBlock().getType() != Material.STONE_PLATE && event.getTo().getBlock().getType() == Material.STONE_PLATE)
                || (event.getFrom().getBlock().getType() != Material.WOOD_PLATE && event.getTo().getBlock().getType() == Material.WOOD_PLATE))) {
            return;
        }
        Player player = event.getPlayer();
        Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN, 2);
        if (!(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
            return;
        }

        Sign sign = (Sign) block.getState();
        List<String> lines = new ArrayList<>(Arrays.asList(sign.getLines()));
        if (!lines.get(0).equals("[jump]")) {
            return;
        }

        double multiply = isDouble(lines.get(1)) ? Double.parseDouble(lines.get(1)) : 3.0;
        double location_y = isDouble(lines.get(2)) ? Double.parseDouble(lines.get(2)) : 1.0;
        player.setVelocity(player.getLocation().getDirection().multiply(multiply).setY(location_y));
        player.playSound(player.getLocation(), Sound.ZOMBIE_INFECT, 4.0F, 2.0F);


    }


    private boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
