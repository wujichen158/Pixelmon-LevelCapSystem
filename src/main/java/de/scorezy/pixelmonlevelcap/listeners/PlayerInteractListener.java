package de.scorezy.pixelmonlevelcap.listeners;

import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import de.scorezy.pixelmonlevelcap.utils.BadgeUtils;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PlayerInteractListener {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof PixelmonEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            int pokemonLevel = ((PixelmonEntity) event.getTarget()).getLvl().getPokemonLevel();
            int maxLevel = BadgeUtils.getLevelCapForPlayer(player);

            if (pokemonLevel > maxLevel) {
                event.setCanceled(true);
                event.setCancellationResult(ActionResultType.FAIL);
                player.sendMessage(
                        new TranslationTextComponent("pixelmonlevelcap.capped_msg.interact_blocked"),
                        player.getUUID());
            }
        }
    }
}
