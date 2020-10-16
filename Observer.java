package com.company;

abstract class Observer {
    protected Player player;
    public abstract void update(int idx);
}
