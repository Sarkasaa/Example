package de.sarkasaa.example.proxy;

import de.sarkasaa.example.GUIBetterFurnace;
import de.sarkasaa.example.Registry;
import net.minecraft.client.gui.ScreenManager;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(Registry.containerBetterFurnace, GUIBetterFurnace::new);
    }
}
