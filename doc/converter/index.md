The binding is using converter to translate between openHAB item / types and device parameters / types used by the aleoncean library.

# parameter class - item class

## RockerSwitchAction - Rollershutter item

Maps up pressed / up released / down pressed / down released to up / down / stop.

If you press up / down, the roller shutter will move up / down.

If the time between press and released event is shorter then one second, the roller shutter moving should be stopped. If the time is longer, the roller shutter should move until a end position is reached or you stop it by yourself (e.g. press again and release faster).
