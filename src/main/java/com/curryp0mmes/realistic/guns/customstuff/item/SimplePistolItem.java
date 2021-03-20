package com.curryp0mmes.realistic.guns.customstuff.item;

public class SimplePistolItem extends GunItem {

    public SimplePistolItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAmmunition() {
        return 15;
    }

}
