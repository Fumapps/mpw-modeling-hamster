#ifndef DE_UNISTUTTGART_HAMSTER_GAMEVIEWMODELIMPL_H
#define DE_UNISTUTTGART_HAMSTER_GAMEVIEWMODELIMPL_H

#include "ViewModelCell.h"
#include "GameViewModel.h"

namespace viewmodel {

class GameViewModelImpl: public GameViewModel {
private:
	using inherited = GameViewModel;

public:

	GameViewModelImpl();

    void init(mpw::Size size) override;

    std::shared_ptr<viewmodel::ViewModelCell> getCellAt(int row,
			int column) override;

};

}

#endif //DE_UNISTUTTGART_HAMSTER_GAMEVIEWMODELIMPL_H
