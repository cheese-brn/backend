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
        if(vidStrainEntity.isPresent()) {
            vid.setStrains(vidStrainEntity.get().getStrains());
            vidStrainRepository.save(vid);
            log.info("VID CRUD SERVICE\tVid was updated");
        }
        log.info("VID CRUD SERVICE\tupdateVid() method done");
    }


}
