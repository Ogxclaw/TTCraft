package com.kirik.ttcraft.events.tasks;

import com.kirik.ttcraft.events.managers.AFKManager;
import com.kirik.ttcraft.main.TTCraft;

public class AFKTask implements Runnable {

    private final TTCraft instance;
    private final AFKManager afkManager;

    public AFKTask(TTCraft instance, AFKManager manager){
        this.instance = instance;
        this.afkManager = manager;
    }

    @Override
    public void run() {
        instance.sendConsoleMsg("Checking for AFK players..."); // TODO verbose mode
        afkManager.checkPlayers();
    }
    
}
