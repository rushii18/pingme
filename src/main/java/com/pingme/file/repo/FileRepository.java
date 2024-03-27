package com.pingme.file.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.file.FileContent;

public interface FileRepository extends JpaRepository<FileContent, Integer> {

}
