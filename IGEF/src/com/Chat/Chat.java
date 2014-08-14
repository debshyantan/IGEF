package com.Chat;

import org.jivesoftware.smack.XMPPException;

public interface Chat {
    void sendMessage(String message) throws XMPPException;

    void release() throws XMPPException;
}
