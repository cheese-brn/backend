package ru.cheezeapp.utils.structures;

import lombok.Setter;
import ru.cheezeapp.entity.SubPropertyEntity;

import java.util.List;

@Setter
public class FunctionPair {

    private List<SubPropertyEntity> fst;
    private String snd;

    public List<SubPropertyEntity> fst() {
        return fst;
    }

    public String snd() {
        return snd;
    }

    public FunctionPair(List<SubPropertyEntity> fst, String snd) {
        this.fst = fst;
        this.snd = snd;
    }

}
