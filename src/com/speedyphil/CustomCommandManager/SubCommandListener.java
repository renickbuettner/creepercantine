package com.speedyphil.CustomCommandManager;

import java.util.EventListener;

public interface SubCommandListener extends EventListener{
	public void onCommand(SubCommandEvent event);
}
