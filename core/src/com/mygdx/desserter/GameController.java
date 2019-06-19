package com.mygdx.desserter;

import com.badlogic.gdx.Game;
import com.mygdx.desserter.screens.MainMenu;

public class GameController extends Game {

	@Override
	public void create() {
		setScreen(new MainMenu(this));
	}

}

