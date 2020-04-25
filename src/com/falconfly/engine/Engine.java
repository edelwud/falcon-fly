package com.falconfly.engine;

import com.falconfly.config.MainLogger;
import com.falconfly.engine.input.MouseInput;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Engine implements Runnable {
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static final String TITLE = "Falcon Fly";

	public static final int TARGET_FPS = 75;
	public static final int TARGET_UPS = 30;

	private final MainLogger engineLogger;
	private final Timer timer;

    private EngineWindow window;
    private final IGameLogic gameLogic;
    private final MouseInput mouseInput;

	public Engine(String windowTitle, int width, int height, boolean vsSync, IGameLogic gameLogic) throws Exception {
		window = EngineWindow.getWindowInstance(windowTitle, width, height, vsSync);
		engineLogger = new MainLogger("./store/logs/application_log_engine.txt", Engine.class.getSimpleName());
		this.gameLogic = gameLogic;
		timer = new Timer();
		mouseInput = new MouseInput();
	}

	@Override
	public void run() {
		try {
			init();
			gameLoop();
		} catch (Exception e) {
			//engineLogger.logger.info(e.toString());
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}

	protected void init() throws Exception {
		window.create();
		timer.init();
		gameLogic.init();
	}

	protected void gameLoop() {
		float elapsedTime;
		float accumulator = 0f;
		float interval = 1f / TARGET_UPS;

		boolean running = true;
		while (running && !glfwWindowShouldClose(window.id)) {
			elapsedTime = timer.getElapsedTime();
			accumulator += elapsedTime;

			input();

			while (accumulator >= interval) {
				update(interval);
				accumulator -= interval;
			}

			render();

			if (!window.isvSync()) {
				sync();
			}
		}
	}

	protected void cleanup() {
		gameLogic.cleanup();
		window.destroy();
	}

	private void sync() {
		float loopSlot = 1f / TARGET_FPS;
		double endTime = timer.getLastLoopTime() + loopSlot;
		while (timer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				engineLogger.logger.info(e.toString());
			}
		}
	}

	protected void input() {
		mouseInput.input(window);
		gameLogic.input(window, mouseInput);
	}

	protected void update(float interval) {
		gameLogic.update(interval, mouseInput);
	}

	protected void render() {
		gameLogic.render(window);
		window.update();
	}

    public EngineWindow getWindow() {
        return window;
    }
}
