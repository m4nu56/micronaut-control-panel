package io.micronaut.controlpanel.core;

import jakarta.inject.Singleton;

import java.util.List;

/**
 * TODO: add javadoc.
 *
 * @author Álvaro Sánchez-Mariscal
 * @since 1.0.0
 */
@Singleton
public class DefaultControlPanelRepository implements ControlPanelRepository {

    private final List<ControlPanel> controlPanels;
    private final List<ControlPanel.Category> categories;

    public DefaultControlPanelRepository(List<ControlPanel> controlPanels) {
        this.controlPanels = controlPanels;
        this.categories = controlPanels.stream()
                .map(ControlPanel::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public List<ControlPanel> findAll() {
        return controlPanels;
    }

    @Override
    public List<ControlPanel> findAllByCategory(String categoryId) {
        return controlPanels.stream()
                .filter(controlPanel -> controlPanel.getCategory().id().equals(categoryId))
                .toList();
    }

    @Override
    public ControlPanel findByName(String name) {
        return controlPanels.stream()
                .filter(controlPanel -> controlPanel.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ControlPanel.Category> findAllCategories() {
        return categories;
    }

    @Override
    public ControlPanel.Category findCategoryById(String categoryId) {
        return categories.stream()
                .filter(category -> category.id().equals(categoryId))
                .findFirst()
                .orElse(null);
    }
}