package ru.cheezeapp.service.vid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.VidStrainEntity;

import java.util.Optional;

/**
 * Контроллер для обработки CRUD запросов, связанных с видами.
 *
 * @author Pavel Chupikov
 */
@Service
@Slf4j
public class VidCrudService {

    @Autowired
    VidStrainRepository vidStrainRepository;

    /**
     * Процедура добавления вида в БД.
     *
     * @param vid вид для добавления
     */
    @Transactional
    public void addVid(VidStrainEntity vid) {
        log.info("VID CRUD SERVICE\tEntered addVid() method");
        vidStrainRepository.save(vid);
        log.info("VID CRUD SERVICE\taddVid() method done");
    }

    /**
     * Процедура обновления вида в БД.
     *
     * @param vid вид для обновления
     */
    @Transactional
    public void updateVid(VidStrainEntity vid) {
        log.info("VID CRUD SERVICE\tEntered updateVid() method");
        Optional<VidStrainEntity> vidStrainEntity = vidStrainRepository.findById(vid.getId());
        if (vidStrainEntity.isPresent()) {
            vid.setStrains(vidStrainEntity.get().getStrains());
            vidStrainRepository.save(vid);
            log.info("VID CRUD SERVICE\tVid was updated");
        }
        log.info("VID CRUD SERVICE\tupdateVid() method done");
    }

    /**
     * Процедура мягкого удаления вида по ID.
     * Помечаем вид как удаленный и обновляем его в БД.
     *
     * @param id ID вида для удаления
     */
    @Transactional
    public void softDeletionById(Long id) {
        log.info("VID CRUD SERVICE\tEntered softDeletionById() method");
        Optional<VidStrainEntity> vidStrainEntity = vidStrainRepository.findById(id);
        if (vidStrainEntity.isPresent()) {
            vidStrainEntity.get().setDeleted(true);
            vidStrainRepository.save(vidStrainEntity.get());
            log.info("VID CRUD SERVICE\tsoftDeletionById() method done");
        } else {
            log.info("VID CRUD SERVICE\tsoftDeletionById() method done with exception");
            throw new RuntimeException("Вид не существует");
        }
    }

    /**
     * Процедура полного удаления вида по ID.
     *
     * @param id ID вида для удаления
     */
    @Transactional
    public void hardDeletionById(Long id) {
        log.info("VID CRUD SERVICE\tEntered hardDeletionById() method");
        Optional<VidStrainEntity> vidStrainEntity = vidStrainRepository.findById(id);
        if (vidStrainEntity.isPresent()) {
            vidStrainRepository.deleteById(id);
            log.info("VID CRUD SERVICE\thardDeletionById() method done");
        } else {
            log.info("VID CRUD SERVICE\thardDeletionById() method done with exception");
            throw new RuntimeException("Вид не существует");
        }
    }

    /**
     * Процедура удаления всех видов в корзине из БД
     */
    @Transactional
    public void hardDeleteAll() {
        log.info("VID CRUD SERVICE\tEntered hardDeleteAll() method");
        vidStrainRepository.deleteAllByDeletedIsTrue();
        log.info("VID CRUD SERVICE\thardDeleteAll() method done");
    }

    /**
     * Процедура восстановления вида по ID.
     *
     * @param id ID вида для восстановления
     */
    @Transactional
    public void restoreById(Long id) {
        log.info("VID CRUD SERVICE\tEntered restoreById() method");
        Optional<VidStrainEntity> vidStrainEntity = vidStrainRepository.findById(id);
        if (vidStrainEntity.isPresent()) {
            vidStrainEntity.get().setDeleted(false);
            vidStrainRepository.save(vidStrainEntity.get());
            log.info("VID CRUD SERVICE\trestoreById() method done");
        } else {
            log.info("VID CRUD SERVICE\trestoreById() method done with exception");
            throw new RuntimeException("Вид не существует");
        }
    }

}
