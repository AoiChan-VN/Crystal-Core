package vn.aoi.onii.skill;

import vn.aoi.onii.classsystem.ClassContext;

public interface Skill {

    String getId();

    void execute(ClassContext ctx);
}
