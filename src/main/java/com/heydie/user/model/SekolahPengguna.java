package com.heydie.user.model;

import com.heydie.user.entity.Pengguna;
import lombok.Data;

@Data
public class SekolahPengguna extends Pengguna {
    private String namaSekolah;
}
