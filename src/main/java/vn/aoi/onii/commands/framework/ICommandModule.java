package vn.aoi.onii.commands.framework;

import cloud.commandframework.annotations.AnnotationParser;

public interface ICommandModule {
    void register(AnnotationParser<?> parser);
}
