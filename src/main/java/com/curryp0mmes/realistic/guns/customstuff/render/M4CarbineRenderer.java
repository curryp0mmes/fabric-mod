package com.curryp0mmes.realistic.guns.customstuff.render;

import com.curryp0mmes.realistic.guns.customstuff.item.M4CarbineItem;
import com.curryp0mmes.realistic.guns.customstuff.model.M4CarbineModel;
import software.bernie.geckolib3.renderer.geo.GeoItemRenderer;

public class M4CarbineRenderer extends GeoItemRenderer<M4CarbineItem> {

public M4CarbineRenderer()
       {
       super(new M4CarbineModel());
       }
      }