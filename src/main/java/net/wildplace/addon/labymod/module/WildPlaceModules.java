package net.wildplace.addon.labymod.module;

import com.google.gson.JsonObject;
import net.labymod.api.events.TabListEvent;
import net.labymod.ingamegui.moduletypes.ColoredTextModule;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;
import net.wildplace.addon.labymod.Main;

import java.util.Collections;
import java.util.List;

public class WildPlaceModules extends Server {


    public static String nickname = "Error";
    private String points = "-9999999";
    private String killstreak = "-5";

    public WildPlaceModules() {
        super("wildplace", "wildplace.net", "wildplace.de");
    }

    @Override
    public void onJoin(final ServerData serverData) {

    }

    @Override
    public ChatDisplayAction handleChatMessage(final String s, final String s1) throws Exception {
        return null;
    }


    @Override
    public void handlePluginMessage(final String channelName, final PacketBuffer packetBuffer) throws Exception {
        if(channelName.equals("nickName")) {
            nickname = new String(packetBuffer.array());
        }

        if(channelName.equals("kBattle_points")) {
            points = new String(packetBuffer.array());
        }

        if(channelName.equals("kFFA_killstreak")) {
            killstreak = new String(packetBuffer.array());
        }
    }

    @Override
    public void handleTabInfoMessage(final TabListEvent.Type type, final String s, final String s1) throws Exception {

    }

    @Override
    protected void initConfig(JsonObject config) {
    }

    @Override
    public void fillSubSettings(final List<SettingsElement> list) {

    }

    @Override
    public void addModuleLines( List<DisplayLine> lines ) {

        if(Main.nickname) {
            if(!nickname.equals("Error")) {
                DisplayLine dl = new DisplayLine( "Nick", Collections.singletonList(ColoredTextModule.Text.getText(nickname)));
                lines.add(dl);
            }
        }

        if(Main.knockbattle) {
            if(!points.equals("-9999999")) {
                DisplayLine dl = new DisplayLine( "Punkte", Collections.singletonList(ColoredTextModule.Text.getText(String.valueOf(points))));
                lines.add(dl);
            }
        }

        if(Main.knockffa) {
            if(!killstreak.equals("-5")) {
                DisplayLine dl = new DisplayLine( "Killstreak", Collections.singletonList(ColoredTextModule.Text.getText(String.valueOf(killstreak))));
                lines.add(dl);
            }
        }

        super.addModuleLines(lines);
    }

}
