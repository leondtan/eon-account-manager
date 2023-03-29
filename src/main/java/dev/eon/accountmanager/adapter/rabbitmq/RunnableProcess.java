package dev.eon.accountmanager.adapter.rabbitmq;

@FunctionalInterface
public interface RunnableProcess {
    void runProcess(String payload);
}
