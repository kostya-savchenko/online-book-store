package kristar.projects.services.impl;

import kristar.projects.dto.category.CategoryRequestDto;
import kristar.projects.dto.category.CategoryResponseDto;
import kristar.projects.exception.EntityNotFoundException;
import kristar.projects.mapper.CategoryMapper;
import kristar.projects.model.Category;
import kristar.projects.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements kristar.projects.services.CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(category -> (categoryMapper.toDto(category)));
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can not"
                        + " find categoryIds with id " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto save(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category "
                        + "not found with id: " + id));
        categoryMapper.updateCategoryFromDto(category, categoryRequestDto);

        categoryRepository.save(category);

        return categoryMapper.toDto(category);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find"
                        + " category with id " + id));
        categoryRepository.deleteById(category.getId());
    }
}
