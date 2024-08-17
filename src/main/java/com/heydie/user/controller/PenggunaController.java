package com.heydie.user.controller;

import com.heydie.user.entity.Pengguna;
import com.heydie.user.model.Sekolah;
import com.heydie.user.model.SekolahPengguna;
import com.heydie.user.service.PenggunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pengguna")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PenggunaController {

    private final PenggunaService penggunaService;

    @GetMapping("/all")
    public ResponseEntity<List<Pengguna>> getAll() {
        return new ResponseEntity<>(penggunaService.getPengguna(), HttpStatus.OK);
    }
    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<Pengguna> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(penggunaService.getPenggunaById(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Pengguna> create(@RequestBody Pengguna pengguna) {
        return new ResponseEntity<>(penggunaService.create(pengguna), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Pengguna> update(@RequestBody Pengguna pengguna, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(penggunaService.update(pengguna, id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(penggunaService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/findby-id-sekolah/{id}")
    public ResponseEntity<List<Pengguna>> findByIdSekolah(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(penggunaService.getByIdSekolah(id), HttpStatus.OK);
    }

    @PostMapping("/find-sekolah/{id}")
    public ResponseEntity<Sekolah> findByIdPengguna(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(penggunaService.findSekolahByIdPengguna(id), HttpStatus.OK);
    }

    @GetMapping("/sekolah-pengguna")
    public ResponseEntity<List<SekolahPengguna>> penggunaWithSekolah() {
        return new ResponseEntity<>(penggunaService.getAllSekolahPengguna(), HttpStatus.OK);
    }

}
