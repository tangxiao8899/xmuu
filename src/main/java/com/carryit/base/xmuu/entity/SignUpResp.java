package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class SignUpResp implements Serializable {

    private SignUpDTO signUpDTO;
    private List<SignUpDTO> signUpList;

    public SignUpDTO getSignUpDTO() {
        return signUpDTO;
    }

    public void setSignUpDTO(SignUpDTO signUpDTO) {
        this.signUpDTO = signUpDTO;
    }

    public List<SignUpDTO> getSignUpList() {
        return signUpList;
    }

    public void setSignUpList(List<SignUpDTO> signUpList) {
        this.signUpList = signUpList;
    }
}
