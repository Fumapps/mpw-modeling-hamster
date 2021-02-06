#ifndef DE_UNISTUTTGART_HAMSTER_GAMELOGIMPL_H
#define DE_UNISTUTTGART_HAMSTER_GAMELOGIMPL_H

#include "GameLog.h"

namespace mpw {

class GameLogImpl: public GameLog {
public:

	GameLogImpl();

    void write(std::shared_ptr<mpw::Actor> actor, std::string message) override;

};

}

#endif //DE_UNISTUTTGART_HAMSTER_GAMELOGIMPL_H
