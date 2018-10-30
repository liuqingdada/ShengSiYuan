package com.shengsiyuan.decorator;

public class Decorator implements Component {
    private Component mComponent;

    public Decorator(Component component) {
        mComponent = component;
    }

    @Override
    public int read(byte[] bytes) {
        return mComponent.read(bytes);
    }

    @Override
    public void close() {
        mComponent.close();
    }
}
