package de.sarkasaa.example.proxy;

import de.sarkasaa.example.Content;
import de.sarkasaa.example.block.GUIBetterFurnace;
import net.minecraft.client.gui.ScreenManager;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(Content.containerBetterFurnace, GUIBetterFurnace::new);
    }
}
