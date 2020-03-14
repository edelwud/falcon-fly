package com.falconfly.engine;

public interface IGameLogic {
	void init() throws Exception;

	void input(EngineWindow window);

	void update(float interval);

	void render(EngineWindow window);

	void cleanup();
}
