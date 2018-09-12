# BroadcastReceiver
Esempio di un Broadcast Receiver

TEST ADB:
adb shell

am broadcast -a com.ziviello.broadcastreceiver.CMD --es Command "HELLO"
am broadcast -a com.ziviello.broadcastreceiver.CMD --es Command "CIAO"
