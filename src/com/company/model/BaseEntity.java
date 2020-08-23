package com.company.model;
/**
@author Evgeniy Lukashik
*/
public class BaseEntity  {
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isNew(){
        return (this.id == null);
    }
}
