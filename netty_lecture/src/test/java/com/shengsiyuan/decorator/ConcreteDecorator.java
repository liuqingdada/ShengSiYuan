package com.shengsiyuan.decorator;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public int read(byte[] bytes) {
        int read = super.read(bytes);
        int read0 = read0(bytes);
        return read + read0;
    }

    @Override
    public void close() {
        super.close();
        close0();
    }

    private int read0(byte[] bytes) {
        return bytes.length;
    }

    private void close0() {
        System.out.println("concrete decorator close0");
    }
}
