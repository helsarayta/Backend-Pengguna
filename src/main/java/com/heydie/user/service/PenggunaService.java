package com.heydie.user.service;

import com.heydie.user.entity.Pengguna;
import com.heydie.user.model.Sekolah;
import com.heydie.user.model.SekolahPengguna;
import com.heydie.user.repository.PenggunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PenggunaService {
    private final PenggunaRepository penggunaRepository;
    private final RestTemplate restTemplate;

    public List<Pengguna> getPengguna() {
        return penggunaRepository.findAll();
    }

    public Pengguna getPenggunaById(Integer id) {
        return penggunaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pengguna not found"));
    }

    public Pengguna create(Pengguna pengguna) {
        if(pengguna.getIdSekolah() == null) {
            throw new RuntimeException("Pengguna id sekolah is null");
        }
        return penggunaRepository.save(pengguna);
    }

    public Pengguna update(Pengguna pengguna, Integer id) {
        Pengguna penggunaById = getPenggunaById(id);
        penggunaById.setAlamat(pengguna.getAlamat());
        penggunaById.setNama(pengguna.getNama());
        penggunaById.setKategori(pengguna.getKategori());
        penggunaById.setIdSekolah(pengguna.getIdSekolah());
        return penggunaRepository.save(penggunaById);
    }

    public String delete(Integer id) {
        penggunaRepository.deleteById(id);
        return "Pengguna deleted";
    }

    public List<Pengguna> getByIdSekolah(Integer idSekolah) {
       return penggunaRepository.findPenggunaByIdSekolah(idSekolah).size() > 0 ? penggunaRepository.findPenggunaByIdSekolah(idSekolah) : null;
    }

    public Sekolah findSekolahByIdPengguna(Integer id) {
        Pengguna penggunaById = getPenggunaById(id);
        return restTemplate.getForObject("http://localhost:8080/api/v1/sekolah/find-by-id/" + penggunaById.getIdSekolah(), Sekolah.class);
    }

    public List<SekolahPengguna> getAllSekolahPengguna() {
        List<Pengguna> all = penggunaRepository.findAll();

        List<SekolahPengguna> listSk = new ArrayList<>();
        for(int i= 0; i < all.size(); i++){
            SekolahPengguna sp = new SekolahPengguna();
            sp.setAlamat(all.get(i).getAlamat());
            sp.setNama(all.get(i).getNama());
            sp.setKategori(all.get(i).getKategori());
            sp.setIdSekolah(all.get(i).getIdSekolah());
            sp.setId(all.get(i).getId());
            Sekolah sekolah = findSekolahByIdPengguna(all.get(i).getId());
            sp.setNamaSekolah(sekolah.getNama());

            listSk.add(sp);
        }

        return listSk;
    }
}
