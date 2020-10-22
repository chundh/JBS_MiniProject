package com.company.player;

abstract class Observer {
    protected Player player;
    public abstract void update(int idx);
}
