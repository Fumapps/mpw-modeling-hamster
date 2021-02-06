package de.unistuttgart.hamster.viewmodel.impl;

import de.unistuttgart.hamster.viewmodel.GameViewModel;
import de.unistuttgart.hamster.viewmodel.GameViewPresenter;

import java.util.concurrent.Semaphore;

public class GameViewPresenterImpl extends GameViewPresenter {

	public GameViewPresenterImpl() {
		super(new Semaphore(1, true));

	}

	@Override
	public void playClicked() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void pauseClicked() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void undoClicked() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void redoClicked() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void textTyped(String text) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void speedChanged(double speedValue) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void close() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public GameViewModel getViewModel() {
		throw new RuntimeException("not implemented");
	}

	@Override
	public void bind() {
		throw new RuntimeException("not implemented");
	}

}
