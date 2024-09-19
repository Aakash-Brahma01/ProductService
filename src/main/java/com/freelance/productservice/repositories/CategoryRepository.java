package com.freelance.productservice.repositories;

import com.freelance.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category,UUID> {
    Optional<Category> findById(UUID uuid);

    List<Category> findAllById(Iterable<UUID> uuids);
}
