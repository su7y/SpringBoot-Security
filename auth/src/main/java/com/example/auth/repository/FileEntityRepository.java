package com.example.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth.entity.FileEntity;

public interface FileEntityRepository  extends JpaRepository<FileEntity,Long>{

}
