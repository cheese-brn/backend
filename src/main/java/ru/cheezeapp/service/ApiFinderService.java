package ru.cheezeapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.cheezeapp.dao.VidStrainRepository;
import ru.cheezeapp.entity.VidStrainEntity;
import ru.cheezeapp.model.JSONRodVid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ApiFinderService {
    private final FinderService finderService;
    private final VidStrainRepository vidStrainRepository;

    public ApiFinderService(FinderService finderService, VidStrainRepository vidStrainRepository) {
        this.finderService = finderService;
        this.vidStrainRepository = vidStrainRepository;
    }

    public List<JSONRodVid> findVidByRodId(Long id) {
        List<JSONRodVid> output = new ArrayList<>();
        List<VidStrainEntity> allVids = vidStrainRepository.findAllByRodStrain(finderService.findRodById(id));
        if (!allVids.isEmpty()){
            for(VidStrainEntity vid : allVids){
                JSONRodVid buf = new JSONRodVid();
                buf.setId(vid.getId());
                buf.setName(vid.getName());
                buf.setChildrenCount(vid.getStrains().size());
                output.add(buf);
            }
        }

        return output;
    }
}
