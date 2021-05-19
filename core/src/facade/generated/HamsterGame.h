/*
 * This file is generated. Do not change it manually.
 */

#ifndef DE_UNISTUTTGART_HAMSTER_HAMSTERGAME_H
#define DE_UNISTUTTGART_HAMSTER_HAMSTERGAME_H

#include <memory>
#include <string>
#include <exception>
#include "MiniProgrammingWorld.h"

namespace hamster {
class Territory;
}
namespace mpw {
class GamePerformance;
}
namespace mpw {
class UserInputInterface;
}
namespace commands {
class CommandStack;
}

namespace hamster {

/**
 * A MiniProgrammingWorld representing an instance of a hamster game. A hamster game consists of a territory, on
 * which the game takes place, a game log for messages, and a command stack for keeping track of the
 * game's history.
 */
class HamsterGame: public mpw::MiniProgrammingWorld {

private:

	using inherited = MiniProgrammingWorld;

	std::shared_ptr<hamster::Territory> territory;

public:

	static std::shared_ptr<HamsterGame> create();
private:
	HamsterGame() = default;
	void init();
public:

	/// \brief returns a shared_ptr of type HamsterGame which shares ownership of *this
	std::shared_ptr<HamsterGame> shared_from_this() {
		return std::dynamic_pointer_cast < HamsterGame
				> (inherited::shared_from_this());
	}
	/// \brief returns a shared_ptr of const type HamsterGame which shares ownership of *this
	std::shared_ptr<const HamsterGame> shared_from_this() const {
		return std::dynamic_pointer_cast<const HamsterGame>(
				inherited::shared_from_this());
	}

	virtual std::shared_ptr<hamster::Territory> getTerritory() noexcept;
	virtual std::shared_ptr<const hamster::Territory> getTerritory() const noexcept;

public:

	virtual ~HamsterGame() = default;

	/**
	 * Start the execution of a game. Before executing start, no commands can be
	 * executed by the actor objects in the game.
	 * This is only possible if the current mode is Mode.INITIALIZING
	 * The game will be started in running mode.
	 */
	void startGame();

	/**
	 * Start the execution of a game. Before executing start, no commands can be
	 * executed by the actor objects in the game.
	 * This is only possible if the current mode is Mode.INITIALIZING
	 * The game will be started in pause mode.
	 */
	void startGamePaused();

	/**
	 * Hard-resets the game command stack. It clears executed commands and undone commands, however, it does NOT
	 * undo all commands. If this behaviour is desired, it is necessary to call undoAll first.
	 * This also sets the mode to initializing.
	 */
	void hardReset();

	/**
	 * Stop the execution of the game. The game is finished and needs to be reset / hardReset
	 * or closed.
	 * If the game is already stopped, this does nothing
	 */
	void stopGame();

	/**
	 * Pauses the game when it is running.
	 * If the game is not running (paused previously, not started or stopped), an exception
	 * is thrown.
	 * This is executed asynchronously, but this method waits until the started thread
	 * has performed a paused state.
	 */
	void pauseGame();

	/**
	 * Resumes the game.
	 * It is only possible to execute this in paused mode.
	 */
	void resumeGame();

	/**
	 * Inform a user about an abnormal execution aborting.
	 * This blocks until it returns or is aborted
	 * This must be thread safe
	 * @param t The throwable which lead to aborting the program.
	 */
	void confirmAlert(const std::exception &t);

	std::shared_ptr<commands::CommandStack> getGameCommandStack();

	static constexpr unsigned cFeatureKeyTerritory = 6;
	using MiniProgrammingWorld::cFeatureKeyEditorCommandStack;
	using MiniProgrammingWorld::cFeatureKeyPerformance;
	using MiniProgrammingWorld::cFeatureKeyGameLog;
	using MiniProgrammingWorld::cFeatureKeyUserInputInterface;

};

}

#endif //DE_UNISTUTTGART_HAMSTER_HAMSTERGAME_H
