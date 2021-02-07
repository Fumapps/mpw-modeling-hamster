package de.unistuttgart.hamster.viewmodel.impl;

import de.unistuttgart.hamster.hamster.*;
import de.unistuttgart.iste.sqa.mpw.framework.mpw.LogEntry;
import de.unistuttgart.iste.sqa.mpw.framework.mpw.Size;
import de.unistuttgart.iste.sqa.mpw.framework.mpw.Tile;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.Color;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCell;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.ViewModelCellLayer;
import de.unistuttgart.iste.sqa.mpw.framework.viewmodel.impl.GameViewPresenterBase;
import javafx.beans.property.ReadOnlyListProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HamsterGameViewPresenter extends GameViewPresenterBase {
	private final Territory territory;

	private final Map<ReadOnlyHamster, Color> hamsterToColorMap = new HashMap<>();

	public HamsterGameViewPresenter(final HamsterGame game) {
		super(game);
		this.territory = game.getTerritory();
	}

	@Override
	protected Size getStageSizeFromConcreteStage() {
		return territory.getTerritorySize();
	}

	@Override
	protected ReadOnlyListProperty<Tile> getTilesPropertyFromConcreteStage() {
		return territory.getInternalTerritory().tilesProperty();
	}

	@Override
	protected Color getColorForLogEntry(LogEntry entry) {
		if (hamsterToColorMap.containsKey(entry.getActor())) {
			return hamsterToColorMap.get(entry.getActor());
		}
		return super.getColorForLogEntry(entry);
	}

	@Override
	protected void onSetTileNodeAtForCell(ViewModelCell cell, Tile tile) {
		configureWallImageView(cell, tile);
		configureGrainImageView(cell, tile);

		Optional<ReadOnlyHamster> hamsterOptional = findHamsterOnTile(tile);
		hamsterOptional.ifPresent(readOnlyHamster -> configureHamsterImageView(cell, readOnlyHamster));
	}

	private Optional<ReadOnlyHamster> findHamsterOnTile(Tile tile) {
		return tile.getContents().stream()
				.filter(ReadOnlyHamster.class::isInstance)
				.map(ReadOnlyHamster.class::cast).findFirst();
	}

	private void configureWallImageView(ViewModelCell cell, Tile tile) {
		var layer = new ViewModelCellLayer();
		layer.setImageName("Wall");
		refreshWallLayer(layer, tile);
		cell.getLayers().add(layer);
	}

	private void refreshWallLayer(ViewModelCellLayer layer, Tile tile) {
		layer.setVisible(tile.getContents().stream().anyMatch(Wall.class::isInstance));
	}

	private void configureGrainImageView(ViewModelCell cell, Tile tile) {
		var layer = new ViewModelCellLayer();
		refreshGrainLayer(layer, tile);
		cell.getLayers().add(layer);
	}

	private void refreshGrainLayer(ViewModelCellLayer layer, Tile tile) {
		final int grainCount = getGrainOfTile(tile).size();
		layer.setVisible(grainCount > 0);

		if (grainCount <= 12) {
			layer.setImageName(grainCount + "Corn");
		} else {
			layer.setImageName("12PlusCorn");
		}
	}

	private List<Grain> getGrainOfTile(final Tile tile) {
		return tile.getContents().stream()
				.filter(Grain.class::isInstance)
				.map(Grain.class::cast)
				.collect(Collectors.toList());
	}

	private void configureHamsterImageView(ViewModelCell cell, ReadOnlyHamster hamster) {
		updateColorMap();

		var layer = new ViewModelCellLayer();
		layer.setImageName("Hamster" + hamsterToColorMap.get(hamster).name());

		hamster.directionProperty().addListener((v, c, l) -> {
			runLocked(() -> {
				refreshHamsterLayer(layer, hamster);
			});
		});

		refreshHamsterLayer(layer, hamster);
		cell.getLayers().add(layer);
	}

	private void refreshHamsterLayer(ViewModelCellLayer layer, ReadOnlyHamster hamster) {
		layer.setVisible(hamster.getCurrentTile() != null);
		layer.setRotation(getRotationForDirection(hamster.getDirection()));
	}

	private void updateColorMap() {
		territory.getInternalTerritory().getTileContents().stream()
				.filter(ReadOnlyHamster.class::isInstance)
				.map(ReadOnlyHamster.class::cast)
				.filter(hamster -> !hamsterToColorMap.containsKey(hamster))
				.forEach(hamster -> {
					var color = HamsterColors.getColorForNthHamster(hamsterToColorMap.size());
					hamsterToColorMap.put(hamster, color);
				});
	}

}
