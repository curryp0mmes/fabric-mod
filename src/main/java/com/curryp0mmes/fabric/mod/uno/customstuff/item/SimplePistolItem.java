package com.curryp0mmes.fabric.mod.uno.customstuff.item;

public class SimplePistolItem extends GunItem {

    public SimplePistolItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAmmunition() {
        return 15;
    }

}
