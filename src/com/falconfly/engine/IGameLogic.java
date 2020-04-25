package com.falconfly.engine;

import com.falconfly.engine.input.MouseInput;

public interface IGameLogic {
	void init() throws Exception;

	void input(EngineWindow window, MouseInput mouseInput);

	void update(float interval, MouseInput mouseInput);

	void render(EngineWindow window);

	void cleanup();
}
