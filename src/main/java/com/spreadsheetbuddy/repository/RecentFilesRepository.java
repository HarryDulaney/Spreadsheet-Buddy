package com.spreadsheetbuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentFilesRepository extends CrudRepository<String, String> {
    List<String> findAllByOwnerId(String ownerId);
}
