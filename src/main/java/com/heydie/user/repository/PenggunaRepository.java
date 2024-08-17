package com.heydie.user.repository;

import com.heydie.user.entity.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PenggunaRepository extends JpaRepository<Pengguna, Integer> {
    List<Pengguna> findPenggunaByIdSekolah(Integer idSekolah);
}
